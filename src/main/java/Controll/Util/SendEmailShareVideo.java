package Controll.Util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SendEmailShareVideo {

	public static String content(String fullname, String title, String href) {
		String content = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>TRAVEL TOUR</title>\r\n"
				+ "    <link rel=\"icon\" href=\"image/favicon.png\" type=\"icon/x-icon\">\r\n" + "</head>\r\n" + "\r\n"
				+ "<body style=\" font-family: Raleway;\r\n" + "background-color: #d8dada;\r\n" + "font-size: 19px;\r\n"
				+ "max-width: 800px;\r\n" + "margin: 0 auto;\r\n" + "padding: 3%;                       \">\r\n"
				+ "\r\n" + "    <head>\r\n" + "        <div id=\"wrapper\" style=\"background-color: #f0f6fb;\">\r\n"
				+ "            <header style=\"width: 98%;\">\r\n"
				+ "                <div id=\"logo\" style=\"max-width: 80px;\r\n"
				+ "                margin: 3% 0 3% 3%;\r\n" + "                float: left;\">\r\n"
				+ "                </div>\r\n" + "                <div>\r\n" + "\r\n" + "            </header>\r\n"
				+ "            <hr style=\"height: 1px;\r\n" + "            background-color: #303840;\r\n"
				+ "            clear: both;\r\n" + "            width: 96%;\r\n" + "            margin: auto;\">\r\n"
				+ "            <div id=\"banner\" style=\"margin: 3%;\">\r\n"
				+ "                <div class=\"one-col\">\r\n"
				+ "                    <h1 style=\"margin: 3%;\"> Xin Chào Bạn !  </h1>\r\n" + "\r\n"
				+ "                    <p style=\"margin: 3%;\">\r\n" + "                        Thân gửi Bạn "
				+ " !<br><br>\r\n" + "\r\n" + "                        Bạn <Strong>" + fullname
				+ "</Strong> đã chia sẽ với bạn bộ phim <Strong>" + title
				+ "</Strong> trên web <Strong>FilmViet</Strong> chúc bạn xem phim vui vẻ !<br><br>\r\n" + "\r\n"
				+ "\r\n"
				+ "                        Nhấp vào đây để xem phim: <Strong><a href='http://localhost:8080/BackEnd/video?action=details&id="
				+ href + "'>Tại đây</a></Strong> \r\n" + "                       <br>\r\n" + "\r\n"
				+ "<br>Trân trọng !<br>\r\n" + "\r\n"
				+ "                        Chi tiết xin liên hệ hotline để được tư vấn thêm ! <br><br>\r\n"
				+ "                        HotLine: 0967363343\r\n" + "                    </p>\r\n"
				+ "                    <hr style=\"height: 1px;\r\n"
				+ "                    background-color: #303840;\r\n" + "                    clear: both;\r\n"
				+ "                    width: 96%;\r\n" + "                    margin: auto;\">\r\n"
				+ "                    <footer>\r\n"
				+ "                        <p id=\"contact\" style=\"text-align: center;\r\n"
				+ "                        padding-bottom: 3%;\r\n" + "                        line-height: 16px;\r\n"
				+ "                        font-size: 12px;\r\n" + "                        color: #303840;\">\r\n"
				+ "                            Địa chỉ: 888 Nguyễn Văn Linh, phường An Khánh, quận Ninh Kiều Cần Thơ <br>\r\n"
				+ "                            Điện thoại: 0967-363-343 <br>\r\n"
				+ "                            Lịch hẹn: nhut.thanthien17@gmail.com <br>\r\n"
				+ "                            Gmail: nhut.thanthien17@gmail.com\r\n"
				+ "                        </p>\r\n" + "                    </footer>\r\n"
				+ "                </div>\r\n" + "            </div>\r\n" + "    </head>\r\n" + "</body>\r\n" + "\r\n"
				+ "</html>";
		return content;
	}

	public static void SendMail(HttpSession mySession, HttpServletRequest request, HttpServletResponse response,
			String email, String fullname, String titleVideo, String href, String tileMail)
			throws ServletException, IOException {

		mySession = request.getSession();

		if (email != null || email != "") {
			String to = email;
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("nhut.thanthien17@gmail.com", "vbnkpfdgibraeqek");
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(tileMail);
				message.setContent(content(fullname, titleVideo, href), "text/html; charset=utf-8");
				Transport.send(message);
				System.out.println("send OTP success");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			mySession.setAttribute("email", email);
		}
	}
}
