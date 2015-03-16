import models.datasource.UserDataSource;
import models.entities.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
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
        UserDataSource.insertIntoUsersCollection(user);
    }

    @AfterClass
    public static void tearDownAfterClass(){
        UserDataSource.dropUserCollection();
    }

    @Test
    public void checkName(){
        assertEquals(user.name, UserDataSource.getUserByEmail(email).name);
    }

    @Test
    public void checkSurname(){
        assertEquals(user.surnames, UserDataSource.getUserByEmail(email).surnames);
    }

    @Test
    public void checkEmail(){
        assertEquals(user.email, UserDataSource.getUserByEmail(email).email);
    }

    @Test
    public void checkPassword(){
        assertEquals(user.password, UserDataSource.getUserByEmail(email).password);
    }

    @Test
    public void checkEmailVerificationKey(){
        assertEquals(user.emailVerificationKey, UserDataSource.getUserByEmail(email).emailVerificationKey);
    }

    @Test
    public void checkCurrentSituation_educationLevel(){
        assertEquals(user.currentSituation.educationLevelList.size(), UserDataSource.getUserByEmail(email).currentSituation.educationLevelList.size());

        for(int i=0; i<user.currentSituation.educationLevelList.size(); i++){
            assertEquals(user.currentSituation.educationLevelList.get(i), UserDataSource.getUserByEmail(email).currentSituation.educationLevelList.get(i));
        }
    }

    @Test
    public void checkCurrentSituation_professionalExperience(){
        assertEquals(user.currentSituation.professionalExperienceList.size(), UserDataSource.getUserByEmail(email).currentSituation.professionalExperienceList.size());

        for(int i=0; i<user.currentSituation.professionalExperienceList.size(); i++){
            assertEquals(user.currentSituation.professionalExperienceList.get(i).toJsonInString(), UserDataSource.getUserByEmail(email).currentSituation.professionalExperienceList.get(i).toJsonInString());
        }
    }

    @Test
    public void checkSkills(){
        assertEquals(user.skills.size(), UserDataSource.getUserByEmail(email).skills.size());

        for(int i=0; i<user.skills.size(); i++) {
            assertEquals(user.skills.get(i).level, UserDataSource.getUserByEmail(email).skills.get(i).level);
            assertEquals(user.skills.get(i).name, UserDataSource.getUserByEmail(email).skills.get(i).name);
        }
    }

    @Test
    public void checkInterests(){
        assertEquals(user.interests.size(), UserDataSource.getUserByEmail(email).interests.size());

        for(int i=0; i<user.interests.size(); i++){
            assertEquals(user.interests.get(i), UserDataSource.getUserByEmail(email).interests.get(i));
        }
    }

    @Test
    public void checkPersonalCharacteristics(){
        assertEquals(user.personalCharacteristics.size(), UserDataSource.getUserByEmail(email).personalCharacteristics.size());

        for(int i=0; i<user.personalCharacteristics.size(); i++){
            assertEquals(user.personalCharacteristics.get(i), UserDataSource.getUserByEmail(email).personalCharacteristics.get(i));
        }
    }

    @Test
    public void checkProfessionalValues(){
        assertEquals(user.professionalValues.size(), UserDataSource.getUserByEmail(email).professionalValues.size());

        for(int i=0; i<user.professionalValues.size(); i++){
            assertEquals(user.professionalValues.get(i).name, UserDataSource.getUserByEmail(email).professionalValues.get(i).name);
            assertEquals(user.professionalValues.get(i).valuation, UserDataSource.getUserByEmail(email).professionalValues.get(i).valuation);
        }
    }

    @Test
    public void checkPhoto(){
        assertEquals(user.photo.id, UserDataSource.getUserByEmail(email).photo.id);
    }

    @Test
    public void checkInterviewSchedules(){
        assertEquals(user.interviewScheduleList.size(), UserDataSource.getUserByEmail(email).interviewScheduleList.size());

        for(int i=0; i<user.interviewScheduleList.size(); i++){
            assertEquals(user.interviewScheduleList.get(i).date, UserDataSource.getUserByEmail(email).interviewScheduleList.get(i).date);
            assertEquals(user.interviewScheduleList.get(i).address, UserDataSource.getUserByEmail(email).interviewScheduleList.get(i).address);
            assertEquals(user.interviewScheduleList.get(i).company, UserDataSource.getUserByEmail(email).interviewScheduleList.get(i).company);
        }
    }


}
