package Controll.Service;

import java.util.List;

import Controll.Entity.User;

public interface UserService {

	User findById(Integer id);

	User findByEmail(String email);

	User findByPhone(String phone);

	User findByToken(String token);

	User login(String username, String password);

	User resetPassword(String email, String password);

	User changePassword(String username, String newPass);

	User editProfile(String username, String fullname, String phone);

	List<User> findAll();

	List<User> findAll(int pageNumber, int pageSize);

	List<User> findByUserId(Integer id);

	List<User> findUserLikedVideoByVideoHref(String href);

	List<User> UserShareVideoByHref(String href);

	List<User> UserPaymentVnpayByHref(String href);

	User register(String password, String phone, String fullname, String email, String token);

	User create(String email, String password, String phone, String fullname);

	User update(String email, String password, String phone, String fullname, Boolean status);

	User delete(int id);

}
