package Controll.Service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controll.Dao.UserDao;
import Controll.Dao.Impl.UserDaoImpl;
import Controll.Entity.User;
import Controll.Service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao dao;

	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}

	@Override
	public User findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public User findByPhone(String phone) {
		return dao.findByPhone(phone);
	}

	@Override
	public User findByToken(String token) {
		return dao.findByToken(token);
	}

	@Override
	public User login(String email, String password) {
		return dao.findByEmailAndPassword(email, password);
	}

	@Override
	public User resetPassword(String email, String password) {
		User existUser = findByEmail(email);
		if (existUser != null) {
			existUser.setPassword(password);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public User changePassword(String email, String newPass) {
		User existUser = findByEmail(email);
		if (existUser != null) {
			existUser.setPassword(newPass);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public User editProfile(String email, String fullname, String phone) {
		User existUser = findByEmail(email);
		if (existUser != null) {
			existUser.setName(fullname);
			existUser.setPhone(phone);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public List<User> findByUserId(Integer id) {
		return dao.findByUserId(id);
	}

	@Override
	public List<User> findUserLikedVideoByVideoHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href);
		return dao.findUserLikeByVideoHref(params);
	}

	@Override
	public List<User> UserShareVideoByHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href);
		return dao.UserShareVideoByHref(params);
	}

	@Override
	public List<User> UserPaymentVnpayByHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href);
		return dao.UserPaymentVnpayByHref(params);
	}

	@Override
	public User register(String email, String password, String phone, String fullname, String token) {
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setPhone(phone);
		newUser.setName(fullname);
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsActive(Boolean.FALSE);
		newUser.setToken(token);
		return dao.create(newUser);
	}

	@Override
	public User create(String email, String password, String phone, String fullname) {
		User UserMail = new User();
		UserMail.setEmail(email);
		UserMail.setPassword(password);
		UserMail.setPhone(phone);
		UserMail.setName(fullname);
		UserMail.setIsAdmin(Boolean.FALSE);
		UserMail.setIsActive(Boolean.TRUE);
		return dao.create(UserMail);
	}

	@Override
	public User update(String email, String password, String phone, String fullname, Boolean status) {
		User user = findByEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setName(fullname);
		user.setIsActive(status);
		return dao.update(user);
	}

	@Override
	public User delete(int id) {
		User user = dao.findById(id);
		user.setIsActive(Boolean.FALSE);
		return dao.update(user);
	}

}
