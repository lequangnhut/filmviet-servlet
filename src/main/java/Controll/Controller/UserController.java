package Controll.Controller;

import java.io.IOException;

import Controll.Contanst.SessionAtrr;
import Controll.DTO.GooglePojo;
import Controll.Entity.User;
import Controll.Service.EmailService;
import Controll.Service.UserService;
import Controll.Service.Impl.EmailServiceImpl;
import Controll.Service.Impl.UserServiceImpl;
import Controll.Util.GoogleUtils;
import Controll.Util.MethodRandomUtils;
import Controll.Util.SendEmailForgotPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/login", "/logout", "/register", "/forgotpass", "/validateotp", "/newpassword", "/profile",
        "/editprofile", "/changepass"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();
    private EmailService emailService = new EmailServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String path = request.getServletPath();
        switch (path) {
            case "/login":
                doGetLogin(request, response);
                break;
            case "/register":
                doGetRegister(request, response);
                break;
            case "/logout":
                doGetLogout(session, request, response);
                break;
            case "/forgotpass":
                doGetForgotPass(request, response);
                break;
            case "/profile":
                doGetProfile(session, request, response);
                break;
            case "/editprofile":
                doGetEditProfile(session, request, response);
                break;
            case "/changepass":
                doGetChangePass(session, request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String path = request.getServletPath();

        switch (path) {
            case "/login":
                doPostLogin(session, request, response);
                break;
            case "/register":
                doPostRegister(session, request, response);
                break;
            case "/forgotpass":
                doPostForgotPass(session, request, response);
                break;
            case "/validateotp":
                doPostValidateOTP(request, response);
                break;
            case "/newpassword":
                doPostNewPassword(session, request, response);
                break;
            case "/editprofile":
                doPostEditProfile(session, request, response);
                break;
            case "/changepass":
                doPostChangePass(session, request, response);
                break;
        }
    }

    //	login
    private void doGetLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

            String email = googlePojo.getEmail();
            String pass = MethodRandomUtils.RandomToken(6);
            String phone = MethodRandomUtils.RandomPhoneNumber();
            String fullname = MethodRandomUtils.RandomFullname();

            User user = userService.findByEmail(email);
            if (user == null) {
                User addUserEmail = userService.create(email, pass, phone, fullname);
                if (addUserEmail != null) {
                    session.setAttribute("loginSuccess", true);
                    session.setAttribute(SessionAtrr.CURRENT_USER, addUserEmail);
                    response.sendRedirect("index");
                }
            } else {
                if (user.getIsActive()) {
                    session.setAttribute("loginSuccess", true);
                    session.setAttribute(SessionAtrr.CURRENT_USER, user);
                    response.sendRedirect("index");
                } else {
                    response.sendRedirect("login");
                }
            }
        }
    }

    private void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        User user = userService.login(email, password);

        if (user != null) {
            Boolean isAdmin = user.getIsAdmin();
            Boolean isActive = user.getIsActive();

            if (isAdmin == false && isActive) {
                session.setAttribute("loginSuccess", true);
                session.setAttribute(SessionAtrr.CURRENT_USER, user);
                response.sendRedirect("index");
            } else {
                session.setAttribute("loginFail", true);
                response.sendRedirect("login");
            }
        } else {
            session.setAttribute("loginSuccess", false);
            response.sendRedirect("login");
        }
    }

    //	register
    private void doGetRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
    }

    private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String phone = request.getParameter("phone");
        String fullname = request.getParameter("fullname");
        String token = MethodRandomUtils.RandomToken(12);

        User existEmail = userService.findByEmail(email);
        User existPhone = userService.findByPhone(phone);

        if (existEmail == null && existPhone == null) {
            User auth = userService.register(email, password, phone, fullname, token);

            if (auth != null) {
                emailService.SendEmail(getServletContext(), auth, fullname, token);
                session.setAttribute("registerSuccess", true);
                response.sendRedirect("index");
            } else {
                response.sendRedirect("register");
            }
        } else {
            session.setAttribute("existPhone", existPhone != null);
            session.setAttribute("existEmail", existEmail != null);
            response.sendRedirect("register");
        }
    }

    //	đăng xuất
    private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session.removeAttribute(SessionAtrr.CURRENT_USER);
        response.sendRedirect("index");
    }

    //	forgot Pass
    protected void doGetForgotPass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/user/forgotPass.jsp").forward(request, response);
    }

    protected void doPostForgotPass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        User existEmail = userService.findByEmail(email);

        if (existEmail != null) {
            if (!existEmail.getIsActive()) {
                session.setAttribute("userFalse", true);
                response.sendRedirect("forgotpass");
            } else {
                String fullname = existEmail.getName();
                SendEmailForgotPassword.SendMail(request, response, session, email, fullname);
                request.getRequestDispatcher("/views/user/enterOTP.jsp").forward(request, response);
            }
        } else {
            session.setAttribute("existEmail", true);
            response.sendRedirect("forgotpass");
        }
    }

    protected void doPostValidateOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        int value = Integer.parseInt(request.getParameter("otp"));
        int otp = (int) session.getAttribute("otp");

        if (value == otp) {
            request.getRequestDispatcher("/views/user/newPassword.jsp").forward(request, response);
        } else {
            session.setAttribute("errorOTP", true);
            request.getRequestDispatcher("/views/user/enterOTP.jsp").forward(request, response);
        }
    }

    protected void doPostNewPassword(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newPass = request.getParameter("pass");
        String email = String.valueOf(session.getAttribute("email"));

        if (newPass != null && email != null) {
            User user = userService.resetPassword(email, newPass);

            if (user != null) {
                session.setAttribute("changePassSuccess", true);
                response.sendRedirect("login");
            }
        }
    }

    //	trang cá nhân
    protected void doGetProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        String email = auth.getEmail();
        String phone = auth.getPhone();
        String fullname = auth.getName();

        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("fullname", fullname);

        request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
    }

    //	chỉnh sửa trang cá nhân
    protected void doGetEditProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        String email = auth.getEmail();
        String fullname = auth.getName();
        String phone = auth.getPhone();

        request.setAttribute("email", email);
        request.setAttribute("fullname", fullname);
        request.setAttribute("phone", phone);

        request.getRequestDispatcher("/views/user/EditProfile.jsp").forward(request, response);
    }

    protected void doPostEditProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        String email = auth.getEmail();
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        String comfirm = request.getParameter("confirmation");

        if (comfirm != null && comfirm.equals("true")) {

            if (fullname != null && phone != null) {
                User user = userService.editProfile(email, fullname, phone);

                if (user != null) {
                    session.removeAttribute(SessionAtrr.CURRENT_USER);
                    response.sendRedirect("login");
                }
            }
        }
    }

    //	đổi mật khẩu
    protected void doGetChangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        String fullname = auth.getName();
        String email = auth.getEmail();
        String password = auth.getPassword();

        request.setAttribute("fullname", fullname);
        request.setAttribute("email", email);
        request.setAttribute("password", password);

        request.getRequestDispatcher("/views/user/changepass.jsp").forward(request, response);
    }

    protected void doPostChangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        String email = auth.getEmail();

        String newPass = request.getParameter("newPass");

        String comfirm = request.getParameter("confirmation");

        if (comfirm != null && comfirm.equals("true")) {
            User user = userService.changePassword(email, newPass);

            if (user != null) {
                session.removeAttribute(SessionAtrr.CURRENT_USER);
                response.sendRedirect("login");
            }
        }
    }
}
