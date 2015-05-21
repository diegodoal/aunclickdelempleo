package models.entities.orientation;

import com.google.gson.Gson;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class ProfessionalExperience {
    public String company;
    public String job;
    public String startDate;
    public String endDate;
    public String ID;

    public ProfessionalExperience(String company, String job, String startDate, String endDate, String ID){
        this.company = company;
        this.job = job;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ID = ID;
    }

    public String toJsonInString(){
        return new Gson().toJson(this).toString();
    }
}