package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.ShareDao;
import Controll.Entity.Share;

public class ShareDaoImpl extends AbstactDao<Share> implements ShareDao {

	@Override
	public List<Share> findByUser(String email) {
		String sql = "SELECT o FROM Share o WHERE o.user.email = ?0 AND o.video.isActive = 1 ORDER BY o.shareDate DESC";
		return super.findMany(Share.class, sql, email);
	}

	@Override
	public Share findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM Share o WHERE o.user.id = ?0 AND o.video.id = ?1 AND o.video.isActive = 1";
		return super.findOne(Share.class, sql, userId, videoId);
	}

}
