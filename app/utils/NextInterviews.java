package utils;

import models.datasource.SingletonDataSource;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 09/04/2015.
 */
public class NextInterviews {

    public final static long DAYS_BEFORE_INTERVIEW = 4;

    public static List<UserInterview> getAllInterviews(){
        List<User> users = SingletonDataSource.getInstance().findAll();

        List<UserInterview> nextInterviews = new ArrayList<>();

        for(int i=0; i<users.size(); i++){
            for(int j=0; j<users.get(i).interviewScheduleList.size(); j++){
                if(users.get(i).interviewScheduleList.get(j).notified.equals(String.valueOf(false)) && isNextInterview(users.get(i).interviewScheduleList.get(j))){
                    nextInterviews.add(new UserInterview(users.get(i).interviewScheduleList.get(j), users.get(i).email, users.get(i).name));
                    users.get(i).interviewScheduleList.get(j).notified = String.valueOf(true);
                }
                SingletonDataSource.getInstance().updateAllUserData(users.get(i));
            }
        }

        return nextInterviews;
    }


    public static boolean isNextInterview(InterviewSchedule interviewSchedule){
        Date interviewDate = Utils.stringToDate(interviewSchedule.date);
        Date currentDate = new Date();

        long diff = Utils.getDiffBetweenTwoDates(currentDate, interviewDate);

        if(diff > 0 && diff <= DAYS_BEFORE_INTERVIEW)
            return true;
        else
            return false;
    }

}
