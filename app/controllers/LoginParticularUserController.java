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
 * Created by Victor on 29/01/2015.
 */
public class LoginParticularUserController extends Controller {

    public static Result blank() {
        return ok(views.html.login_particular_user.login.render(null));
    }

    public static Result submitLogin() {
    	String error_msg = null; // Para saber en la vista del login cuando hay error
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
        
        error_msg = "Email o contraseña incorrecta";
        
        return unauthorized(views.html.login_particular_user.login.render(error_msg));
    }

    public static Result signUpLogin() {
        DynamicForm filledForm = form().bindFromRequest();

        ParticularUser userCreated = ParticularUserDataSource.getParticularUser(filledForm.get("register_email"));
        if (userCreated != null){
            return badRequest("Usuario con ese email ya registrado");
        }

        if(!filledForm.get("register_password").equals(filledForm.get("register_repeat_password"))) {
            return badRequest("Las contraseñas no coinciden");
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
