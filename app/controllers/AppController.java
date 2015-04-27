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

import java.util.*;

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

            return updateInterviews(user, request.get("dataBase"));
        }

        return badRequest("fail-invalid user");
    }

    public static Result updateInterviews(User user, JsonNode dataBase){
        List<InterviewSchedule> appInterviews = new Gson().fromJson(dataBase.toString(), new TypeToken<List<InterviewSchedule>>() {
        }.getType());

        user.interviewScheduleList = updateInterviews(user, appInterviews);
        SingletonDataSource.getInstance().updateAllUserData(user);

        return ok(user.interviewScheduleListToJson());
    }

    private static List<InterviewSchedule> updateInterviews(User user, List<InterviewSchedule> appInterviews){
        List<InterviewSchedule> userInterviews = user.interviewScheduleList;
        int userSize = userInterviews.size();
        int appSize = appInterviews.size();

        if(userSize == 0){

            for (int i = 0; i < appSize; i++) {
                //There's not equal interview in user's list. Add new
                userInterviews.add(appInterviews.get(i));
            }
        } else {
            for (int i = 0; i < appSize; i++) {
                for (int j = 0; j < userSize; j++) {
                    if (appInterviews.get(i).id.equals(userInterviews.get(j).id)) {
                        //Has equal ID
                        if ((appInterviews.get(i).creationDate.getTime() - userInterviews.get(j).creationDate.getTime()) == 0) {
                            //Has equal ID and CreationDate
                            if ((appInterviews.get(i).modificationDate.getTime() - userInterviews.get(j).modificationDate.getTime()) > 0) {
                                //Has been modificated -> Update interview in user's list
                                userInterviews.get(j).date = appInterviews.get(i).date;
                                userInterviews.get(j).address = appInterviews.get(i).address;
                                userInterviews.get(j).company = appInterviews.get(i).company;
                                userInterviews.get(j).modificationDate = appInterviews.get(i).modificationDate;
                            }
                        } else {
                            //Has equal ID and different CreationDate
                            appInterviews.get(i).id = UUID.randomUUID().toString();
                            userInterviews.add(appInterviews.get(i));
                        }
                    } else {
                        //Has equal ID and different CreationDate
                        appInterviews.get(i).id = UUID.randomUUID().toString();
                        userInterviews.add(appInterviews.get(i));
                    }
                }
            }
        }
        return user.interviewScheduleList;
    }
}
