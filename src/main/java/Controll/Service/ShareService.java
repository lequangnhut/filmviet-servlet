package Controll.Service;

import java.util.List;

import Controll.Entity.Share;
import Controll.Entity.User;
import Controll.Entity.Video;

public interface ShareService {

	List<Share> findByUser(String username);

	Share findByUserIdAndVideoId(Integer userId, Integer videoId);

	Share create(User user, Video video, String email);

}
