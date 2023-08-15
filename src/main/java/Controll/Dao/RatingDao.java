package Controll.Dao;

import java.util.List;

import Controll.Entity.Rating;

public interface RatingDao {

	List<Rating> findByEmail(String email);
	
	Rating findByUserIdAndVideoId(Integer userId, Integer videoId);

	Rating create(Rating entity);

	Rating update(Rating entity);

}
