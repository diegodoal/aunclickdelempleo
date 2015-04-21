package controllers;

import com.google.gson.Gson;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Utils;

import java.util.List;

import static play.data.Form.form;


/**
 * Created by Victor on 20/04/2015.
 */
public class AdminController extends Controller{

    private final static String ADMIN_USER = "adecco";
    private final static String ADMIN_PASSWORD = "password";

    public static boolean checkConnection(String user){
        if(user != null && user.equals(Utils.encryptWithSHA1(ADMIN_USER))){
            return true;
        }else{
            return false;
        }
    }

    public static Result login(){
        return ok(views.html.admin.login.render());
    }

    public static Result submitLogin(){
        DynamicForm bindedForm = form().bindFromRequest();

        String user = bindedForm.get("user");
        String password = bindedForm.get("password");
        if(user.equals("adecco") && password.equals("password")){
            session("user", Utils.encryptWithSHA1(user));
            return redirect("/admin/show");
        }
        return badRequest();
    }

    public static Result blank(String email, String id){
        if(checkConnection(session().get("user"))) {
            List<User> users = SingletonDataSource.getInstance().findAll();
            if (email == null || id == null)
                return ok(views.html.admin.admin_main.render(users, null));

            for (User user : users) {
                if (user.email.equals(email) && user.id.equals(id)) {
                    return ok(views.html.admin.admin_main.render(users, user));
                }
            }
            return ok(views.html.admin.admin_main.render(users, null));
        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result deleteUser(String email, String id){
        if(checkConnection(session().get("user"))) {
            User user = SingletonDataSource.getInstance().getUserByEmail(email);
            if (user != null && user.id.equals(id)) {
                boolean result = SingletonDataSource.getInstance().deleteUser(email);
                if (result == true)
                    return redirect("/admin/show");
                else
                    return badRequest("Cannot delete.");
            } else
                return badRequest("Cannot delete. User not found or invalid user.");
        }else{
            return unauthorized("Access denied");
        }
    }


}
