package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.data.DynamicForm;
import play.mvc.Http;
import play.mvc.Result;
import utils.EmailUtil;
import utils.Utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import static play.data.Form.form;
import static play.mvc.Controller.request;
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

        User user = SingletonDataSource.getInstance().getUserByEmail(bindedForm.get("email"));

        if(user != null && Utils.encryptWithSHA1(bindedForm.get("password")).equals(user.password)){
            user.connectionTimestamp = new Date().toString();
            session("email", user.email);
            session("name", user.name);
            session("timestamp", user.connectionTimestamp);

            SingletonDataSource.getInstance().updateAllUserData(user);

            return redirect("/");
        }

        error_login_msg = "Email o contraseña incorrecta";
        return badRequest(views.html.login_user.login.render(error_login_msg, null));
    }

    public static Result submitSignUp(){
        String error_signup_msg = null;
        DynamicForm filledForm = form().bindFromRequest();

        User userCreated = SingletonDataSource.getInstance().getUserByEmail(filledForm.get("register_email"));

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

        userCreated.connectionTimestamp = new Date().toString();
        SingletonDataSource.getInstance().insertIntoUsersCollection(userCreated);

        session("email", userCreated.email);
        session("name", userCreated.name);
        session("timestamp", userCreated.connectionTimestamp);

        return redirect("/");
    }

    public static Result logout(){
        session().clear();
        return redirect("/");
    }

    public static Result sendRestoreEmail(){
        JsonNode request = request().body().asJson();

        String email = new Gson().fromJson(request.toString(), new TypeToken<String>() {}.getType());

        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        if (user == null){
            return badRequest(views.html.login_user.login.render(null, null));
        }

        user.restorePasswordToken = UUID.randomUUID().toString();
        user.restorePasswordTimestamp = Utils.formatDateToCustomPattern(new Date());

        SingletonDataSource.getInstance().updateAllUserData(user);
        String subject = "Restablecer contraseña en \"A un click del empleo\"";
        String message = "Para restablecer su contraseña, pulse en el siguiente enlace: http://localhost:9000/restore/"+user.email+"/"+user.restorePasswordToken;
        EmailUtil.emailMaker(email, subject, message);

        return redirect("/login");
    }

    public static Result restore(String email, String token){

        User user = SingletonDataSource.getInstance().getUserByEmail(email);
        if(user == null){
            return badRequest(views.html.login_user.restore.render("Usuario incorrecto"));
        }
        if(!user.restorePasswordToken.equals(token)){
            return badRequest(views.html.login_user.restore.render("Token para restablecer incorrecto"));
        }

        if(Utils.getDiffBetweenTwoDates(Utils.stringToDate(user.restorePasswordTimestamp), new Date()) > 0){
            return badRequest(views.html.login_user.restore.render("Expirado tiempo para restablecer contraseña. Enviar nuevo correo"));
        }

        session().clear();
        session("restore_email", user.email);
        return ok(views.html.login_user.restore.render(null));
    }

    public static Result restorePassword(){
        DynamicForm form = form().bindFromRequest();
        String newPassword = form.get("restore_password");
        String repeatPassword = form.get("repeat_restore_password");

        if(!newPassword.equals(repeatPassword)){
            return badRequest(views.html.login_user.restore.render("Las contraseñas no coinciden"));
        }

        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("restore_email"));
        if(user != null){
            user.password = Utils.encryptWithSHA1(newPassword);
            session().clear();
            user.connectionTimestamp = new Date().toString();

            session("email", user.email);
            session("name", user.name);
            session("timestamp", user.connectionTimestamp);

            user.restorePasswordTimestamp = null;
            user.restorePasswordToken = null;

            SingletonDataSource.getInstance().updateAllUserData(user);
        }else{
            return badRequest(views.html.login_user.restore.render("No se ha econtrado el usuario"));
        }

        return redirect("/");
    }
}
