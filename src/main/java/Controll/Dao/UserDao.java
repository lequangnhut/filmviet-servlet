package Controll.Dao;

import java.util.List;
import java.util.Map;

import Controll.Entity.User;

public interface UserDao {

	User findById(Integer id);

	User findByEmail(String email);

	User findByPhone(String phone);

	User findByToken(String token);

	User findByEmailAndPassword(String email, String password);

	List<User> findAll();

	List<User> findAll(int pageNumber, int pageSize);

	List<User> findByUserId(Integer id);

	List<User> findUserLikeByVideoHref(Map<String, Object> params);

	List<User> UserShareVideoByHref(Map<String, Object> params);

	List<User> UserPaymentVnpayByHref(Map<String, Object> params);

	User create(User entity);

	User update(User entity);

	User delete(User entity);

}