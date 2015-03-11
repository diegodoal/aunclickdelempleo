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

    public static Result submitSignUp(){
        String error_signup_msg = null;
        DynamicForm filledForm = form().bindFromRequest();

        User userCreated = UserDataSource.getUserByEmail(filledForm.get("register_email"));

        if (userCreated != null){
            error_signup_msg = "Usuario con ese email ya registrado";
            return badRequest(views.html.login_user.login.render(null, error_signup_msg));
        }

        if(!filledForm.get("register_password").equals(filledForm.get("register_repeat_password"))) {
            error_signup_msg = "Las contraseñas no coinciden";
            return badRequest(views.html.login_user.login.render(null, error_signup_msg));
        }

        userCreated = new User(filledForm.get("register_name"), filledForm.get("register_surnames"),
                filledForm.get("register_email"), filledForm.get("register_password"));
        UserDataSource.insertIntoUsersCollection(userCreated);

        session("email", userCreated.email);
        session("name", userCreated.name);

        return redirect("/");
    }

    public static Result logout(){
        session().clear();
        return redirect("/");
    }
}