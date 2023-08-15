package Controll.Util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
	public static String formatVND(double amount) {
		NumberFormat format = NumberFormat.getInstance(Locale.forLanguageTag("vi-VN"));
		return format.format(amount) + " VND";
	}
}