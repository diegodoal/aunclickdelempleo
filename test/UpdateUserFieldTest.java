import models.datasource.UserDataSource;
import models.entities.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Constants;

import static org.junit.Assert.assertEquals;

/**
 * Created by Victor on 13/03/2015.
 */
public class UpdateUserFieldTest {

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
    public void testUpdate_personalInformation(){
        String fakeName = "fakeName";
        String fakeSurnames = "fakeSurnames";
        String fakeEmail = "fakeEmail";
        String fakePassword = "fakePassword";

        UserDataSource.updateUserData(email, Constants.USER_NAME, fakeName);
        UserDataSource.updateUserData(email, Constants.USER_SURNAMES, fakeSurnames);
        UserDataSource.updateUserData(email, Constants.USER_PASSWORD, fakePassword);
        UserDataSource.updateUserData(email, Constants.USER_EMAIL, fakeEmail);

        email = fakeEmail;

        assertEquals(fakeName, UserDataSource.getUserByEmail(email).name);
        assertEquals(fakeSurnames, UserDataSource.getUserByEmail(email).surnames);
        assertEquals(fakeEmail, UserDataSource.getUserByEmail(email).email);
        assertEquals(fakePassword, UserDataSource.getUserByEmail(email).password);
    }

    @Test
    public void testUpdate_orientationSteps(){
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_CURRENT_SITUATION, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_SKILLS, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_INTEREST_IDENTIFICATION, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_PERSONAL, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_PROFESSIONAL, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_PHOTO, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_CHANNELS, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_LEARN_TOOLS, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_GET_TOOLS, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_T_INTERVIEW, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_P_INTERVIEW, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_ACT_INTERVIEW, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_QUESTIONS_INTERVIEW, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_DEADLINE, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_TRAVEL, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_SPECIALIZATION, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_BEST_DEALS, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_LEVEL, String.valueOf(true));
        UserDataSource.updateUserData(email, Constants.USER_ORIENTATION_STEPS_REPUTATION, String.valueOf(true));

        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.currentSituation);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.skills);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.interestIdentification);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.personal);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.professional);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.photo);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.channels);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.learnTools);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.getTools);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.tInterview);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.pInterview);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.actInterview);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.questionsInterview);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.deadLine);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.travel);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.specialization);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.bestDeals);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.level);
        assertEquals(String.valueOf(true), UserDataSource.getUserByEmail(email).completedOrientationSteps.reputation);

    }
}
