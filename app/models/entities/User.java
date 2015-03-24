package models.entities;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.entities.orientation.*;
import utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Victor on 30/01/2015.
 */
public class User {

    // Step 1
    public String name;
    public String surnames;
    public String email;
    public String password;
    public String emailVerificationKey;
    public String connectionTimestamp;

    public CompletedOrientationSteps completedOrientationSteps;

    //Orientation steps
    public CurrentSituation currentSituation;
    public List<Skill> skills;
    public List<String> interests;
    public List<String> personalCharacteristics;
    public List<ProfessionalValue> professionalValues;
    public Photo photo;
    public List<InterviewSchedule> interviewScheduleList;

    public User(){
        this.emailVerificationKey = UUID.randomUUID().toString();
        //Includes an "a" at the end for more security. Stored with custom format in DB and with standard format in session
        this.connectionTimestamp = new Date().toString();
        this.currentSituation = new CurrentSituation();
        this.skills = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.personalCharacteristics = new ArrayList<>();
        this.professionalValues = new ArrayList<>();
        this.photo = new Photo();
        this.interviewScheduleList = new ArrayList<>();
    }

    public User(String name,String surnames, String email, String password){
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.emailVerificationKey = UUID.randomUUID().toString();
        this.connectionTimestamp = new Date().toString();
        this.completedOrientationSteps = new CompletedOrientationSteps();
        this.currentSituation = new CurrentSituation();
        this.skills = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.personalCharacteristics = new ArrayList<>();
        this.professionalValues = new ArrayList<>();
        this.photo = new Photo();
        this.interviewScheduleList = new ArrayList<>();
    }

    public CompletedOrientationSteps getCompletedOrientationSteps() {
        return completedOrientationSteps;
    }


    public class CompletedOrientationSteps {
        public String currentSituation,
        skills,
        interestIdentification,
        personal,
        professional,
        photo,
        channels,
        learnTools,
        getTools,
        tInterview,
        pInterview,
        actInterview,
        questionsInterview,
        deadLine,
        travel,
        specialization,
        bestDeals,
        level,
        reputation;

        public CompletedOrientationSteps(){
            this.currentSituation = "false";
            this.skills = "false";
            this.interestIdentification = "false";
            this.personal = "false";
            this.professional = "false";
            this.photo = "false";
            this.channels = "false";
            this.learnTools = "false";
            this.getTools = "false";
            this.tInterview = "false";
            this.pInterview = "false";
            this.actInterview = "false";
            this.questionsInterview = "false";
            this.deadLine = "false";
            this.travel = "false";
            this.specialization = "false";
            this.bestDeals = "false";
            this.level = "false";
            this.reputation = "false";
        }

        public String orientationStepsToJson(){
            return new Gson().toJson(this, CompletedOrientationSteps.class).toString();
        }

        public String getCurrentSituation() {
            return currentSituation;
        }

        public String getSkills() {
            return skills;
        }

        public String getInterestIdentification() {
            return interestIdentification;
        }

        public String getProfessional() {
            return professional;
        }

        public String getPersonal() {
            return personal;
        }

        public String getPhoto() {
            return photo;
        }

        public String getChannels() {
            return channels;
        }

        public String getLearnTools() {
            return learnTools;
        }

        public String getGetTools() {
            return getTools;
        }

        public String getTInterview() {
            return tInterview;
        }

        public String getPInterview() {
            return pInterview;
        }

        public String getActInterview() {
            return actInterview;
        }

        public String getQuestionsInterview() {
            return questionsInterview;
        }

        public String getDeadLine() {
            return deadLine;
        }

        public String getTravel() {
            return travel;
        }

        public String getSpecialization() {
            return specialization;
        }

        public String getBestDeals() {
            return bestDeals;
        }

        public String getLevel() {
            return level;
        }

        public String getReputation() {
            return reputation;
        }
    }

    public String skillstoJson(){
        Type listType = new TypeToken<List<Skill>>(){}.getType();
        String result = new Gson().toJson(this.skills, listType);

        return result;
    }

    public String professionalValuesToJson(){
        Type listType = new TypeToken<List<ProfessionalValue>>(){}.getType();
        String result = new Gson().toJson(this.professionalValues, listType);

        return result;
    }

    public String interviewScheduleListToJson(){
        Type listType = new TypeToken<List<InterviewSchedule>>(){}.getType();
        String result = new Gson().toJson(this.interviewScheduleList, listType);

        return result;
    }
}
