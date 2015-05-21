package utils;

import models.entities.orientation.InterviewSchedule;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
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
