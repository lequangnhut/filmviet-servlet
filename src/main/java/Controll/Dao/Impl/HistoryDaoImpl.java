package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.HistoryDao;
import Controll.Entity.History;

public class HistoryDaoImpl extends AbstactDao<History> implements HistoryDao {

	@Override
	public List<History> findByUser(String email) {
		String sql = "SELECT o FROM History o WHERE o.user.email = ?0 AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, email);
	}

	@Override
	public List<History> findByUserAndIsLiked(String email) {
		String sql = "SELECT o FROM History o WHERE o.user.email = ?0 AND o.isLiked = 1" + " AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, email);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM History o WHERE o.user.id = ?0 AND o.video.id = ?1 AND o.video.isActive = 1";
		return super.findOne(History.class, sql, userId, videoId);
	}

}
