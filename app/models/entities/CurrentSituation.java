package models.entities;

import java.util.List;

/**
 * Created by Victor on 05/03/2015.
 */
public class CurrentSituation {

    public List<String> educationLevelList;
    public List<ProfessionalExperience> professionalExperienceList;

    public CurrentSituation(List<String> educationLevelList, List<ProfessionalExperience> professionalExperienceList){
        this.educationLevelList = educationLevelList;
        this.professionalExperienceList = professionalExperienceList;
    }

    public class ProfessionalExperience {
        public String company;
        public String job;
        public String experienceYears;

        public ProfessionalExperience(String company, String job, String experienceYears){
            this.company = company;
            this.job = job;
            this.experienceYears = experienceYears;
        }
    }
}
