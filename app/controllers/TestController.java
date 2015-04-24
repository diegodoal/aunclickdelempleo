package controllers;

import com.google.gson.Gson;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Victor on 24/04/2015.
 */
public class TestController extends Controller{

    public static Result testInterviews(){
        User user = new User();



        for(int i=0; i<6; i++){
            try {
                Thread.sleep(3000);                 //3 seconds
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            InterviewSchedule interviewSchedule = new InterviewSchedule("28-04-2015", "Company"+i, "Address"+i);
            user.interviewScheduleList.add(interviewSchedule);
        }


        List<InterviewSchedule> appInterviews = new ArrayList<>();

        for(int i=0; i<2; i++){
            InterviewSchedule appInterview = new InterviewSchedule();
            appInterview.company = user.interviewScheduleList.get(i).company;
            appInterview.date = user.interviewScheduleList.get(i).date;
            appInterview.address = user.interviewScheduleList.get(i).address;
            appInterview.id = user.interviewScheduleList.get(i).id;
            appInterview.creationDate = user.interviewScheduleList.get(i).creationDate;
            appInterview.modificationDate = new Date();

            appInterviews.add(appInterview);
        }

        for(int i=2; i<4; i++){
            InterviewSchedule appInterview = new InterviewSchedule();
            appInterview.company = user.interviewScheduleList.get(i).company;
            appInterview.date = user.interviewScheduleList.get(i).date;
            appInterview.address = user.interviewScheduleList.get(i).address;
            appInterview.id = user.interviewScheduleList.get(i).id;
            appInterview.creationDate = user.interviewScheduleList.get(i).creationDate;
            appInterview.modificationDate = user.interviewScheduleList.get(i).modificationDate;

            appInterviews.add(appInterview);
        }

        for(int i=4; i<6; i++){
            try {
                Thread.sleep(3000);                 //3 seconds
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            InterviewSchedule appInterview = new InterviewSchedule();
            appInterview.company = "Company"+i;
            appInterview.date = user.interviewScheduleList.get(i).date;
            appInterview.address = "Address"+i;
            appInterview.id = user.interviewScheduleList.get(i).id;
            appInterview.creationDate = new Date();
            appInterview.modificationDate = new Date();

            appInterviews.add(appInterview);
        }

        String result = new Gson().toJson(user.interviewScheduleList) + "\n\n" + new Gson().toJson(appInterviews);

        user.interviewScheduleList = updateInterviews(user.interviewScheduleList, appInterviews);

        result += "\n\n\n" + new Gson().toJson(user.interviewScheduleList);
        /*



        appInterview.id = interviewSchedule.id;
        appInterview.modificationDate = new Date();

        long diff = appInterview.modificationDate.getTime() - interviewSchedule.modificationDate.getTime();
        String result;
        if(diff > 0){
            result = "Modificated after last upload";
        }else{
            result = "Not modificated";
        }

       */

        return ok(result);

    }

    private static InterviewSchedule checkModifications(List<InterviewSchedule> userInterviews, InterviewSchedule appInterview){
        for(InterviewSchedule userInterview : userInterviews){
            if(userInterview.id.equals(appInterview.id)){
                if(userInterview.creationDate.equals(appInterview.creationDate)){
                    //Has equal ID and CreationDate
                    if ((appInterview.modificationDate.getTime() - userInterview.modificationDate.getTime()) > 0) {
                        Logger.info("Sobreescribir " + new Gson().toJson(userInterview) + " por " + new Gson().toJson(appInterview));
                        return appInterview;
                    } else {
                        Logger.info("NO sobreesciribir " + new Gson().toJson(userInterview));
                        return null;
                    }
                }else{
                    //Has equal ID but different DateCreation -> New entry
                    Logger.info("Insertar nueva entrevista " + new Gson().toJson(appInterview));
                }
            }else{
                //Has different ID -> new entry
                Logger.info("Insertar nueva entrevista " + new Gson().toJson(appInterview));
            }
        }
        return null;
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
