package controllers;

import models.datasource.UserDataSource;
import models.entities.User;
import play.data.DynamicForm;
import play.mvc.Result;

import static play.data.Form.form;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;


/**
 * Created by Victor on 09/03/2015.
 */
public class LoginController {

    public static Result blank(){
        return ok(views.html.login_user.login.render(null, null));
    }

    public static Result submitLogin(){
        String error_login_msg = null; //Contains the msg if there's a login error
        DynamicForm bindedForm = form().bindFromRequest();

        User user = UserDataSource.getUserByEmail(bindedForm.get("email"));

        if(user != null && bindedForm.get("password").equals(user.password)) {
            session("email", user.email);
            session("name", user.name);
            return redirect("/");
        }

        error_login_msg = "Email o contraseña incorrecta";
        return badRequest(views.html.login_user.login.render(error_login_msg, null));
    }
}
