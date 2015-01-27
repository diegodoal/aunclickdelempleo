package controllers;

import java.util.List;

import com.mongodb.DBObject;

import email.EmailUtil;
import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import play.mvc.*;

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
    
    // COMPANY USERS
    public static Result newCompanyUser(String email, String password){
    	CompanyUser companyUser = new CompanyUser(email, password);
    	CompanyUserDataSource.insertIntoCompanyUser(companyUser);
    	return ok("Usuario: "+email + " con contraseña: "+password+" añadido.");
    }

    public static Result listCompanyUsers(){
    	List<DBObject> all = CompanyUserDataSource.getAllCompanyUsers();
    	
    	String users = "";
    	
    	for(int i=0; i<all.size(); i++){
    		users+=all.get(i)+"\n";
    	}
    	
    	return ok(users);
    }
    
    public static Result findCompanyUser(String email){
    	CompanyUser query = CompanyUserDataSource.getCompanyUser(email);
    	if(query==null)
    		return badRequest("No existe el usuario con email: "+email);
    	else
    		return ok(query.showUserInfo());
    }
    
    public static Result initializeCompanyUserDB(){
    	CompanyUserDataSource.initializeCompanyUsersDB();
    	return ok("Base de datos CompanyUser inicializada correctamente");
    }
    
    // PARTICULAR USERS
    public static Result newParticularUser(String email, String password){
    	ParticularUser particularUser = new ParticularUser(email, password);
    	ParticularUserDataSource.insertIntoParticularUser(particularUser);
    	return ok("Usuario: "+email + " con contraseña: "+password+" añadido.");
    }

    public static Result listParticularUsers(){
    	List<DBObject> all = ParticularUserDataSource.getAllParticularUsers();
    	String users = "";
    	for(int i=0; i<all.size(); i++){
    		users+=all.get(i)+"\n";
    	}
    	return ok(users);
    }
    
    public static Result findParticularUser(String email){
    	DBObject query = ParticularUserDataSource.getParticularUser(email);
    	if(query==null)
    		return badRequest("No existe el usuario con email: "+email);
    	else
    		return ok(query.toString());
    }
    
    public static Result initializeParticularUserDB(){
    	ParticularUserDataSource.initializeParticularUsersDB();
    	return ok("Base de datos ParticularUser inicializada correctamente");
    }
}
