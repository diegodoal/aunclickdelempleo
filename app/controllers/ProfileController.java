package controllers;

import models.datasource.SingletonDataSource;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Victor on 08/04/2015.
 */
public class ProfileController extends Controller {

    public static Result blank(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if (user == null){
            return redirect("/");
        }
        return ok(views.html.user_profile.profile.render(user));
    }

    public static Result textPlainInfo(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if (user == null){
            return redirect("/");
        }
        return ok(views.html.user_profile.user_info.render(user));
    }
}
