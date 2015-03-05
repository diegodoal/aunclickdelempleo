package models.entities;

import java.util.Date;

/**
 * Created by Victor on 05/03/2015.
 */
public class InterviewSchedule {

    public String sector;
    public String job;
    public Date date;

    public InterviewSchedule(String sector, String job, Date date){
        this.sector = sector;
        this.job = job;
        this.date = date;
    }
}
