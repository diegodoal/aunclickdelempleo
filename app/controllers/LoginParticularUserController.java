package controllers;

import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import static play.data.Form.form;

/**
 * Created by Victor on 29/01/2015.
 */
public class LoginParticularUserController extends Controller {

    public static Result blank() {
        return ok(views.html.login_particular_user.login.render());
    }

    public static Result submitLogin() {
        DynamicForm bindedForm = form().bindFromRequest();

        ParticularUser user = ParticularUserDataSource.getParticularUser(bindedForm.get("email"));
        if(user != null && bindedForm.get("password").equals(user.password)) {
            session("email", user.email);
            session("name", user.name);
            return ok(index.render());
        }

        CompanyUser companyUser = CompanyUserDataSource.getCompanyUser(bindedForm.get("email"));
        if(companyUser != null && bindedForm.get("password").equals(companyUser.password)) {
            session("email", companyUser.email);
            session("name", "Company name FAKE");
            return ok(index.render());
        }

        return unauthorized(views.html.login_particular_user.login.render());
    }
    
    // NO FUNCIONA BIEN
    public static Result signUpLogin() {
    	DynamicForm filledForm = form().bindFromRequest();

    	// Check repeated password
    	if(!filledForm.field("password").valueOr("").equals(filledForm.field("verifyPassword").value())) {
    		filledForm.reject("verifyPassword", "La contraseña no coincide.");
    	}
        
    	if(filledForm.hasErrors()) {
            return badRequest(views.html.login_particular_user.login.render());
        } else {
            // Insert new user in the database
            ParticularUser userCreated = ParticularUserDataSource.insertIntoParticularUser(filledForm.get());
            return ok(views.html.login_particular_user.complete_user_profile_1.render(userCreated)); // MIRAR POR QUÉ NO DEJA USAR ESA VISTA
        }
    	
    	return unauthorized(views.html.login_particular_user.login.render());
    }
    
}
