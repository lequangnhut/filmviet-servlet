package Controll.Service;

import java.util.List;

import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Entity.Video;

public interface HoaDonService {

	List<Hoadon> findAll();

	List<Hoadon> findHoadonSuccess();

	List<Hoadon> findByEmail(String email);

	Hoadon findHoaDonByUserIdAndVideoId(int userId, int videoId);

	Hoadon create(String Vnp_TxnRef, Video video, User user, String Vnp_OrderInfo, String Vnp_ResponseCode,
			String Vnp_TransactionNo, String Vnp_BankCode, String vnp_Amount);

	Hoadon remove(int userId, int videoId);
}
