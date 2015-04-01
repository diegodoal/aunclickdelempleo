package models.entities.orientation;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 05/03/2015.
 */
public class CurrentSituation {

    public List<String> educationLevelList;
    public List<ProfessionalExperience> professionalExperienceList;


    public CurrentSituation(){
        this.educationLevelList = new ArrayList<>();
        this.professionalExperienceList = new ArrayList<>();
    }

    public CurrentSituation(List<String> educationLevelList, List<ProfessionalExperience> professionalExperienceList){
        this.educationLevelList = educationLevelList;
        this.professionalExperienceList = professionalExperienceList;
    }


    public void addEducationLevel(String level){
        this.educationLevelList.add(level);
    }

    public void addProfessionalExperience(String company, String job, String experienceYears){
        this.professionalExperienceList.add(new ProfessionalExperience(company, job, experienceYears));
    }

    public String toJsonString(){
        return new Gson().toJson(this).toString();
    }


    /**
     * PROFESSIONAL EXPERIENCE CLASS
     */
    public class ProfessionalExperience {
        public String company;
        public String job;
        public String experienceYears;

        public ProfessionalExperience(String company, String job, String experienceYears){
            this.company = company;
            this.job = job;
            this.experienceYears = experienceYears;
        }

        public String toJsonInString(){
            return new Gson().toJson(this).toString();
        }
    }
}
