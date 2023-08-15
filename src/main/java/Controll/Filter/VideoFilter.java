package Controll.Filter;

import java.io.IOException;

import Controll.Contanst.SessionAtrr;
import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.HoaDonService;
import Controll.Service.VideoService;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.VideoServiceImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/video"})
public class VideoFilter implements Filter {

    HoaDonService hoadonService = new HoaDonServiceImpl();
    VideoService videoService = new VideoServiceImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String actionParam = req.getParameter("action");

        if (actionParam == null) {
            resp.sendRedirect("errorpage");
        } else {
            switch (actionParam) {
                case "details":
                    chain.doFilter(req, resp);
                    break;
                case "like":
                    proFile(req, resp, chain);
                    break;
                case "watch":
                    checkOutNotPayment(req, resp, chain);
                    break;
            }
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

    private void checkOutNotPayment(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String href = req.getParameter("id");

        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        Video video = videoService.findByHref(href);

        if (video.getPrice() != 0 && user == null) {
            resp.sendRedirect("video?action=details&id=" + href);
        } else if (video.getPrice() != 0 && user != null && video != null) {
            System.out.println("user id " + user.getId() + " video id " + video.getId());
            Hoadon hd = hoadonService.findHoaDonByUserIdAndVideoId(user.getId(), video.getId());
            System.out.println(hd);
            if (hd == null) {
                resp.sendRedirect("video?action=details&id=" + href);
            } else {
                if (hd.getVnp_ResponseCode().equals("00")) {
                    chain.doFilter(req, resp);
                } else {
                    resp.sendRedirect("video?action=details&id=" + href);
                }
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

}
