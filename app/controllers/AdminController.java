package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.datasource.ConfDataSource;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Stats;
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
            return redirect("/admin/users");
        }
        return badRequest();
    }

    public static Result usersBlank(){
        String[] result = ConfDataSource.getInstance().getAmazonConf();
       // System.out.println("@@@@@@@@@@@@@@@" + result[0] + "---"+result[1]+"----"+result[2]);

        if(checkConnection(session().get("user"))) {
            List<User> users = SingletonDataSource.getInstance().findAll();
            return ok(views.html.admin.users.render(users));
        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result userInfo(String email, String id){
        if(checkConnection(session().get("user"))){
            User user = SingletonDataSource.getInstance().getUserByEmail(email);
            if(user != null && user.id.equals(id)){
                return ok(views.html.admin.user_info.render(user));
            }else{
                return redirect("/admin/users");
            }
        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result deleteUser(){
        if(checkConnection(session().get("user"))) {
            JsonNode request = request().body().asJson();

            String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {
            }.getType());

            User user = SingletonDataSource.getInstance().getUserByEmail(result[0]);
            if(user != null && user.id.equals(result[1])){
                SingletonDataSource.getInstance().deleteUser(user.email);
                return ok();
            }else{
                return badRequest("Cannot delete. User not found or invalid user.");
            }
        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result stats(){
        return ok(views.html.admin.stats.render(Stats.getUsersWithDrivingLicense(), Stats.getCertificatesOfDisability()));
    }

    /* ############### OPTIONS ############### */
    public static Result optionsBlank(){
        String[] amazon = ConfDataSource.getInstance().getAmazonConf();
        return ok(views.html.admin.options.render(amazon));
    }

    public static Result updateAmazonOptions(){
        DynamicForm form = form().bindFromRequest();
        String bucket = form.get("bucket_name");
        String access_key = form.get("access_key");
        String secret_key = form.get("secret_key");

        if(bucket != null && access_key != null && secret_key != null){
            ConfDataSource.getInstance().updateAmazonConf(bucket.trim(), access_key.trim(), secret_key.trim());
        }

        return redirect("/admin/options");
    }

}
