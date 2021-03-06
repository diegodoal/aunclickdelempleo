package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.util.JSON;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.data.Form;
import play.mvc.*;
import utils.EmailUtil;
import utils.Utils;

import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class Application extends Controller {

	public static Result index() {
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user==null || !Utils.checkEqualTimestamps(session().get("timestamp"), user.connectionTimestamp)){
            session().clear();
        }

        return ok(views.html.index.render());
	}

    public static Result logout(){
        session().clear();
        return ok(views.html.index.render());
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
		
		User query = SingletonDataSource.getInstance().getUserByEmail(toEmail);
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
