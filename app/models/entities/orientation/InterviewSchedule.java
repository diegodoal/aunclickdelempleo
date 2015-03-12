package models.entities.orientation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Victor on 05/03/2015.
 */
public class InterviewSchedule {

    public Date date;
    public String company;
    public String address;

    public InterviewSchedule(){

    }

    public InterviewSchedule(String date, String company, String address){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.company = company;
        this.address = address;
    }
}
