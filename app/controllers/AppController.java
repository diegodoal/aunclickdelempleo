package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import models.datasource.SingletonDataSource;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;
import utils.Utils;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static play.mvc.Controller.request;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;


/**
 * Created by alfonsocamberolorenzo on 21/04/15.
 */
public class AppController {

    //METODO PARA LA APP DE ALFONSO

    public static Result submitLoginApp(){
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(request.get("email").asText());

        if(user != null && Utils.encryptWithSHA1(request.get("password").asText()).equals(user.password)){
            return ok("success");
        }

        return badRequest("fail");
    }

    public static Result getInterviews() {
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(request.get("email").asText());

        if(user != null && Utils.encryptWithSHA1(request.get("password").asText()).equals(user.password)){
            return ok(user.interviewScheduleListToJson());
        }

        return badRequest("fail");
    }

    public static Result updateInterviews(){
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        String email = request.get("email")[0];
        String password = request.get("password")[0];
        List<InterviewSchedule> appInterviews = new Gson().fromJson(request.get("dataBase")[0], new TypeToken<List<InterviewSchedule>>() {
        }.getType());

        User user = SingletonDataSource.getInstance().getUserByEmail(email);

        if(user != null && user.password.equals(password)){
            user.interviewScheduleList = updateInterviews(user.interviewScheduleList, appInterviews);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }else{
            return badRequest("fail-invalid user");
        }
        return ok("success");
    }

    private static List<InterviewSchedule> updateInterviews(List<InterviewSchedule> userInterviews, List<InterviewSchedule> appInterviews){
        for(int i=0; i<appInterviews.size(); i++){
            boolean updated = false;
            for(int j=0; j<userInterviews.size(); j++){
                if(appInterviews.get(i).id.equals(userInterviews.get(j).id)){
                    //Has equal ID
                    if(appInterviews.get(i).id.equals(userInterviews.get(j).id)){
                        //Has equal ID and CreationDate
                        if ((appInterviews.get(i).modificationDate.getTime() - userInterviews.get(j).modificationDate.getTime()) > 0) {
                            //Has been modificated -> Update interview in user's list
                            userInterviews.remove(j);
                            appInterviews.get(i).id = UUID.randomUUID().toString();
                            userInterviews.add(appInterviews.get(i));
                            updated = true;
                        }
                    }else{
                        //Has equal ID and different CreationDate
                        userInterviews.add(appInterviews.get(i));
                        updated = true;
                    }
                }
            }
            if(!updated){
                //There's not equal interview in user's list. Add new
                userInterviews.add(appInterviews.get(i));
            }
        }
        return userInterviews;
    }
}
