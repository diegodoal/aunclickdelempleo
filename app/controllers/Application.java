package controllers;

import java.util.List;

import com.mongodb.DBObject;

import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import play.mvc.*;

public class Application extends Controller {

    public static Result index() {
        return TODO;
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
    	DBObject query = CompanyUserDataSource.getCompanyUser(email);
    	if(query==null)
    		return badRequest("No existe el usuario con email: "+email);
    	else
    		return ok(query.toString());
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
