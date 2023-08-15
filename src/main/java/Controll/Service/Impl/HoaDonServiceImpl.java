package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.HoaDonDao;
import Controll.Dao.Impl.HoaDonImpl;
import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.HoaDonService;

public class HoaDonServiceImpl implements HoaDonService {

	HoaDonDao dao;

	public HoaDonServiceImpl() {
		dao = new HoaDonImpl();
	}

	@Override
	public List<Hoadon> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Hoadon> findHoadonSuccess() {
		return dao.findHoadonSuccess();
	}

	@Override
	public List<Hoadon> findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public Hoadon findHoaDonByUserIdAndVideoId(int userId, int videoId) {
		return dao.findHoaDonByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public Hoadon create(String Vnp_TxnRef, Video video, User user, String Vnp_OrderInfo, String Vnp_ResponseCode,
			String Vnp_TransactionNo, String Vnp_BankCode, String vnp_Amount) {
		Hoadon hoadon = new Hoadon();
		hoadon.setVnp_TxnRef(Vnp_TxnRef);
		hoadon.setVideo(video);
		hoadon.setUser(user);
		hoadon.setVnp_OrderInfo(Vnp_OrderInfo);
		hoadon.setVnp_ResponseCode(Vnp_ResponseCode);
		hoadon.setVnp_TransactionNo(Vnp_TransactionNo);
		hoadon.setVnp_BankCode(Vnp_BankCode);
		hoadon.setVnp_Amount(vnp_Amount);
		hoadon.setVnp_PayDate(new Timestamp(System.currentTimeMillis()));
		return dao.create(hoadon);
	}

	@Override
	public Hoadon remove(int userId, int videoId) {
		Hoadon hoadon = findHoaDonByUserIdAndVideoId(userId, videoId);
		return dao.remove(hoadon);
	}

}
