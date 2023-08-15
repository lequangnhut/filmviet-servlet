package Controll.Dao;

import java.util.List;

import Controll.Entity.Share;

public interface ShareDao {

	List<Share> findByUser(String username);
	
	Share findByUserIdAndVideoId(Integer userId, Integer videoId);

	Share create(Share entity);

}
