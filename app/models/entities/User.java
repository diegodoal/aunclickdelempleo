package models.entities;


import com.google.gson.Gson;

import java.util.ArrayList;
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

    public CompletedOrientationSteps completedOrientationSteps;

    public User(){}

    public User(String name,String surnames, String email, String password){
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.emailVerificationKey = UUID.randomUUID().toString();
        completedOrientationSteps = new CompletedOrientationSteps();
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
    }
}
