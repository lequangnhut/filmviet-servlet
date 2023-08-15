package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.HistoryDao;
import Controll.Dao.Impl.HistoryDaoImpl;
import Controll.Entity.History;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.HistoryService;
import Controll.Service.VideoService;

public class HistoryServiceImpl implements HistoryService {

	private HistoryDao dao;
	private VideoService videoService = new VideoServiceImpl();

	public HistoryServiceImpl() {
		dao = new HistoryDaoImpl();
	}

	@Override
	public List<History> findByUser(String email) {
		return dao.findByUser(email);
	}

	@Override
	public List<History> findByUserAndIsLiked(String email) {
		return dao.findByUserAndIsLiked(email);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if (existHistory == null) {
			existHistory = new History();
			existHistory.setUser(user);
			existHistory.setVideo(video);
			existHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
			existHistory.setIsLiked(Boolean.FALSE);
			dao.create(existHistory);
		}
		return existHistory;
	}

	@Override
	public Boolean updateLikeOrUnLike(User user, String videoHref) {
		Video video = videoService.findByHref(videoHref);
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());

		if (existHistory.getIsLiked() == Boolean.FALSE) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikedDate(new Timestamp(System.currentTimeMillis()));
		} else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setLikedDate(null);
		}
		History updateHistory = dao.update(existHistory);
		return updateHistory != null ? true : false;
	}

}
