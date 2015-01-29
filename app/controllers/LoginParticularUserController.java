package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Victor on 29/01/2015.
 */
public class LoginParticularUserController extends Controller {

    public static Result blank(){
        return ok(views.html.login_particular_user.login.render());
    }
}
