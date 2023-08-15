package Controll.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import Controll.Contanst.SessionAtrr;
import Controll.Entity.Comment;
import Controll.Entity.History;
import Controll.Entity.Hoadon;
import Controll.Entity.Rating;
import Controll.Entity.Share;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.CommentService;
import Controll.Service.HistoryService;
import Controll.Service.HoaDonService;
import Controll.Service.RatingService;
import Controll.Service.ShareService;
import Controll.Service.UserService;
import Controll.Service.VideoService;
import Controll.Service.Impl.CommentServiceImpl;
import Controll.Service.Impl.HistoryServiceImpl;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.RatingServiceImpl;
import Controll.Service.Impl.ShareServiceImpl;
import Controll.Service.Impl.UserServiceImpl;
import Controll.Service.Impl.VideoServiceImpl;
import Controll.Util.SendEmailShareVideo;

@WebServlet({"/video", "/share", "/rating", "/comment"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();
    private ShareService shareService = new ShareServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();
    private CommentService commentService = new CommentServiceImpl();
    private UserService userService = new UserServiceImpl();
    private HoaDonService hoadonService = new HoaDonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        String href = request.getParameter("id");
        HttpSession session = request.getSession();

        switch (actionParam) {
            case "details":
                doGetDetails(session, href, request, response);
                break;
            case "like":
                doGetLike(session, href, request, response);
                break;
            case "watch":
                doGetWatch(session, href, request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/share":
                doPostShare(request, response);
                break;
            case "/rating":
                doPostRating(request, response);
                break;
            case "/comment":
                doPostComment(request, response);
                break;
        }
    }

    protected void doGetDetails(HttpSession session, String href, HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        Video video = videoService.findByHref(href);
        request.setAttribute("video", video);

        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        if (user != null) {
            Hoadon hoadon = hoadonService.findHoaDonByUserIdAndVideoId(user.getId(), video.getId());
            if (hoadon != null) {
                request.setAttribute("ResponseCode", hoadon);
            }
        }

        List<Comment> cmt = commentService.findByVideoId(video.getId());
        request.setAttribute("comment", cmt);

        LocalDateTime now = LocalDateTime.now();

        for (Comment comment : cmt) {
            Timestamp commentTimestamp = comment.getCommentDate();
            LocalDateTime commentTime = commentTimestamp.toLocalDateTime();
            Duration duration = Duration.between(commentTime, now);

            long hours = duration.toHours();
            long seconds = duration.getSeconds();

            String timeAgo;

            if (seconds < 60) {
                timeAgo = seconds + " giây trước";
            } else {
                if (hours < 1) {
                    long minutes = duration.toMinutes();
                    timeAgo = minutes + " phút trước";
                } else if (hours < 24) {
                    timeAgo = hours + " giờ trước";
                } else {
                    long days = hours / 24;
                    timeAgo = days + " ngày trước";
                }
            }
            request.setAttribute("timeAgo", timeAgo);
        }

        User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        if (currentUser != null) {
            Rating rating = ratingService.findByUserIdAndVideoId(currentUser.getId(), video.getId());

            if (rating != null) {
                int ratingFromDatabase = rating.getRating();
                String checkedAttribute5 = (ratingFromDatabase == 5) ? "checked" : "";
                String checkedAttribute4 = (ratingFromDatabase == 4) ? "checked" : "";
                String checkedAttribute3 = (ratingFromDatabase == 3) ? "checked" : "";
                String checkedAttribute2 = (ratingFromDatabase == 2) ? "checked" : "";
                String checkedAttribute1 = (ratingFromDatabase == 1) ? "checked" : "";

                request.setAttribute("checkedAttribute5", checkedAttribute5);
                request.setAttribute("checkedAttribute4", checkedAttribute4);
                request.setAttribute("checkedAttribute3", checkedAttribute3);
                request.setAttribute("checkedAttribute2", checkedAttribute2);
                request.setAttribute("checkedAttribute1", checkedAttribute1);
            } else {
                request.setAttribute("checkedAttribute5", "");
                request.setAttribute("checkedAttribute4", "");
                request.setAttribute("checkedAttribute3", "");
                request.setAttribute("checkedAttribute2", "");
                request.setAttribute("checkedAttribute1", "");
            }

            History history = historyService.create(currentUser, video);
            request.setAttribute("flagLikeButton", history.getIsLiked());
        }

        request.getRequestDispatcher("/views/user/details.jsp").forward(request, response);
    }

    protected void doGetLike(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        boolean result = historyService.updateLikeOrUnLike(currentUser, href);

        if (result == true) {
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }
    }

    protected void doGetWatch(HttpSession session, String href, HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {

        Video video = videoService.findByHref(href);
        request.setAttribute("video", video);

        List<Comment> cmt = commentService.findByVideoId(video.getId());
        request.setAttribute("comment", cmt);

        LocalDateTime now = LocalDateTime.now();

        for (Comment comment : cmt) {

            Timestamp commentTimestamp = comment.getCommentDate();
            LocalDateTime commentTime = commentTimestamp.toLocalDateTime();
            Duration duration = Duration.between(commentTime, now);

            long hours = duration.toHours();
            long seconds = duration.getSeconds();

            String timeAgo;

            if (seconds < 60) {
                timeAgo = seconds + " giây trước";
            } else {
                if (hours < 1) {
                    long minutes = duration.toMinutes();
                    timeAgo = minutes + " phút trước";
                } else if (hours < 24) {
                    timeAgo = hours + " giờ trước";
                } else {
                    long days = hours / 24;
                    timeAgo = days + " ngày trước";
                }
            }
            request.setAttribute("timeAgo", timeAgo);
        }

        Comment commentUser = commentService.findByVideoIdGetUser(video.getId());
        if (commentUser != null) {
            List<User> user = userService.findByUserId(commentUser.getUser().getId());
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("/views/user/watching.jsp").forward(request, response);
    }

    protected void doPostShare(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        String fullname = user.getName();

        String videoTitle = request.getParameter("title");
        String email = request.getParameter("email");
        String noted = request.getParameter("noted");
        String href = request.getParameter("href");
        String note = request.getParameter("noted");

        if (email != null && noted != null) {
            Video video = videoService.findByHref(href);
            Share share = shareService.create(user, video, email);
            if (share != null) {
                SendEmailShareVideo.SendMail(session, request, response, email, fullname, videoTitle, href, note);
                session.setAttribute("sendMailSuccess", true);
                response.sendRedirect("video?action=details&id=" + href);
            }
        }
    }

    protected void doPostRating(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String videoId = request.getParameter("href");
        Video video = videoService.findByHref(videoId);
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        if (videoId != null && user != null && video != null) {
            int rating = Integer.parseInt(request.getParameter("rating"));
            Rating ratingFormDB = ratingService.findByUserIdAndVideoId(user.getId(), video.getId());
            System.out.println("do day chua");
            if (ratingFormDB != null) {
                ratingFormDB.setRating(rating);
                ratingService.CreateAndUpdate(user, video, rating);
            } else {
                ratingFormDB = ratingService.CreateAndUpdate(user, video, rating);
            }
            if (ratingFormDB != null) {
                response.sendRedirect("video?action=details&id=" + videoId);
            }
        }
    }

    protected void doPostComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String comment = request.getParameter("comment");
        String videoId = request.getParameter("href");
        Video video = videoService.findByHref(videoId);
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

        Comment cmt = commentService.create(user, video, comment);
        if (cmt != null) {
            response.sendRedirect("video?action=details&id=" + videoId);
        }
    }

}
