package Controll.Controller.Admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import Controll.Entity.Video;
import Controll.Service.VideoService;
import Controll.Service.Impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/videoviews", "/videoadd", "/videoedit", "/videodelete", "/videodisabled", "/restorevideo", "/editvideodisabled"})
public class VideoControllerAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int VIDEO_MAX_PAGE_SIZE = 10;
    private VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/videoviews":
                doGetViewsVideo(request, response);
                break;
            case "/videodisabled":
                doGetVideoDisabled(request, response);
                break;
            case "/videoadd":
                doGetNewFilm(request, response);
                break;
            case "/videoedit":
                doGetEditFilm(request, response);
                break;
            case "/editvideodisabled":
                doGetEditFilmDisable(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/videoadd":
                doPostNewFilm(request, response);
                break;
            case "/videoedit":
                doPostEditFilm(request, response);
                break;
            case "/editvideodisabled":
                doPostEditFilmDisable(request, response);
                break;
            case "/videodelete":
                doPostDeleteFilm(request, response);
                break;
            case "/restorevideo":
                doPostRestoreVideo(request, response);
                break;
        }
    }

    //	danh sách video
    protected void doGetViewsVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Video> countVideo = videoService.findAll();
        int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
        request.setAttribute("maxPage", maxPage);

        String pageNumber = request.getParameter("page");

        List<Video> videos;
        if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
            videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currenPage", 1);
        } else {
            videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currenPage", Integer.valueOf(pageNumber));
        }

        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/views/admin/add-video.jsp").forward(request, response);
    }

    //	danh sách ngưng công chiếu
    protected void doGetVideoDisabled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Video> countVideo = videoService.findAllVideoDelete();
        int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
        request.setAttribute("maxPage", maxPage);

        String pageNumber = request.getParameter("page");

        List<Video> videos;
        if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
            videos = videoService.findAllVideoDelete(1, VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currenPage", 1);
        } else {
            videos = videoService.findAllVideoDelete(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currenPage", Integer.valueOf(pageNumber));
        }

        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/views/admin/DataVideoDisabled.jsp").forward(request, response);
    }

    //	thêm phim mới
    protected void doGetNewFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/newFilm.jsp").forward(request, response);
    }

    protected void doPostNewFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String title = request.getParameter("title");
        String href = request.getParameter("href");
        String poster = request.getParameter("poster");
        String daodien = request.getParameter("daodien");
        String dienvien = request.getParameter("dienvien");
        String theloai = request.getParameter("category");
        String mota = request.getParameter("mota");
        String price = request.getParameter("price");
        String noted = request.getParameter("noted");

        if (title != null && href != null && poster != null && daodien != null && dienvien != null && theloai != null && mota != null && noted != null) {
            Video videosHref = videoService.findByHref(href);

            if (videosHref == null) {
                Video videosCreate = videoService.create(title, href, poster, daodien, dienvien, theloai, mota, price, noted);
                if (videosCreate != null) {
                    response.sendRedirect("videoviews");
                    session.setAttribute("addVideoSuccess", true);
                }
            } else {
                response.sendRedirect("videoviews");
                session.setAttribute("addVideoSuccess", false);
            }
        }
    }

    //	chỉnh sửa phim
    protected void doGetEditFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String href = request.getParameter("href");
        Video video = videoService.findByHref(href);

        // format giá tiền
        int pice = video.getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(pice);

        request.setAttribute("formattedPrice", formattedPrice);
        request.setAttribute("video", video);
        request.getRequestDispatcher("/views/admin/EditFilm.jsp").forward(request, response);
    }

    protected void doPostEditFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String href = request.getParameter("href");
        String daodien = request.getParameter("daodien");
        String dienvien = request.getParameter("dienvien");
        String theloai = request.getParameter("category");
        String mota = request.getParameter("mota");
        String price = request.getParameter("price");
        String noted = request.getParameter("noted");

        if (title != null && href != null && daodien != null && dienvien != null && theloai != null && mota != null && noted != null) {

            Video videosUpdate = videoService.update(title, href, daodien, dienvien, theloai, mota, price, noted);
            if (videosUpdate != null) {
                response.sendRedirect("videoviews");
            }
        }
    }

    //	chỉnh sửa phim ngưng hoạt động
    protected void doGetEditFilmDisable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String href = request.getParameter("href");
        Video video = videoService.findByHref(href);

        // format giá tiền
        int pice = video.getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(pice);

        request.setAttribute("formattedPrice", formattedPrice);
        request.setAttribute("video", video);
        request.getRequestDispatcher("/views/admin/EditFilmDisabled.jsp").forward(request, response);
    }

    protected void doPostEditFilmDisable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String href = request.getParameter("href");
        String daodien = request.getParameter("daodien");
        String dienvien = request.getParameter("dienvien");
        String theloai = request.getParameter("category");
        String mota = request.getParameter("mota");
        String price = request.getParameter("price");
        String noted = request.getParameter("noted");

        if (title != null && href != null && daodien != null && dienvien != null && theloai != null && mota != null && noted != null) {

            Video videosUpdate = videoService.updateDisabled(title, href, daodien, dienvien, theloai, mota, price, noted);
            if (videosUpdate != null) {
                response.sendRedirect("videodisabled");
            }
        }
    }

    //	khôi phục video đã xoá
    protected void doPostRestoreVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String href = request.getParameter("href");
        Video video = videoService.RestoreVideo(href);

        if (video != null) {
            response.sendRedirect("videodisabled");
        }
    }

    //	xoá phim
    protected void doPostDeleteFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String href = request.getParameter("href");
        Video videos = videoService.delete(href);

        if (videos != null) {
            response.sendRedirect("videoviews");
        }
    }

}
