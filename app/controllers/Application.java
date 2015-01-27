package controllers;

import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import play.mvc.*;
import utils.EmailUtil;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class Application extends Controller {

	public static Result index() {
		return TODO;
	}


	/* EMAIL
	 * Outgoing Mail (SMTP) Server
	 * requires TLS or SSL: smtp.gmail.com (use authentication)
	 * Use Authentication: Yes
	 * Port for TLS/STARTTLS: 587
	 */
	public static Result sendEmail(String toEmail){
		final String fromEmail = "aunclickdelempleo@gmail.com"; //requires valid gmail id
		final String password = "webadecco"; // correct password for gmail id
		// Por ejemplo poner toEmail = "useradecco@gmail.com" // can be any email id 

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		// Cambiar el mensaje del título del mail y el cuerpo del mensaje
		EmailUtil.sendEmail(session, toEmail,"Confirmación de cuenta creada en www.aunclickdelempleo.com", "Su cuenta " + toEmail + " se ha creado con éxito");
		return ok("Se ha enviado un email a: " + toEmail);
	}   
}
