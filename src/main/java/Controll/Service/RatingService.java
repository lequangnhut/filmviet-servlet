package Controll.Service;

import java.util.List;

import Controll.Entity.Rating;
import Controll.Entity.User;
import Controll.Entity.Video;

public interface RatingService {

	List<Rating> findByUser(String username);

	Rating findByUserIdAndVideoId(Integer userId, Integer videoId);

	Rating CreateAndUpdate(User user, Video video, int rating);
	
}
