package controllers;

import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

import static play.data.Form.form;

/**
 * Created by Victor on 29/01/2015.
 */
public class LoginCompanyUserController extends Controller {

    public static Result blank(){
        return ok(views.html.login_company_user.login.render(null, null));
    }

    public static Result submitLogin(){
        String error_login_msg = null;
        DynamicForm filledForm = form().bindFromRequest();

        System.out.println("####### FORM\n EMAIL: "+filledForm.get("email")+"\n PASSWORD: "+filledForm.get("password"));

        //Check if Company User exists in db
        CompanyUser companyUser = CompanyUserDataSource.getCompanyUser(filledForm.get("email"));
        if(companyUser != null && companyUser.password.equals(filledForm.get("password"))){
            session("email", companyUser.email);
            session("name", companyUser.name);
            return redirect("/");
        }

        //Check if Particular User exists. Trying to login as Particular User from Company User view
        ParticularUser particularUser = ParticularUserDataSource.getParticularUser(filledForm.get("email"));
        if(particularUser != null && particularUser.password.equals(filledForm.get("password"))){
            session("email", particularUser.email);
            session("name", particularUser.name);
            return redirect("/");
        }

        error_login_msg = "Email o contraseña incorrecta";

        return badRequest(views.html.login_company_user.login.render(error_login_msg, null));
    }

    public static Result submitSignUp(){
        String error_signup_msg = null;

        DynamicForm filledForm = form().bindFromRequest();

        CompanyUser companyUser = CompanyUserDataSource.getCompanyUser(filledForm.get("email"));

        if(companyUser != null){
            error_signup_msg = "Usuario con ese email ya registrado";
            return badRequest(views.html.login_company_user.login.render(null, error_signup_msg));
        }

        if(!filledForm.get("password").equals(filledForm.get("repeat_password"))) {
            error_signup_msg = "Las contraseñas no coinciden";
            return badRequest(views.html.login_particular_user.login.render(null, error_signup_msg));
        }

        companyUser = new CompanyUser(filledForm.get("name"), filledForm.get("email"), filledForm.get("password"));
        CompanyUserDataSource.insertIntoCompanyUser(companyUser);

        return redirect("/");
    }

}
