package Controll.Dao;

import java.util.List;

import Controll.Entity.Comment;

public interface CommentDao {

	List<Comment> findByUser(String email);

	List<Comment> findByVideoId(Integer videoId);

	Comment findByUserIdAndVideoId(Integer userId, Integer videoId);

	Comment findByVideoIdGetUser(Integer videoId);

	Comment create(Comment entity);

}
