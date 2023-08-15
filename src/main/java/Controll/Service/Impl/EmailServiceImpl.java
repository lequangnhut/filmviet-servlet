package Controll.Service.Impl;

import Controll.Entity.User;
import Controll.Service.EmailService;
import Controll.Util.SendEmailRegisterUtil;
import jakarta.servlet.ServletContext;

public class EmailServiceImpl implements EmailService {

	public static final String EMAIL_WELCOME_SUBJECT = "FILMVIET - CHÀO MỪNG BẠN ĐÃ THAM GIA BLOG XEM PHIM VIỆT";
	public static final String EMAIL_FORGOT_PASSWORD = "FILMVIET - ĐỔI MẬT KHẨU";

	@Override
	public void SendEmail(ServletContext context, User recipient, String fullname, String token) {
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");

		try {
			String subject = "FILMVIET - CẢM ƠN BẠN ĐÃ ĐĂNG KÝ !";
			String message = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>TRAVEL TOUR</title>\r\n"
					+ "    <link rel=\"icon\" href=\"image/favicon.png\" type=\"icon/x-icon\">\r\n" + "</head>\r\n"
					+ "\r\n" + "<body style=\" font-family: Raleway;\r\n" + "background-color: #d8dada;\r\n"
					+ "font-size: 19px;\r\n" + "max-width: 800px;\r\n" + "margin: 0 auto;\r\n"
					+ "padding: 3%;                       \">\r\n" + "\r\n" + "    <head>\r\n"
					+ "        <div id=\"wrapper\" style=\"background-color: #f0f6fb;\">\r\n"
					+ "            <header style=\"width: 98%;\">\r\n"
					+ "                <div id=\"logo\" style=\"max-width: 80px;\r\n"
					+ "                margin: 3% 0 3% 3%;\r\n" + "                float: left;\">\r\n"
					+ "                </div>\r\n" + "                <div>\r\n" + "\r\n" + "            </header>\r\n"
					+ "            <hr style=\"height: 1px;\r\n" + "            background-color: #303840;\r\n"
					+ "            clear: both;\r\n" + "            width: 96%;\r\n"
					+ "            margin: auto;\">\r\n" + "            <div id=\"banner\" style=\"margin: 3%;\">\r\n"
					+ "                <div class=\"one-col\">\r\n"
					+ "                    <h1 style=\"margin: 3%;\"> Xin Chào " + fullname + " !  </h1>\r\n" + "\r\n"
					+ "                    <p style=\"margin: 3%;\">\r\n" + "                        Thân gửi "
					+ fullname + " !,<br><br>\r\n" + "\r\n"
					+ "                        Cảm ơn bạn đã luôn tin tưởng và sử dụng ứng dụng của chúng tôi!<br><br>\r\n"
					+ "\r\n" + "\r\n"
					+ "                        Bạn có thể chỉnh sửa thông tin cá nhân của mình <a href=\"http://localhost:8080/BackEnd/profile\">tại đây !</a> sau khi tài khoản được kích hoạt\r\n"
					+ "                       <br>\r\n" + "\r\n" + "<br>Trân trọng !<br>\r\n" + "\r\n"
					+ "                        Chi tiết xin liên hệ hotline để được tư vấn thêm ! <br><br>\r\n"
					+ "                        HotLine: 0967363343\r\n" + "                    </p>\r\n"
					+ "                    <a href=\"http://localhost:8080/BackEnd/verifyemail?token=" + token
					+ "\" class=\"btn\" style=\"float: right;\r\n" + "                    margin: 0 2% 4% 0;\r\n"
					+ "                    background-color: #303840;\r\n" + "                    color: #f6faff;\r\n"
					+ "                    text-decoration: none;\r\n" + "                    font-weight: 800;\r\n"
					+ "                    padding: 8px 12px;\r\n" + "                    border-radius: 8px;\r\n"
					+ "                    letter-spacing: 2px;\">Kích Hoạt Tài Khoản</a>\r\n"
					+ "                    <hr style=\"height: 1px;\r\n"
					+ "                    background-color: #303840;\r\n" + "                    clear: both;\r\n"
					+ "                    width: 96%;\r\n" + "                    margin: auto;\">\r\n"
					+ "                    <footer>\r\n"
					+ "                        <p id=\"contact\" style=\"text-align: center;\r\n"
					+ "                        padding-bottom: 3%;\r\n"
					+ "                        line-height: 16px;\r\n" + "                        font-size: 12px;\r\n"
					+ "                        color: #303840;\">\r\n"
					+ "                            Địa chỉ: 888 Nguyễn Văn Linh, phường An Khánh, quận Ninh Kiều Cần Thơ <br>\r\n"
					+ "                            Điện thoại: 0967-363-343 <br>\r\n"
					+ "                            Lịch hẹn: nhut.thanthien17@gmail.com <br>\r\n"
					+ "                            Gmail: nhut.thanthien17@gmail.com\r\n"
					+ "                        </p>\r\n" + "                    </footer>\r\n"
					+ "                </div>\r\n" + "            </div>\r\n" + "    </head>\r\n" + "</body>\r\n"
					+ "\r\n" + "</html>";

			SendEmailRegisterUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, message);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
