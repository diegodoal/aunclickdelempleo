package models.entities.orientation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Victor on 05/03/2015.
 */
public class InterviewSchedule {

    public String id;
    public String date;
    public String company;
    public String address;
    public String notified;
    public Date creationDate;
    public Date modificationDate;

    public InterviewSchedule(){
        this.id = UUID.randomUUID().toString();
        this.notified = "false";
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public InterviewSchedule(String date, String company, String address){
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.company = company;
        this.address = address;
        this.notified = "false";
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public String getInterviewDate(){
        return date.substring(0, 10);
    }

    public String getInterviewTime(){
        return date.substring(11, 16);
    }


    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof InterviewSchedule)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        InterviewSchedule interviewSchedule = (InterviewSchedule) o;

        // Compare the data members and return accordingly
        if(interviewSchedule.date.equals(this.date) && interviewSchedule.address.equals(this.address) && interviewSchedule.company.equals(this.company)){
            return true;
        }else{
            return false;
        }
    }
}
