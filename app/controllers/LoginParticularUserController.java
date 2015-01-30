package controllers;

import models.datasource.CompanyUserDataSource;
import models.datasource.ParticularUserDataSource;
import models.entities.CompanyUser;
import models.entities.ParticularUser;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import static play.data.Form.form;

/**
 * Created by Victor on 29/01/2015.
 */
public class LoginParticularUserController extends Controller {

    public static Result blank(){
        return ok(views.html.login_particular_user.login.render());
    }

    public static Result submitLogin(){
        DynamicForm bindedForm = form().bindFromRequest();

        ParticularUser user = ParticularUserDataSource.getParticularUser(bindedForm.get("email"));

        if(user != null && bindedForm.get("password").equals(user.password)){
            session("email", user.email);
            session("name", user.name);
            return ok(index.render());
        }

        CompanyUser companyUser = CompanyUserDataSource.getCompanyUser(bindedForm.get("email"));
        if(companyUser != null && bindedForm.get("password").equals(companyUser.password)){
            session("email", companyUser.email);
            session("name", "Company name FAKE");
            return ok(index.render());
        }

        return unauthorized(views.html.login_particular_user.login.render());
    }
}
