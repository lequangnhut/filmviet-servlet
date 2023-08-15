package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.RatingDao;
import Controll.Dao.Impl.RatingDaoImpl;
import Controll.Entity.Rating;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.RatingService;

public class RatingServiceImpl implements RatingService {

	private RatingDao dao;

	public RatingServiceImpl() {
		dao = new RatingDaoImpl();
	}

	@Override
	public List<Rating> findByUser(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public Rating findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public Rating CreateAndUpdate(User user, Video video, int rating) {
		Rating existRating = findByUserIdAndVideoId(user.getId(), video.getId());
		if (existRating != null) {
			existRating.setRating(rating);
			existRating.setRatingDate(new Timestamp(System.currentTimeMillis()));
			dao.update(existRating);
		} else {
			existRating = new Rating();
			existRating.setUser(user);
			existRating.setVideo(video);
			existRating.setRating(rating);
			existRating.setRatingDate(new Timestamp(System.currentTimeMillis()));
			dao.create(existRating);
		}
		return existRating;
	}

}
