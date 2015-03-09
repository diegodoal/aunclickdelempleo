package controllers;

import play.mvc.Result;
import static play.mvc.Results.ok;


/**
 * Created by Victor on 09/03/2015.
 */
public class LoginController {

    public static Result blank(){
        return ok(views.html.login_user.login.render(null, null));
    }
}
