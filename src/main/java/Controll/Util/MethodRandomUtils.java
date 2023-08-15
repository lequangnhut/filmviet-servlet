package Controll.Util;

import java.security.SecureRandom;
import java.util.Random;

public class MethodRandomUtils {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String DIGITS = "0123456789";
	private static final String[] FIRST_NAMES = { "Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Vũ", "Đặng", "Bùi",
			"Đỗ", "Hồ", "Ngô" };
	private static final String[] LAST_NAMES = { "Huy", "Nam", "Linh", "Trang", "Bình", "Anh", "Thủy", "Mai", "Đức",
			"Thành", "Quỳnh", "Tùng" };
	private static final String[] AREA_CODES = { "9", "8", "7", "3" };
	private static final SecureRandom random = new SecureRandom();

	public static String RandomToken(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public static String RandomPhoneNumber() {
		Random random = new Random();
		String areaCode = AREA_CODES[random.nextInt(AREA_CODES.length)];
		StringBuilder localNumber = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			localNumber.append(random.nextInt(10));
		}
		String phoneNumber = "0" + areaCode + localNumber.toString();
		return phoneNumber;
	}

	public static int RandomOtpValue(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(DIGITS.length());
			char randomDigit = DIGITS.charAt(randomIndex);
			sb.append(randomDigit);
		}

		String otpString = sb.toString();
		int otpValue = Integer.parseInt(otpString);

		return otpValue;
	}

	public static String RandomFullname() {
		String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
		String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
		return firstName + " " + lastName;
	}
}
