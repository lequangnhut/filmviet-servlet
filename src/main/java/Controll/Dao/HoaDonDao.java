package Controll.Dao;

import java.util.List;

import Controll.Entity.Hoadon;

public interface HoaDonDao {

	List<Hoadon> findAll();
	
	List<Hoadon> findHoadonSuccess();

	List<Hoadon> findByEmail(String email);

	Hoadon findHoaDonByUserIdAndVideoId(int userId, int videoId);

	Hoadon create(Hoadon entity);

	Hoadon remove(Hoadon entity);
}
