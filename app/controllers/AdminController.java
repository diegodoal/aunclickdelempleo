package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.S3File;
import models.datasource.ConfDataSource;
import models.datasource.MessagesDataSource;
import models.datasource.SingletonDataSource;
import models.entities.AdminUser;
import models.entities.Message;
import models.entities.User;
import models.entities.orientation.ProfessionalValue;
import models.entities.orientation.Skill;
import play.Logger;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Stats;
import utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.data.Form.form;
import static utils.Utils.getRecentUsers;


/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
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
            return redirect("/admin");
        }else
            return badRequest("Login error!");
    }

    public static Result logout(){
        session().clear();
        return redirect("/admin/login");
    }

    public static Result blank(){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        return ok(views.html.admin.index.render(getRecentUsers()));
    }



    public static Result usersBlank(){
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

    public static Result updatePersonalInfo(String email, String id){
        if(checkConnection() == null){
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        JsonNode request = request().body().asJson();

        if(user != null && user.id.equals(id)) {
            String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {}.getType());

            User auxUser = SingletonDataSource.getInstance().getUserByEmail(result[2]);
            if(!result[2].equals(user.email) && auxUser != null){
                return badRequest("Ya existe un usuario con este email");
            }
            user.name = result[0];
            user.surnames = result[1];
            user.email = result[2];

            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/admin/users/"+user.email+"/"+id);

    }

    public static Result userSkills(String email, String id){
        if (checkConnection() == null){
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);
        if(user != null && user.id.equals(id)) {
            return ok(views.html.admin.user_skills.render(user));
        }else{
            return redirect("/admin/users");
        }
    }

    public static Result submitUserSkills(String email, String id){
        if (checkConnection() == null){
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        JsonNode request = request().body().asJson();

        if(user != null && user.id.equals(id)) {
            String[][] skills = new Gson().fromJson(request.toString(), new TypeToken<String[][]>() {
            }.getType());
            if(user.skill.isEmpty()){
                for (int i = 0; i < skills.length; i++) {
                    user.skill.add(i, new Skill(skills[i][0], skills[i][1]));
                }
            }else{
                for (int i=0; i<skills.length; i++){
                    user.skill.get(i).level = skills[i][1];
                }
            }
            user.completedOrientationSteps.skills = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/admin/users/"+email+"/"+id);
    }

    public static Result userInterests(String email, String id){
        if (checkConnection() == null){
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);
        if(user != null && user.id.equals(id)) {
            return ok(views.html.admin.user_interestIdentification.render(user));
        }else{
            return redirect("/admin/users");
        }
    }

    public static Result submitUserInterests(String email, String id){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        if(user != null && user.id.equals(id)) {
            List<String> interests = new Gson().fromJson(request.toString(), new TypeToken<List<String>>() {
            }.getType());

            user.interests = interests;
            user.completedOrientationSteps.interestIdentification = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/admin/users/"+email+"/"+id);
    }

    public static Result userPersonal(String email, String id){
        if (checkConnection() == null){
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);
        if(user != null && user.id.equals(id)) {
            return ok(views.html.admin.user_personal.render(user));
        }else{
            return redirect("/admin/users");
        }
    }

    public static Result submitUserPersonal(String email, String id){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        if(user != null && user.id.equals(id)) {
            List<String> personalCharacteristics = new Gson().fromJson(request.toString(), new TypeToken<List<String>>() {
            }.getType());

            user.personalCharacteristics = personalCharacteristics;
            user.completedOrientationSteps.personal = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/admin/users/"+email+"/"+id);
    }

    public static Result userProfessional(String email, String id){
        if (checkConnection() == null){
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);
        if(user != null && user.id.equals(id)) {
            return ok(views.html.admin.user_professional.render(user));
        }else{
            return redirect("/admin/users");
        }
    }

    public static Result submitUserProfessional(String email, String id){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        JsonNode request = request().body().asJson();

        if(user != null && user.id.equals(id)) {
            String[][] professionalValues = new Gson().fromJson(request.toString(), new TypeToken<String[][]>() {
            }.getType());

            if(user.professionalValues.isEmpty()) {
                for (int i = 0; i < professionalValues.length; i++) {
                    user.professionalValues.add(i, new ProfessionalValue(professionalValues[i][0], professionalValues[i][1]));
                }
            } else {
                for (int i = 0; i < professionalValues.length; i++) {
                    user.professionalValues.get(i).valuation = professionalValues[i][1];
                }
            }

            user.completedOrientationSteps.professional = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/admin/users/"+email+"/"+id);
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

    public static Result deleteUserNoValidated(){
        if(checkConnection() != null) {
            JsonNode request = request().body().asJson();

            String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {
            }.getType());

            if(SingletonDataSource.getInstance().deleteUser(result[0]))
                return ok();
            else
                return badRequest("Cannot delete. User not found or invalid user.");

        }else{
            return unauthorized("Access denied");
        }
    }

    public static Result stats(){
        if(checkConnection() != null) {
            List<User> users = SingletonDataSource.getInstance().findAll();

            return ok(views.html.admin.stats.render(Stats.getUsersWithDrivingLicense(users), Stats.getCertificatesOfDisability(users), Stats.getEducationLevel(users),
                    users.size(), Utils.getRecentUsers().size(), Stats.getNumberOfUsersWithFullProfile(users),
                    MessagesDataSource.getNumberOfTotalMessages(), new S3File().getNumberOfFiles(), ConfDataSource.getNumberOfGeneratedDocs()));
        }else{
            return unauthorized("Access denied");
        }
    }

    /* ############### MESSAGES ############### */
    public static Result messages(){
        List<Message> allInbox = MessagesDataSource.getInstance().getMessagesByReceiver("adecco");
        List<Message> inboxNotDeleted = new ArrayList<>();
        List<Message> inboxDeleted = new ArrayList<>();
        for(Message message : allInbox){
            if(message.deletedByReceiver == false){
                inboxNotDeleted.add(message);
            }else{
                inboxDeleted.add(message);
            }
        }

        List<Message> sentMessages = MessagesDataSource.getInstance().getMessagesBySender("adecco");
        List<Message> sentNotDeleted = new ArrayList<>();
        List<Message> sentDeleted = new ArrayList<>();
        for(Message message : sentMessages){
            if(message.deletedBySender == false){
                sentNotDeleted.add(message);
            }else{
                sentDeleted.add(message);
            }
        }

        //Custom list for typeahead
        List<User> users = SingletonDataSource.getInstance().findAll();
        List<String> customUsers = new ArrayList<>();
        for(User user : users){
            customUsers.add(user.name + " " + user.surnames + " ("+user.email+")");
        }

        String notReadMessages = ""+MessagesDataSource.getInstance().getNumberOfNotReadMessages("adecco");
        return ok(views.html.admin.messages.render(customUsers, inboxNotDeleted, sentNotDeleted, inboxDeleted, sentDeleted, notReadMessages));
    }

    public static Result readMessage(){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        String request = request().body().asText();

        Message message = MessagesDataSource.getInstance().getMessagesById(request);
        if(message != null){
            message.read = true;
            MessagesDataSource.getInstance().updateMessage(message);
        }
        return ok();
    }

    public static Result deleteMessage(){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        JsonNode request = request().body().asJson();

        String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {
        }.getType());
        Message message = MessagesDataSource.getInstance().getMessagesById(result[0]);

        if(message.toUser.equals(result[1])){
            message.deletedByReceiver = true;
        }else{
            message.deletedBySender = true;
        }
        MessagesDataSource.getInstance().updateMessage(message);

        return ok();
    }

    public static Result sendMessage(){
        if(checkConnection() == null) {
            return unauthorized("Access denied");
        }
        JsonNode request = request().body().asJson();

        String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {
        }.getType());

        User user = SingletonDataSource.getInstance().getUserByEmail(result[0]);
        if(user == null){
            return badRequest("El usuario no existe");
        }

        Message message = new Message("adecco", result[0], result[1], result[2]);

        MessagesDataSource.insertNewMessage(message);

        return ok();
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
