package Controll.Dao.Impl;

import java.util.List;
import java.util.Map;

import Controll.Contanst.NamedStored;
import Controll.Dao.AbstactDao;
import Controll.Dao.UserDao;
import Controll.Entity.User;

public class UserDaoImpl extends AbstactDao<User> implements UserDao {

	@Override
	public User findById(Integer id) {
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByPhone(String phone) {
		String sql = "SELECT o FROM User o WHERE o.phone = ?0";
		return super.findOne(User.class, sql, phone);
	}

	@Override
	public User findByToken(String token) {
		String sql = "SELECT o FROM User o WHERE o.token = ?0";
		return super.findOne(User.class, sql, token);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		String sql = "SELECT o FROM User o WHERE o.email = ?0 AND o.password = ?1";
		return super.findOne(User.class, sql, email, password);
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT o FROM User o ";
		return super.findMany(User.class, sql);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return super.findAllUser(User.class, true, pageNumber, pageSize);
	}

	@Override
	public List<User> findByUserId(Integer id) {
		String sql = "SELECT o FROM User o WHERE o.id = ?0";
		return super.findMany(User.class, sql, id);
	}

	@Override
	public List<User> findUserLikeByVideoHref(Map<String, Object> params) {
		return super.callStored(NamedStored.STORED_FIND_USER_LIKED_VIDEO_BY_VIDEO_HREF, params);
	}

	@Override
	public List<User> UserShareVideoByHref(Map<String, Object> params) {
		return super.callStored(NamedStored.STORED_FIND_USER_SHARE_VIDEO_BY_HREF, params);
	}

	@Override
	public List<User> UserPaymentVnpayByHref(Map<String, Object> params) {
		return super.callStored(NamedStored.STORED_FIND_USER_PAYMENT_VNPAY_BY_HREF, params);
	}

}
