package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.HoaDonDao;
import Controll.Entity.Hoadon;

public class HoaDonImpl extends AbstactDao<Hoadon> implements HoaDonDao {

	@Override
	public List<Hoadon> findAll() {
		String jpql = "SELECT h FROM Hoadon h ORDER BY vnp_PayDate DESC";
		return super.findManyMaxResult(Hoadon.class, jpql, 5);
	}

	@Override
	public List<Hoadon> findHoadonSuccess() {
		String jpql = "SELECT h FROM Hoadon h WHERE h.vnp_ResponseCode = '00' ORDER BY vnp_PayDate DESC";
		return super.findMany(Hoadon.class, jpql);
	}

	@Override
	public List<Hoadon> findByEmail(String email) {
		String jpql = "SELECT o FROM Hoadon o WHERE o.user.email = ?0";
		return super.findMany(Hoadon.class, jpql, email);
	}

	@Override
	public Hoadon findHoaDonByUserIdAndVideoId(int userId, int videoId) {
		String jpql = "SELECT o FROM Hoadon o WHERE o.user.id = ?0 AND o.video.id = ?1";
		return super.findOne(Hoadon.class, jpql, userId, videoId);
	}

	@Override
	public Hoadon remove(Hoadon entity) {
		return super.delete(entity);
	}

}
