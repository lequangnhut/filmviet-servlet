package Controll.Controller.Admin;

import java.io.IOException;
import java.util.List;

import Controll.DTO.VideoLikedInfo;
import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Service.HoaDonService;
import Controll.Service.StatsService;
import Controll.Service.UserService;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.StatsServiceImpl;
import Controll.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/dashboarh", "/likeonevideo", "/userlike", "/usershare", "/doanhthu" })
public class HomeControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StatsService statsService = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();
	private HoaDonService hoadonService = new HoaDonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		switch (path) {
		case "/dashboarh":
			doGetDashBoarh(request, response);
			break;
		case "/likeonevideo":
			doGetListVideoLike(request, response);
			break;
		case "/userlike":
			doGetUserLike(request, response);
			break;
		case "/usershare":
			doGetUserShare(request, response);
			break;
		case "/doanhthu":
			doGetDoanhThu(request, response);
			break;
		}
	}

	protected void doGetDashBoarh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Hoadon> hoadon = hoadonService.findAll();
		List<Hoadon> hoadonSuccess = hoadonService.findHoadonSuccess();

		int totalPrice = 0;
		for (Hoadon hd : hoadonSuccess) {
			String vnp_Aumout = hd.getVnp_Amount();
			int price = Integer.parseInt(vnp_Aumout);

			totalPrice += price;
		}

		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("hoadon", hoadon);
		request.getRequestDispatcher("/views/admin/dashboarh.jsp").forward(request, response);
	}

	protected void doGetListVideoLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<VideoLikedInfo> likeInfo = statsService.findVideoLikedInfo();
		request.setAttribute("countlike", likeInfo);
		request.getRequestDispatcher("/views/admin/list-likeVideo.jsp").forward(request, response);
	}

	protected void doGetUserLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String videoHref = request.getParameter("href");

		List<VideoLikedInfo> video = statsService.findVideoLikedInfo();

		if (videoHref == null) {
			request.setAttribute("video", video);
		} else {
			List<User> user = userService.findUserLikedVideoByVideoHref(videoHref);
			request.setAttribute("user", user);
		}

		request.setAttribute("videoHref", videoHref);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/admin/list-userLike.jsp").forward(request, response);
	}

	protected void doGetUserShare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String videoHref = request.getParameter("href");

		List<VideoLikedInfo> video = statsService.findVideoLikedInfo();

		if (videoHref == null) {
			request.setAttribute("video", video);
		} else {
			List<User> user = userService.UserShareVideoByHref(videoHref);
			request.setAttribute("user", user);
		}

		request.setAttribute("videoHref", videoHref);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/admin/list-userShare.jsp").forward(request, response);
	}

	protected void doGetDoanhThu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String videoHref = request.getParameter("href");

		List<VideoLikedInfo> video = statsService.findVideoLikedInfo();

		if (videoHref == null) {
			request.setAttribute("video", video);
		} else {
			List<User> paymentVnpay = userService.UserPaymentVnpayByHref(videoHref);
			request.setAttribute("paymentVnpay", paymentVnpay);
		}

		request.setAttribute("videoHref", videoHref);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/admin/DoanhThu.jsp").forward(request, response);
	}

}
