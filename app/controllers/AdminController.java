package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.datasource.ConfDataSource;
import models.datasource.SingletonDataSource;
import models.entities.AdminUser;
import models.entities.User;
import play.Logger;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Stats;
import utils.Utils;

import java.util.Date;
import java.util.List;

import static play.data.Form.form;


/**
 * Created by Victor on 20/04/2015.
 */
public class AdminController extends Controller{

    private final static String ADMIN_USER = "adecco";
    private final static String ADMIN_PASSWORD = "password";

    public static AdminUser checkConnection(){
        String user = session().get("a-user");
        String timestamp = session().get("a-timestamp");
        if(user != null && timestamp != null){
            for(AdminUser adminUser : ConfDataSource.getInstance().getAllAdminUsers()){
                if(user.equals(adminUser.name) && adminUser.connectionTimestamp.equals(timestamp)){
                    return adminUser;
                }
            }
        }
        session().clear();
        return null;
    }

    public static Result login(){
        if(checkConnection() != null){
            return redirect("/admin/users");
        }else {
            return ok(views.html.admin.login.render());
        }
    }

    public static Result submitLogin(){
        DynamicForm bindedForm = form().bindFromRequest();

        String name = bindedForm.get("user");
        String password = bindedForm.get("password");

        AdminUser adminUser = ConfDataSource.getInstance().getAdminUser(name);

        if(adminUser != null && adminUser.password.equals(Utils.encryptWithSHA1(password))){
            Date date = new Date();
            adminUser.connectionTimestamp = date.toString();
            session("a-user", name);
            session("a-timestamp", date.toString());
            ConfDataSource.updateAdminUser(adminUser);
            return redirect("/admin/users");
        }else
            return badRequest("Login error!");
    }

    public static Result logout(){
        session().clear();
        return redirect("/admin/login");
    }

    public static Result usersBlank(){
        String[] result = ConfDataSource.getInstance().getAmazonConf();
        if(checkConnection() != null) {
            List<User> users = SingletonDataSource.getInstance().findAll();
            return ok(views.html.admin.users.render(users));
        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result userInfo(String email, String id){
        if(checkConnection() != null){
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
        if(checkConnection() != null) {
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
        if(checkConnection() != null) {
            return ok(views.html.admin.stats.render(Stats.getUsersWithDrivingLicense(), Stats.getCertificatesOfDisability()));
        }else{
            return unauthorized("Access denied");
        }
    }

    /* ############### OPTIONS ############### */
    public static Result optionsBlank(){
        AdminUser adminUser = checkConnection();
        if(adminUser != null) {
            String[] amazon = ConfDataSource.getInstance().getAmazonConf();
            List<AdminUser> adminUsers = ConfDataSource.getInstance().getAllAdminUsers();
            return ok(views.html.admin.options.render(amazon, adminUser, adminUsers));
        }else{
            return unauthorized("Access denied");
        }
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

    public static Result updateAdminUserOptions(){
        DynamicForm form = form().bindFromRequest();
        JsonNode request = request().body().asJson();

        String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {
        }.getType());

        AdminUser adminUser = ConfDataSource.getInstance().getAdminUser(result[0]);
        if(adminUser!= null) {
            if(!result[1].trim().equals("")){
                adminUser.password = Utils.encryptWithSHA1(result[1]);
            }
            Date date = new Date();
            adminUser.connectionTimestamp = date.toString();
            session("a-user", adminUser.name);
            session("a-timestamp", date.toString());
            ConfDataSource.updateAdminUser(adminUser);
            return redirect("/admin/options");
        }
        return badRequest("Cannot update user info.");
    }

    public static Result deleteAdminUser(){
        if(checkConnection() != null) {
            String request = request().body().asText();

            AdminUser adminUser = ConfDataSource.getInstance().getAdminUser(request);
            if(adminUser != null && ConfDataSource.getInstance().deleteAdminUser(request)){
                return ok();
            }else{
                return badRequest("Cannot delete. User not found or invalid user.");
            }
        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result newAdminUser(){
        if(checkConnection() != null){
            JsonNode request = request().body().asJson();

            String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {}.getType());

            AdminUser adminUser = new AdminUser(result[0], result[1]);
            ConfDataSource.getInstance().insertNewAdminUser(adminUser);
            return ok();
        }else{
            return unauthorized("Access denied");
        }
    }

}
