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
import views.html.*;
import views.html.complete_user_profile.*;
import static play.data.Form.form;

/**
 * Created by Unknown on 29/01/2015.
 */
public class LoginParticularUserController extends Controller {

    public static Result blank() {
    	/* Primer parámetro es para error_login_msg
    	 * Segundo parámetro es para error_signup_msg */
        return ok(views.html.login_particular_user.login.render(null, null)); 
    }

    public static Result submitLogin() {
    	String error_login_msg = null; // Para saber en la vista del login cuando hay error
        DynamicForm bindedForm = form().bindFromRequest();

        ParticularUser user = ParticularUserDataSource.getParticularUser(bindedForm.get("email"));
        if(user != null && bindedForm.get("password").equals(user.password)) {
            session("email", user.email);
            session("name", user.name);
            return redirect("/");
        }

        CompanyUser companyUser = CompanyUserDataSource.getCompanyUser(bindedForm.get("email"));
        if(companyUser != null && bindedForm.get("password").equals(companyUser.password)) {
            session("email", companyUser.email);
            session("name", "Company name FAKE");
            return redirect("/");
        }
        
        error_login_msg = "Email o contraseña incorrecta";
        return badRequest(views.html.login_particular_user.login.render(error_login_msg, null));
    }

    public static Result signUpLogin() {
    	String error_signup_msg = null;
        DynamicForm filledForm = form().bindFromRequest();

        ParticularUser userCreated = ParticularUserDataSource.getParticularUser(filledForm.get("register_email"));
        if (userCreated != null){
        	error_signup_msg = "Usuario con ese email ya registrado";
            return badRequest(views.html.login_particular_user.login.render(null, error_signup_msg));
        }

        if(!filledForm.get("register_password").equals(filledForm.get("register_repeat_password"))) {
        	error_signup_msg = "Las contraseñas no coinciden";
            return badRequest(views.html.login_particular_user.login.render(null, error_signup_msg));
        }

        userCreated = new ParticularUser(filledForm.get("register_name"), filledForm.get("register_surnames"),
                filledForm.get("register_email"), filledForm.get("register_password"));
        ParticularUserDataSource.insertIntoParticularUser(userCreated);

        return ok(views.html.complete_user_profile.complete_user_profile_1.render());
    }


    public static Result step2(){
        return ok(views.html.complete_user_profile.complete_user_profile_2.render());
    }

    public static Result step3(){
        return ok(views.html.complete_user_profile.complete_user_profile_3.render());
    }



    
    
}
