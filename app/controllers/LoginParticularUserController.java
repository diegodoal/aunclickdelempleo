package controllers;

import models.datasource.ParticularUserDataSource;
import models.entities.ParticularUser;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

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

        ParticularUser user = new ParticularUser();
        user = ParticularUserDataSource.getParticularUser(bindedForm.get("email"));

        if(bindedForm.hasErrors()){
            return badRequest(bindedForm.errorsAsJson().toString());
        }

        if(user != null){
            session("email", user.email);
            session("name", user.name);
            return ok("Logueado correctamente! "+user.email);
        }


        return unauthorized(views.html.login_particular_user.login.render());
    }
}
