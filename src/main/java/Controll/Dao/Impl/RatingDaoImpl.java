package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.RatingDao;
import Controll.Entity.Rating;

public class RatingDaoImpl extends AbstactDao<Rating> implements RatingDao {

	@Override
	public List<Rating> findByEmail(String email) {
		String sql = "SELECT o FROM Rating o WHERE o.user.email = ?0 AND o.video.isActive = 1 ORDER BY o.ratingDate DESC";
		return super.findMany(Rating.class, sql, email);
	}

	@Override
	public Rating findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM Rating o WHERE o.user.id = ?0 AND o.video.id = ?1 AND o.video.isActive = 1";
		return super.findOne(Rating.class, sql, userId, videoId);
	}

}
