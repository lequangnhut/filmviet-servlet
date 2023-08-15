package Controll.Util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
	// Mã hóa mật khẩu và trả về chuỗi đã được mã hóa
	public static String hashPassword(String plainPassword) {
		String salt = BCrypt.gensalt(); // Tạo muối ngẫu nhiên
		return BCrypt.hashpw(plainPassword, salt); // Mã hóa mật khẩu
	}

	// Kiểm tra tính đúng đắn của mật khẩu
	public static boolean checkPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword); // So sánh mật khẩu
	}

}
