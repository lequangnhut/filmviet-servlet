package Controll.Service;

import java.util.List;

import Controll.Entity.Comment;
import Controll.Entity.User;
import Controll.Entity.Video;

public interface CommentService {

	List<Comment> findByUser(String username);

	List<Comment> findByVideoId(Integer videoId);

	Comment findByUserIdAndVideoId(Integer userId, Integer videoId);

	Comment findByVideoIdGetUser(Integer videoId);

	Comment create(User user, Video video, String comment);

}
