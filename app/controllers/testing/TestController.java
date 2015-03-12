package controllers.testing;

import com.google.gson.Gson;
import models.datasource.UserDataSource;
import models.entities.orientation.InterviewSchedule;
import models.entities.orientation.CurrentSituation;
import models.entities.orientation.ProfessionalValue;
import models.entities.orientation.Skill;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;
import utils.EmailChecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Victor on 11/03/2015.
 */
public class TestController extends Controller {

    public static int counter = 0;

    public static Result testDataSource1(){

        CurrentSituation currentSituation = new CurrentSituation();

        currentSituation.addEducationLevel("Primaria");
        currentSituation.addEducationLevel("Secundaria");
        currentSituation.addEducationLevel("Grado");

        currentSituation.addProfessionalExperience("Telefonica", "Talentum", "5 años");
        currentSituation.addProfessionalExperience("Microsoft", "CEO", "10 años");


        return ok(currentSituation.toJsonString());
    }

    public static Result testDataSourceToMongo1(){
        counter++;
        User user = new User("Name"+counter, "Surname"+counter, "email"+counter+"@gmail.com", "password"+counter);

        //Current Situation
        user.currentSituation.addEducationLevel("Primaria");
        user.currentSituation.addEducationLevel("Secundaria");
        user.currentSituation.addEducationLevel("Grado");

        user.currentSituation.addProfessionalExperience("Telefonica", "Talentum", "5 años");
        user.currentSituation.addProfessionalExperience("Microsoft", "CEO", "10 años");

        //Skills
        user.skills.add(new Skill("Hablar en público", "Bien"));
        user.skills.add(new Skill("Picar código", "Muy bien"));

        //Interests
        user.interests.add("Informática");
        user.interests.add("Electrónica");

        //Personal Characteristics
        user.personalCharacteristics.add("Trabajador");
        user.personalCharacteristics.add("Creativo");
        user.personalCharacteristics.add("Productivo");

        //Professional Values
        user.professionalValues.add(new ProfessionalValue("Responsabilidad y toma de decisiones", "Si"));
        user.professionalValues.add(new ProfessionalValue("Reconocimiento de la lealtad a la empresa", "No sé"));

        //Photo
        user.photo.id = UUID.randomUUID().toString();

        //Next Interviews
        Date date = null;
        user.interviewScheduleList.add(new InterviewSchedule("18-03-2015 20:45", "Telefonica", "Madrid International Lab"));
        user.interviewScheduleList.add(new InterviewSchedule("13-03-2015 17:45", "Apple", "Palo Alto"));

        return ok(new Gson().toJson(UserDataSource.insertIntoUsersCollection(user)).toString());
    }

    public static Result testDataSourceUpdating() {

        String email = "email1@gmail.com";
        UserDataSource.updateUserData(email, "interests.0", "true");

        UserDataSource.updateUserData(email, "professionalValues.1.valuation", "Other value");

        UserDataSource.updateUserData(email, "nextInterviews.1.address", "Alemania");

        UserDataSource.deleteUserData(email, "interests");

        return ok(new Gson().toJson(UserDataSource.getUserByEmail(email)));
    }

    public static Result testNextInterviews() {
        EmailChecker emailChecker = new EmailChecker();

        /**
         * Recives the days before the interview
         */
        List<EmailChecker.UserInterview> usersToNotify = emailChecker.getUsersWithNextInterviews(1);

        String result = "";

        for(int i=0; i<usersToNotify.size(); i++){
            result+= usersToNotify.get(i).email + " : "+usersToNotify.get(i).interviewSchedule.date.toString() +"\n";
        }

        return ok(result);
    }

    public static Result printUserInterviews(){
        User user = UserDataSource.getUserByEmail("email1@gmail.com");

        return ok(user.interviewScheduleListToJson());
    }

    public static Result initFakeUsersDB(){
        for(int counter=0; counter<500; counter++){
            User user = new User("Name"+counter, "Surname"+counter, "email"+counter+"@gmail.com", "password"+counter);

            //Current Situation
            user.currentSituation.addEducationLevel("Primaria");
            user.currentSituation.addEducationLevel("Secundaria");
            user.currentSituation.addEducationLevel("Grado");

            user.currentSituation.addProfessionalExperience("Telefonica", "Talentum", "5 años");
            user.currentSituation.addProfessionalExperience("Microsoft", "CEO", "10 años");

            //Skills
            user.skills.add(new Skill("Hablar en público", "Bien"));
            user.skills.add(new Skill("Picar código", "Muy bien"));

            //Interests
            user.interests.add("Informática");
            user.interests.add("Electrónica");

            //Personal Characteristics
            user.personalCharacteristics.add("Trabajador");
            user.personalCharacteristics.add("Creativo");
            user.personalCharacteristics.add("Productivo");

            //Professional Values
            user.professionalValues.add(new ProfessionalValue("Responsabilidad y toma de decisiones", "Si"));
            user.professionalValues.add(new ProfessionalValue("Reconocimiento de la lealtad a la empresa", "No sé"));

            //Photo
            user.photo.id = UUID.randomUUID().toString();

            //Next Interviews
            Date date = null;
            int randomDay = new Random().nextInt((30-1)+1)+1;
            int randomMonth = new Random().nextInt((12-1)+1)+1;
            user.interviewScheduleList.add(new InterviewSchedule(randomDay+"-"+randomMonth+"-2015 20:45", "Telefonica", "Madrid International Lab"));
            randomDay = new Random().nextInt((30-1)+1)+1;
            randomMonth = new Random().nextInt((12-1)+1)+1;
            user.interviewScheduleList.add(new InterviewSchedule(randomDay+"-"+randomMonth+"-2015 17:45", "Apple", "Palo Alto"));

            UserDataSource.insertIntoUsersCollection(user);
        }
        return ok("Initialized fake mongoCollection");
    }
}
