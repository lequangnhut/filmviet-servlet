package Controll.Filter;

import Controll.Contanst.SessionAtrr;
import Controll.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/admin", "/dashboarh", "/likeonevideo", "/userlike", "/usershare", "/datauser", "/edituser", "/videoviews", "/videoadd", "/videoedit", "/videodelete", "/videodisabled", "/restorevideo", "/editvideodisabled", "/poster", "/doanhthu"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();

        switch (path) {
            case "/admin":
                checkOutAdmin(req, resp, chain);
                break;
            case "/dashboarh":
            case "/likeonevideo":
            case "/userlike":
            case "/usershare":
            case "/datauser":
            case "/edituser":
            case "/deleteuser":
            case "/videoviews":
            case "/videoadd":
            case "/videoedit":
            case "/videodelete":
            case "/videodisabled":
            case "/restorevideo":
            case "/poster":
            case "/doanhthu":
            case "/editvideodisabled":
                validateAdmin(req, resp, chain);
                break;
            default:
                resp.sendRedirect("errorpage");
                break;
        }
    }

    private void validateAdmin(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_ADMIN);

        if (user == null) {
            resp.sendRedirect("admin");
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void checkOutAdmin(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_ADMIN);

        if (user != null) {
            resp.sendRedirect("dashboarh");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
