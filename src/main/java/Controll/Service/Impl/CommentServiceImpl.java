package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.CommentDao;
import Controll.Dao.Impl.CommentDaoImpl;
import Controll.Entity.Comment;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.CommentService;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao;

	public CommentServiceImpl() {
		dao = new CommentDaoImpl();
	}

	@Override
	public List<Comment> findByUser(String email) {
		return dao.findByUser(email);
	}

	@Override
	public List<Comment> findByVideoId(Integer videoId) {
		return dao.findByVideoId(videoId);
	}

	@Override
	public Comment findByVideoIdGetUser(Integer videoId) {
		return dao.findByVideoIdGetUser(videoId);
	}

	@Override
	public Comment findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public Comment create(User user, Video video, String comment) {
		Comment cmt = findByUserIdAndVideoId(user.getId(), video.getId());
		if (cmt == null) {
			cmt = new Comment();
			cmt.setUser(user);
			cmt.setVideo(video);
			cmt.setComment(comment);
			cmt.setCommentDate(new Timestamp(System.currentTimeMillis()));
			return dao.create(cmt);

		}
		cmt = new Comment();
		cmt.setUser(user);
		cmt.setVideo(video);
		cmt.setComment(comment);
		cmt.setCommentDate(new Timestamp(System.currentTimeMillis()));
		return dao.create(cmt);
	}

}
