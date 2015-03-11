package controllers.testing;

import com.google.gson.Gson;
import models.datasource.UserDataSource;
import models.entities.InterviewSchedule;
import models.entities.orientation.CurrentSituation;
import models.entities.orientation.ProfessionalValue;
import models.entities.orientation.Skill;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;
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
        user.interviewScheduleList.add(new InterviewSchedule(null, null, "Telefonica", "Madrid International Lab"));
        user.interviewScheduleList.add(new InterviewSchedule(null, null, "Apple", "Palo Alto"));

        return ok(new Gson().toJson(UserDataSource.insertIntoUsersCollection(user)).toString());
    }


}
