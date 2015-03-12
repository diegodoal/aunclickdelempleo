package utils;

import models.datasource.UserDataSource;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Victor on 12/03/2015.
 */
public class EmailChecker {

    public SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");

    public List<UserInterview> getUsersWithNextInterviews(){
        List<User> allUsers = UserDataSource.getAllUsers();
        List<UserInterview> usersToNotify = new ArrayList<>();

        List<UserInterview> auxList;
        for(int i=0; i<allUsers.size(); i++){
            auxList = checkForUpcomingDates(allUsers.get(i));
            if(!auxList.isEmpty())
                usersToNotify.addAll(auxList);
        }

        return usersToNotify;
    }

    private List<UserInterview> checkForUpcomingDates(User user){
        List<InterviewSchedule> allInterviews = user.interviewScheduleList;
        List<UserInterview> interviewsToNotify = new ArrayList<>();
        if(allInterviews.isEmpty())
            return null;
        UserInterview userInterview;
        Date interviewDate = null;
        Date currentDate = new Date();

        for(int i=0; i<allInterviews.size(); i++){
            interviewDate = allInterviews.get(i).date;
            long diff = TimeUnit.DAYS.convert((currentDate.getTime() - interviewDate.getTime()), TimeUnit.MILLISECONDS);

            if(diff <= 1){
                userInterview = new UserInterview(allInterviews.get(i), user.email, user.name);
                interviewsToNotify.add(userInterview);
            }
        }
        return interviewsToNotify;
    }



    public class UserInterview {
        public InterviewSchedule interviewSchedule;
        public String email;
        public String name;

        public UserInterview(){
            this.interviewSchedule = new InterviewSchedule();
        }

        public UserInterview(InterviewSchedule interviewSchedule, String email, String name){
            this.interviewSchedule = interviewSchedule;
            this.email = email;
            this.name = name;
        }
    }

}
