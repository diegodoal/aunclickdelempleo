package models.entities;

import java.util.Date;

/**
 * Created by Victor on 05/03/2015.
 */
public class InterviewSchedule {

    public Date date;
    public Date hour;
    public String company;
    public String address;

    public InterviewSchedule(){

    }

    public InterviewSchedule(Date date, Date hour, String company, String address){
        this.date = date;
        this.hour = hour;
        this.company = company;
        this.address = address;
    }
}
