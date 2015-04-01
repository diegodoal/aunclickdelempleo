import com.google.gson.Gson;
import models.datasource.SingletonDataSource;
import models.entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.assertEquals;


/**
 * Created by Victor on 13/03/2015.
 */
public class InsertUserIntoMongoDBTest {
    private static User user;
    private static String email = UtilsTest.email;

    @BeforeClass
    public static void setUpBeforeClass(){
        user = UtilsTest.buildNewFakeUser();
        SingletonDataSource.getInstance().insertIntoUsersCollection(user);
    }

    @AfterClass
    public static void tearDownAfterClass(){
        SingletonDataSource.getInstance().dropUserCollection();
        assertEquals(SingletonDataSource.getInstance().mongoClient.getConnector().isOpen(), false);
    }

    @After
    public void connectionMongoDB(){
        assertEquals(SingletonDataSource.getInstance().mongoClient.getConnector().isOpen(), false);
    }

    @Test
    public void checkName(){
        assertEquals(user.name, SingletonDataSource.getInstance().getUserByEmail(email).name);
    }

    @Test
    public void checkSurname(){
        assertEquals(user.surnames, SingletonDataSource.getInstance().getUserByEmail(email).surnames);
    }

    @Test
    public void checkEmail(){
        assertEquals(user.email, SingletonDataSource.getInstance().getUserByEmail(email).email);
    }

    @Test
    public void checkPassword(){
        assertEquals(Utils.encryptWithSHA1(user.password), SingletonDataSource.getInstance().getUserByEmail(email).password);
    }

    @Test
    public void checkEmailVerificationKey(){
        assertEquals(user.emailVerificationKey, SingletonDataSource.getInstance().getUserByEmail(email).emailVerificationKey);
    }

    @Test
    public void checkCurrentSituation_educationLevel(){
        assertEquals(user.currentSituation.educationLevelList.size(), SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.educationLevelList.size());

        for(int i=0; i<user.currentSituation.educationLevelList.size(); i++){
            assertEquals(user.currentSituation.educationLevelList.get(i), SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.educationLevelList.get(i));
        }
    }

    @Test
    public void checkCurrentSituation_professionalExperience(){
        assertEquals(user.currentSituation.professionalExperienceList.size(), SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.professionalExperienceList.size());

        for(int i=0; i<user.currentSituation.professionalExperienceList.size(); i++){
            assertEquals(user.currentSituation.professionalExperienceList.get(i).toJsonInString(), SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.professionalExperienceList.get(i).toJsonInString());
        }
    }

    @Test
    public void checkSkills(){
        assertEquals(user.skills.size(), SingletonDataSource.getInstance().getUserByEmail(email).skills.size());

        for(int i=0; i<user.skills.size(); i++) {
            assertEquals(user.skills.get(i).level, SingletonDataSource.getInstance().getUserByEmail(email).skills.get(i).level);
            assertEquals(user.skills.get(i).name, SingletonDataSource.getInstance().getUserByEmail(email).skills.get(i).name);
        }
    }

    @Test
    public void checkInterests(){
        assertEquals(user.interests.size(), SingletonDataSource.getInstance().getUserByEmail(email).interests.size());

        for(int i=0; i<user.interests.size(); i++){
            assertEquals(user.interests.get(i), SingletonDataSource.getInstance().getUserByEmail(email).interests.get(i));
        }
    }

    @Test
    public void checkPersonalCharacteristics(){
        assertEquals(user.personalCharacteristics.size(), SingletonDataSource.getInstance().getUserByEmail(email).personalCharacteristics.size());

        for(int i=0; i<user.personalCharacteristics.size(); i++){
            assertEquals(user.personalCharacteristics.get(i), SingletonDataSource.getInstance().getUserByEmail(email).personalCharacteristics.get(i));
        }
    }

    @Test
    public void checkProfessionalValues(){
        assertEquals(user.professionalValues.size(), SingletonDataSource.getInstance().getUserByEmail(email).professionalValues.size());

        for(int i=0; i<user.professionalValues.size(); i++){
            assertEquals(user.professionalValues.get(i).name, SingletonDataSource.getInstance().getUserByEmail(email).professionalValues.get(i).name);
            assertEquals(user.professionalValues.get(i).valuation, SingletonDataSource.getInstance().getUserByEmail(email).professionalValues.get(i).valuation);
        }
    }

    @Test
    public void checkPhoto(){
        assertEquals(user.photo.id, SingletonDataSource.getInstance().getUserByEmail(email).photo.id);
    }

    @Test
    public void checkInterviewSchedules(){
        assertEquals(user.interviewScheduleList.size(), SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.size());

        for(int i=0; i<user.interviewScheduleList.size(); i++){
            assertEquals(user.interviewScheduleList.get(i).date, SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.get(i).date);
            assertEquals(user.interviewScheduleList.get(i).address, SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.get(i).address);
            assertEquals(user.interviewScheduleList.get(i).company, SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.get(i).company);
        }
    }

    @Test
    public void checkAllUsers(){
        assertEquals(SingletonDataSource.getInstance().getAllUsers().size(), 1);
        user.password = Utils.encryptWithSHA1(user.password);
        assertEquals(new Gson().toJson(SingletonDataSource.getInstance().getAllUsers().get(0)), new Gson().toJson(user));
    }


}
