package Controll.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controll.Contanst.SessionAtrr;
import Controll.Dao.UserDao;
import Controll.Dao.Impl.UserDaoImpl;
import Controll.Entity.History;
import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.HistoryService;
import Controll.Service.HoaDonService;
import Controll.Service.UserService;
import Controll.Service.VideoService;
import Controll.Service.Impl.HistoryServiceImpl;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.UserServiceImpl;
import Controll.Service.Impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/index", "/introduce", "/categories", "/favorites", "/history", "/search", "/verifyemail",
		"/transaction", "/errorpage" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int VIDEO_MAX_PAGE_SIZE = 12;
	private UserDao dao = new UserDaoImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	private UserService userService = new UserServiceImpl();
	private VideoService videoServervice = new VideoServiceImpl();
	private HoaDonService hoadonService = new HoaDonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String path = request.getServletPath();

		switch (path) {
		case "/index":
			doGetIndex(request, response);
			break;
		case "/introduce":
			doGetInfo(request, response);
			break;
		case "/categories":
			doGetCategories(request, response);
			break;
		case "/favorites":
			doGetFavorites(session, request, response);
			break;
		case "/history":
			doGetHistory(session, request, response);
			break;
		case "/search":
			doGetSearch(request, response);
			break;
		case "/verifyemail":
			doGetVerifySuccess(request, response);
			break;
		case "/transaction":
			doGetTransactionHistory(session, request, response);
			break;
		case "/errorpage":
			doGetErrorPage(request, response);
			break;
		}
	}

	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Video> videoTrend = videoServervice.findVideoTrending();

		List<Video> countVideo = videoServervice.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);

		String pageNumber = request.getParameter("page");

		List<Video> videos;
		if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			videos = videoServervice.findAll(1, VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", 1);
		} else {
			videos = videoServervice.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", Integer.valueOf(pageNumber));
		}

		request.setAttribute("videoTrend", videoTrend);
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
	}

	protected void doGetInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/introduce.jsp").forward(request, response);
	}

	protected void doGetCategories(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Video> countVideo = videoServervice.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);

		String pageNumber = request.getParameter("page");

		List<Video> videos;
		if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			videos = videoServervice.findAll(1, VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", 1);
		} else {
			videos = videoServervice.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", Integer.valueOf(pageNumber));
		}

		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/categories.jsp").forward(request, response);

	}

	protected void doGetFavorites(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		List<History> histories = historyService.findByUserAndIsLiked(user.getEmail());
		List<Video> videos = new ArrayList<>();

		for (int i = 0; i < histories.size(); i++) {
			videos.add(histories.get(i).getVideo());
		}

		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/favorites.jsp").forward(request, response);

	}

	protected void doGetHistory(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		List<History> histories = historyService.findByUser(user.getEmail());
		List<Video> videos = new ArrayList<>();

		int limit = 12;

		for (int i = 0; i < histories.size(); i++) {
			videos.add(histories.get(i).getVideo());

			if (videos.size() >= limit) {
				break;
			}
		}

		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/history.jsp").forward(request, response);

	}

	protected void doGetSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nameVideo = request.getParameter("search");

		List<Video> videoTrend = videoServervice.findVideoTrending();
		List<Video> videos = videoServervice.findByName(nameVideo);

		request.setAttribute("videoTrend", videoTrend);
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/SearchVideo.jsp").forward(request, response);
	}

	protected void doGetVerifySuccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		User user = userService.findByToken(token);
		if (user != null) {
			String email = user.getEmail();
			request.setAttribute("email", email);
			user.setIsActive(Boolean.TRUE);
			dao.update(user);
		}
		request.getRequestDispatcher("/views/user/VerifySuccess.jsp").forward(request, response);
	}

	protected void doGetTransactionHistory(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		if (user != null) {
			List<Hoadon> hoadon = hoadonService.findByEmail(user.getEmail());
			request.setAttribute("hoadon", hoadon);
			request.getRequestDispatcher("/views/user/Transactionhistory.jsp").forward(request, response);
		}
	}

	private void doGetErrorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/404Page/404page.jsp").forward(request, response);
	}

}
