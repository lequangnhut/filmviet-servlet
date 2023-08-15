package Controll.Filter;

import Controll.Contanst.SessionAtrr;
import Controll.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebFilter({"/login", "/register", "/forgotpass", "/validateotp", "/newpassword", "/profile", "/editprofile", "/changepass", "/favorites", "/history", "/transaction", "/verifyemail"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();

        switch (path) {
            case "/login":
            case "/register":
            case "/forgotpass":
                checkOutUser(req, resp, chain);
                break;
            case "/validateotp":
            case "/newpassword":
                valiDateOTP(req, resp, chain);
                break;
            case "/profile":
            case "/editprofile":
            case "/changepass":
            case "/favorites":
            case "/history":
            case "/transaction":
                proFile(req, resp, chain);
                break;
            case "/verifyemail":
                verifyEmail(req, resp, chain);
                break;
            default:
                resp.sendRedirect("errorpage");
                break;
        }
    }

    private void valiDateOTP(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Object otp = session.getAttribute("otp");

        if (otp == null) {
            resp.sendRedirect("errorpage");
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void proFile(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        if (user == null) {
            resp.sendRedirect("errorpage");
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void verifyEmail(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String token = req.getParameter("token");

        if (token == null) {
            resp.sendRedirect("errorpage");
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void checkOutUser(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        if (user != null) {
            resp.sendRedirect("index");
        } else {
            chain.doFilter(req, resp);
        }
    }

}