package utils;

import models.entities.orientation.InterviewSchedule;

/**
 * Created by Victor on 09/04/2015.
 */
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
