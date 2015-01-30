package controllers;

import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import models.entities.User;
import play.mvc.*;
import utils.EmailUtil;
import views.html.index;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class Application extends Controller {

	public static Result index() {
        User user = new User("victor.gzarco@gmail.com", "123456789");
		return ok(index.render(user));
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
	
	/**
	 * This method sends an email to the new registered PARTICULAR USER with the URL for validate its account
	 * @param toEmail Email of the new registered user
	 * @return A controller action
	 */
	public static Result sendParticularValidationEmail(String toEmail){
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
		String message = "Para activar tu cuenta, haz click en el siguiente enlace: http://localhost:9000/particular/verify";
		
		ParticularUser query = ParticularUserDataSource.getParticularUser(toEmail);
		if(query == null){
			return badRequest("Error, no se ha podido encontrar el usuario");
		}else if(query.emailVerificationKey == null){
			return badRequest("El usuario ya ha sido validado, no es necesario enviar este mensaje");
		}else if(query != null){
			message += "/"+query.email+"/"+query.emailVerificationKey;
		}
		
		EmailUtil.sendEmail(session, toEmail,"Confirmación de cuenta creada en www.aunclickdelempleo.com", message);
		return ok("Se ha enviado un email a: " + toEmail+" .Por favor, revise su correo");
	}  
	
	/**
	 * This method sends an email to the new registered COMPANY USER with the URL for validate its account
	 * @param toEmail Email of the new registered user
	 * @return A controller action
	 */
	public static Result sendCompanyValidationEmail(String toEmail){
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
		String message = "Para activar tu cuenta, haz click en el siguiente enlace: http://localhost:9000/company/verify";
		
		CompanyUser query = CompanyUserDataSource.getCompanyUser(toEmail);
		if(query == null){
			return badRequest("Error, no se ha podido encontrar el usuario");
		}else if(query.emailVerificationKey == null){
			return badRequest("El usuario ya ha sido validado, no es necesario enviar este mensaje");
		}else if(query != null){
			message += "/"+query.email+"/"+query.emailVerificationKey;
		}
		
		EmailUtil.sendEmail(session, toEmail,"Confirmación de cuenta creada en www.aunclickdelempleo.com", message);
		return ok("Se ha enviado un email a: " + toEmail+" .Por favor, revise su correo");
	}  
}
