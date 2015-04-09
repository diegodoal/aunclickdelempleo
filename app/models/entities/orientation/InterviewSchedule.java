package models.entities.orientation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Victor on 05/03/2015.
 */
public class InterviewSchedule {

    public String date;
    public String company;
    public String address;
    public String notified;

    public InterviewSchedule(){
        this.notified = "false";
    }

    public InterviewSchedule(String date, String company, String address){
        this.date = date;
        this.company = company;
        this.address = address;
        this.notified = "false";
    }

    public String getInterviewDate(){
        return date.substring(0, 10);
    }

    public String getInterviewTime(){
        return date.substring(11, 16);
    }

}
