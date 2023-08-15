package Controll.Service;

import java.util.List;

import Controll.Entity.History;
import Controll.Entity.User;
import Controll.Entity.Video;

public interface HistoryService {

	List<History> findByUser(String username);

	List<History> findByUserAndIsLiked(String username);

	History findByUserIdAndVideoId(Integer userId, Integer videoId);

	History create(User user, Video video);

	Boolean updateLikeOrUnLike(User userId, String videoHref);

}
