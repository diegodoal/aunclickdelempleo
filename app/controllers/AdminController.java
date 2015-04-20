package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Http.Context.Implicit.request;

/**
 * Created by Victor on 20/04/2015.
 */
public class AdminController extends Controller{

    public static Result blank(String email, String id){
        List<User> users = SingletonDataSource.getInstance().getAllUsers();
        if(email == null || id == null)
            return ok(views.html.admin.admin_main.render(users, null));

        User user = SingletonDataSource.getUserByEmail(email);
        if(user != null && user.id.equals(id))
            return ok(views.html.admin.admin_main.render(users, user));
        else
            return ok(views.html.admin.admin_main.render(users, null));
    }
}
