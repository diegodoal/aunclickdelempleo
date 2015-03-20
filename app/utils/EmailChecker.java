package utils;

import models.datasource.SingletonDataSource;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Victor on 12/03/2015.
 */
public class EmailChecker {

    public List<UserInterview> getUsersWithNextInterviews(int daysBefore){
        SingletonDataSource singletonDataSource = SingletonDataSource.getInstance();
        List<User> allUsers = singletonDataSource.getAllUsers();
        List<UserInterview> usersToNotify = new ArrayList<>();

        List<UserInterview> auxList = new ArrayList<>();
        for(int i=0; i<allUsers.size(); i++){
            auxList = checkForUpcomingDates(allUsers.get(i), daysBefore);
            if(!auxList.isEmpty())
                usersToNotify.addAll(auxList);
        }

        if(usersToNotify.isEmpty()){
            return null;
        }else{
            return usersToNotify;
        }
    }

    private List<UserInterview> checkForUpcomingDates(User user, int daysBefore){
        SingletonDataSource singletonDataSource = SingletonDataSource.getInstance();
        List<InterviewSchedule> allInterviews = user.interviewScheduleList;
        List<UserInterview> interviewsToNotify = new ArrayList<>();
        if(allInterviews.isEmpty())
            return null;
        UserInterview userInterview;
        Date interviewDate = null;
        Date currentDate = new Date();

        for(int i=0; i<allInterviews.size(); i++){
            if(allInterviews.get(i).notified.equals("false")) {
                interviewDate = Other.stringToDate(allInterviews.get(i).date);
                long diff = TimeUnit.DAYS.convert((interviewDate.getTime() - currentDate.getTime()), TimeUnit.MILLISECONDS);

                if (diff <= daysBefore && diff > 0) {
                    userInterview = new UserInterview(allInterviews.get(i), user.email, user.name);
                    singletonDataSource.updateUserData(user.email, Constants.USER_NEXT_INTERVIEWS_LIST + "." + i + "." + Constants.NEXT_INTERVIEW_NOTIFIED, "true");
                    interviewsToNotify.add(userInterview);
                }
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
