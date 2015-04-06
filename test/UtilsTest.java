import models.entities.User;
import models.entities.orientation.InterviewSchedule;
import models.entities.orientation.ProfessionalValue;
import models.entities.orientation.Skill;

import java.util.UUID;

/**
 * Created by Victor on 13/03/2015.
 */
public class UtilsTest {

    public static String email = "testMongoDB@fakeuser.com";

    public static User buildNewFakeUser(){
        User user = new User("Name", "Surname", email, "password");
        //Current Situation - Education Level
        user.currentSituation.addEducationLevel("Primaria");
        user.currentSituation.addEducationLevel("Secundaria");

        //Current Situation - Professional Experience
        user.currentSituation.addProfessionalExperience("Telefonica", "Talentum", "5 años");
        user.currentSituation.addProfessionalExperience("Microsoft", "CEO", "10 años");

        //Skills
        user.skill.add(new Skill("Hablar en público", "Bien"));
        user.skill.add(new Skill("Picar código", "Muy bien"));

        //Interests
        user.interests.add("Informática");
        user.interests.add("Electrónica");

        //Personal Characteristics
        user.personalCharacteristics.add("Trabajador");
        user.personalCharacteristics.add("Creativo");

        //Professional Values
        user.professionalValues.add(new ProfessionalValue("Responsabilidad y toma de decisiones", "Si"));
        user.professionalValues.add(new ProfessionalValue("Reconocimiento de la lealtad a la empresa", "No sé"));

        //Next Interviews
        user.interviewScheduleList.add(new InterviewSchedule("18-03-2015 20:45", "Telefonica", "Madrid International Lab"));
        user.interviewScheduleList.add(new InterviewSchedule("13-03-2015 17:45", "Apple", "Palo Alto"));


        //Photo
        user.photo.id = UUID.randomUUID().toString();

       return user;
    }

}
