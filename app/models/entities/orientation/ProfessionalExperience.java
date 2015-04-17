package models.entities.orientation;

import com.google.gson.Gson;

/**
 * Created by Victor on 16/04/2015.
 */
public class ProfessionalExperience {
    public String company;
    public String job;
    public String startDate;
    public String endDate;

    public ProfessionalExperience(String company, String job, String startDate, String endDate){
        this.company = company;
        this.job = job;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toJsonInString(){
        return new Gson().toJson(this).toString();
    }
}