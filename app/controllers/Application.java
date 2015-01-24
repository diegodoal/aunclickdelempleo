package controllers;

import java.util.List;
import com.mongodb.DBObject;

import models.datasource.CompanyUserDataSource;
import models.entities.CompanyUser;
import play.mvc.*;

public class Application extends Controller {

    public static Result index() {
        return TODO;
    }
    
    public static Result newUser(String email, String password){
    	CompanyUser companyUser = new CompanyUser(email, password);
    	CompanyUserDataSource.insertIntoCompanyUser(companyUser);
    	return ok("Usuario: "+email + " con contraseña: "+password+" añadido.");
    }

    public static Result listCompanyUsers(){
    	List<DBObject> all = CompanyUserDataSource.getAllCompanyUsers().toArray();
    	String users = "";
    	
    	for(int i=0; i<all.size(); i++){
    		users+=all.get(i)+"\n";
    	}
    	
    	return ok(users);
    	
    }
}
