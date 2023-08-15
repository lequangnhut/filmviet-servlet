package Controll.Service;

import Controll.Entity.User;
import jakarta.servlet.ServletContext;

public interface EmailService {
	
	public void SendEmail(ServletContext context, User recipient, String fullname, String token);
	
}
