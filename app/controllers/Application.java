package controllers;

import models.datasource.CompanyUserDataSource;
import models.entities.CompanyUser;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return TODO;
    }
    
    public static Result newUser(String email, String password){
    	CompanyUser companyUser = new CompanyUser(email, password);
    	CompanyUserDataSource.insertIntoCompanyUser(companyUser);
    	return ok("Usuario: "+email + " con contraseña: "+password+" añadido.");
    }

}
