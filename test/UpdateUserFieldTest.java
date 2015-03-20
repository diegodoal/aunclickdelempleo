import models.datasource.SingletonDataSource;
import models.entities.User;
import org.junit.After;
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
    public void testUpdate_personalInformation(){
        String fakeName = "fakeName";
        String fakeSurnames = "fakeSurnames";
        String fakeEmail = "fakeEmail";
        String fakePassword = "fakePassword";

        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_NAME, fakeName);
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_SURNAMES, fakeSurnames);
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_PASSWORD, fakePassword);
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_EMAIL, fakeEmail);

        email = fakeEmail;

        assertEquals(fakeName, SingletonDataSource.getInstance().getUserByEmail(email).name);
        assertEquals(fakeSurnames, SingletonDataSource.getInstance().getUserByEmail(email).surnames);
        assertEquals(fakeEmail, SingletonDataSource.getInstance().getUserByEmail(email).email);
        assertEquals(fakePassword, SingletonDataSource.getInstance().getUserByEmail(email).password);
    }

    @Test
    public void testUpdate_orientationSteps(){
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_CURRENT_SITUATION, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_SKILLS, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_INTEREST_IDENTIFICATION, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_PERSONAL, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_PROFESSIONAL, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_PHOTO, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_CHANNELS, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_LEARN_TOOLS, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_GET_TOOLS, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_T_INTERVIEW, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_P_INTERVIEW, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_ACT_INTERVIEW, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_QUESTIONS_INTERVIEW, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_DEADLINE, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_TRAVEL, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_SPECIALIZATION, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_BEST_DEALS, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_LEVEL, String.valueOf(true));
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_ORIENTATION_STEPS_REPUTATION, String.valueOf(true));

        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.currentSituation);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.skills);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.interestIdentification);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.personal);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.professional);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.photo);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.channels);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.learnTools);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.getTools);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.tInterview);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.pInterview);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.actInterview);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.questionsInterview);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.deadLine);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.travel);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.specialization);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.bestDeals);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.level);
        assertEquals(String.valueOf(true), SingletonDataSource.getInstance().getUserByEmail(email).completedOrientationSteps.reputation);

    }

    @Test
    public void testUpdate_currentSituation_educationLevel(){
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.educationLevelList.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_CURRENT_SITUATION_EDUACTION_LEVEL_LIST + "." + i, "Education" + i);

            assertEquals("Education"+i, SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.educationLevelList.get(i));
        }
    }

    @Test
    public void testUpdate_currentSituation_professionalExperience(){
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.professionalExperienceList.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_CURRENT_SITUATION_PROFESSIONAL_EXPERIENCE_LIST + "." + i + "." + Constants.PROFESSIONAL_EXPERIENCE_COMPANY, "Company" + i);
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_CURRENT_SITUATION_PROFESSIONAL_EXPERIENCE_LIST + "." + i + "." + Constants.PROFESSIONAL_EXPERIENCE_JOB, "Job" + i);
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_CURRENT_SITUATION_PROFESSIONAL_EXPERIENCE_LIST + "." + i + "." + Constants.PROFESSIONAL_EXPERIENCE_EXPERIENCE_YEARS, i + " years");

            assertEquals("Company"+i, SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.professionalExperienceList.get(i).company);
            assertEquals("Job"+i, SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.professionalExperienceList.get(i).job);
            assertEquals(i+" years", SingletonDataSource.getInstance().getUserByEmail(email).currentSituation.professionalExperienceList.get(i).experienceYears);
        }
    }

    @Test
    public void testUpdate_skills(){
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).skills.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_SKILLS_LIST + "." + i + "." + Constants.SKILL_NAME, "SkillName" + i);
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_SKILLS_LIST + "." + i + "." + Constants.SKILL_LEVEL, "SkillLevel" + i);
            assertEquals("SkillName" + i, SingletonDataSource.getInstance().getUserByEmail(email).skills.get(i).name);
            assertEquals("SkillLevel" + i, SingletonDataSource.getInstance().getUserByEmail(email).skills.get(i).level);
        }
    }

    @Test
    public void testUpdate_interests(){
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).interests.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_INTERESTS_LIST + "." + i, "Interest" + i);
            assertEquals("Interest"+i, SingletonDataSource.getInstance().getUserByEmail(email).interests.get(i));
        }
    }

    @Test
    public void testUpdate_personalCharacteristics(){
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).personalCharacteristics.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_PERSONAL_CHARACTERISTICS_LIST + "." + i, "Characteristic" + i);
            assertEquals("Characteristic"+i, SingletonDataSource.getInstance().getUserByEmail(email).personalCharacteristics.get(i));
        }
    }

    @Test
    public void testUpdate_professionalValues(){
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).professionalValues.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_PROFESSIONAL_VALUES_LIST + "." + i + "." + Constants.PROFESSIONAL_VALUES_NAME, "ProfessionalValue" + i);
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_PROFESSIONAL_VALUES_LIST + "." + i + "." + Constants.PROFESSIONAL_VALUES_VALUATION, "Valuation" + i);
            assertEquals("ProfessionalValue"+i, SingletonDataSource.getInstance().getUserByEmail(email).professionalValues.get(i).name);
            assertEquals("Valuation"+i, SingletonDataSource.getInstance().getUserByEmail(email).professionalValues.get(i).valuation);
        }
    }

    @Test
    public void testUpdate_photoId(){
        SingletonDataSource.getInstance().updateUserData(email, Constants.USER_PHOTO_ID, "New ID");
        assertEquals("New ID", SingletonDataSource.getInstance().getUserByEmail(email).photo.id);
    }

    @Test
    public void testUpdate_nextInterviews(){
        String fakeDate = "01-01-1990 22:10";
        for(int i=0; i<SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.size(); i++){
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_NEXT_INTERVIEWS_LIST + "." + i + "." + Constants.NEXT_INTERVIEW_DATE, fakeDate);
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_NEXT_INTERVIEWS_LIST + "." + i + "." + Constants.NEXT_INTERVIEW_COMPANY, "Company" + i);
            SingletonDataSource.getInstance().updateUserData(email, Constants.USER_NEXT_INTERVIEWS_LIST + "." + i + "." + Constants.NEXT_INTERVIEW_ADDRESS, "Address" + i);

            assertEquals(fakeDate, SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.get(i).date);
            assertEquals("Company"+i, SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.get(i).company);
            assertEquals("Address"+i, SingletonDataSource.getInstance().getUserByEmail(email).interviewScheduleList.get(i).address);
        }
    }
}
