package controllers;

import models.datasource.SingletonDataSource;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;


/**
 * Created by Victor on 20/04/2015.
 */
public class AdminController extends Controller{

    public static Result blank(String email, String id){
        List<User> users = SingletonDataSource.getInstance().findAll();
        if(email == null || id == null)
            return ok(views.html.admin.admin_main.render(users, null));

        for(User user : users){
            if(user.email.equals(email) && user.id.equals(id)){
                return ok(views.html.admin.admin_main.render(users, user));
            }
        }

        return ok(views.html.admin.admin_main.render(users, null));
    }
}
