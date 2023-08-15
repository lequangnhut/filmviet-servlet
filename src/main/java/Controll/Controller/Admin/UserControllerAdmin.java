package Controll.Controller.Admin;

import java.io.IOException;
import java.util.List;

import Controll.Contanst.SessionAtrr;
import Controll.Entity.User;
import Controll.Service.UserService;
import Controll.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/admin", "/logoutadmin", "/datauser", "/edituser" })
public class UserControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	private static final int USER_MAX_PAGE_SIZE = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String path = request.getServletPath();

		switch (path) {
		case "/admin":
			doGetLogin(request, response);
			break;
		case "/logoutadmin":
			doGetLogout(session, request, response);
			break;
		case "/datauser":
			doGetListUser(request, response);
			break;
		case "/edituser":
			doGetEditUser(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		switch (path) {
		case "/admin":
			doPostLogin(request, response);
			break;
		case "/edituser":
			doPostEditUser(request, response);
			break;
		}
	}

//	đăng nhập
	protected void doGetLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
	}

	protected void doPostLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			Boolean isAdmin = user.getIsAdmin();
			Boolean isActive = user.getIsActive();

			if (isAdmin && isActive) {
				response.sendRedirect("dashboarh");
				session.setAttribute(SessionAtrr.CURRENT_ADMIN, user);
				session.setAttribute("loginAdmin", true);
			} else {
				session.setAttribute("loginAdminFail", true);
				response.sendRedirect("admin");
			}
		} else {
			session.setAttribute("loginAdmin", false);
			response.sendRedirect("admin");
		}
	}

//	đăng xuất
	private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("admin");
		session.removeAttribute(SessionAtrr.CURRENT_ADMIN);
	}

//	danh sách người dùng
	protected void doGetListUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> countVideo = userService.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) USER_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);

		String pageNumber = request.getParameter("page");

		List<User> users;
		if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			users = userService.findAll(1, USER_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", 1);
		} else {
			users = userService.findAll(Integer.valueOf(pageNumber), USER_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", Integer.valueOf(pageNumber));
		}

		request.setAttribute("users", users);
		request.getRequestDispatcher("/views/admin/list-user.jsp").forward(request, response);
	}

//	chỉnh sửa người dùng
	protected void doGetEditUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userId = request.getParameter("id");
		User user = userService.findById(Integer.parseInt(userId));

		Boolean isAdmin = user.getIsAdmin();

		if (isAdmin) {
			session.setAttribute("EditMessage", true);
			response.sendRedirect("datauser");
		} else {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/views/admin/EditUser.jsp").forward(request, response);
		}
	}

	protected void doPostEditUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String fullname = request.getParameter("fullname");
		int StringStatus = Integer.parseInt(request.getParameter("status"));

		Boolean status = false;
		if (StringStatus == 1) {
			status = true;
		}

		User user = userService.update(email, password, phone, fullname, status);

		if (user != null) {
			response.sendRedirect("datauser");
		}
	}

}
