package utils;

import com.itextpdf.text.BaseColor;

public final class Constants {
	public static final String MONGO_PARTICULAR_USERS_COLLECTION = "mongo.particularUsersCollection";
	public static final String MONGO_COMPANY_USERS_COLLECTION = "mongo.companyUsersCollection";
	public static final String MONGO_JOBS_COLLECTION = "mongo.jobsCollection";
	public static final String MONGO_COURSES_COLLECTION = "mongo.coursesCollection";

    //Template 1
    public final static BaseColor BASE_COLOR_CUSTOM_BLUE_1 = new BaseColor(67, 137, 215);
    public final static String FONT_TEMPLATE_1 = "Calibri";

    //Template 2
    public final static BaseColor T2_BASE_COLOR_CUSTOM_DARK_GREY = new BaseColor(60, 60, 59);
    public final static float T2_FONT_SIZE_TITLE_1 = 20;
    public final static float T2_FONT_SIZE_TITLE_2 = 14.5f;
    public final static float T2_FONT_SIZE_TEXT = 10;
    public final static String T2_FONT_STYLE = "Arial";


    //USER COLLECTION FIELDS
    public final static String USER_NAME = "name";
    public final static String USER_SURNAMES = "surnames";
    public final static String USER_EMAIL = "email";
    public final static String USER_PASSWORD = "password";
    public final static String USER_EMAIL_VERIFICATION_KEY = "emailVerificationKey";
    public final static String USER_CONNECTION_TIMESTAMP = "connectionTimestamp";

    public final static String USER_RESTORE_PASSWORD_TOKEN = "restorePasswordToken";
    public final static String USER_RESTORE_PASSWORD_TIMESTAMP = "restorePasswordTimestamp";

    public final static String USER_BIRTH_DATE = "birthDate";
    public final static String USER_RESIDENCE_CITY = "residenceCity";
    public final static String USER_RESIDENCE_ADDRESS = "residenceAddress";
    public final static String USER_RESIDENCE_NUMBER = "residenceNumber";
    public final static String USER_RESIDENCE_ZIP_CODE = "residenceZipCode";
    public final static String USER_PHONE_NUMBER = "phoneNumber";

    public final static String USER_STUDY_TITLE = "studyTitle";
    public final static String USER_STUDY_LOCATION = "studyLocation";

    public final static String USER_DRIVING_LICENSE = "drivingLicense";
    public final static String USER_CERTIFICATE_OF_DISABILITY = "certificateOfDisability";

    public final static String USER_ORIENTATION_STEPS = "orientationSteps";
    public final static String USER_ORIENTATION_STEPS_CURRENT_SITUATION = "orientationSteps.currentSituation";
    public final static String USER_ORIENTATION_STEPS_SKILLS = "orientationSteps.skills";
    public final static String USER_ORIENTATION_STEPS_INTEREST_IDENTIFICATION = "orientationSteps.interestIdentification";
    public final static String USER_ORIENTATION_STEPS_PERSONAL = "orientationSteps.personal";
    public final static String USER_ORIENTATION_STEPS_PROFESSIONAL = "orientationSteps.professional";
    public final static String USER_ORIENTATION_STEPS_PHOTO = "orientationSteps.photo";
    public final static String USER_ORIENTATION_STEPS_CHANNELS = "orientationSteps.channels";
    public final static String USER_ORIENTATION_STEPS_LEARN_TOOLS = "orientationSteps.learnTools";
    public final static String USER_ORIENTATION_STEPS_GET_TOOLS = "orientationSteps.getTools";
    public final static String USER_ORIENTATION_STEPS_T_INTERVIEW = "orientationSteps.tInterview";
    public final static String USER_ORIENTATION_STEPS_P_INTERVIEW = "orientationSteps.pInterview";
    public final static String USER_ORIENTATION_STEPS_ACT_INTERVIEW = "orientationSteps.actInterview";
    public final static String USER_ORIENTATION_STEPS_QUESTIONS_INTERVIEW = "orientationSteps.questionsInterview";
    public final static String USER_ORIENTATION_STEPS_DEADLINE = "orientationSteps.deadLine";
    public final static String USER_ORIENTATION_STEPS_TRAVEL = "orientationSteps.travel";
    public final static String USER_ORIENTATION_STEPS_SPECIALIZATION = "orientationSteps.specialization";
    public final static String USER_ORIENTATION_STEPS_BEST_DEALS = "orientationSteps.bestDeals";
    public final static String USER_ORIENTATION_STEPS_LEVEL = "orientationSteps.level";
    public final static String USER_ORIENTATION_STEPS_REPUTATION = "orientationSteps.reputation";

    public final static String USER_CURRENT_SITUATION = "currentSituation";
    public final static String USER_CURRENT_SITUATION_EDUACTION_LEVEL_LIST = "currentSituation.educationLevelList";

    public final static String USER_CURRENT_SITUATION_PROFESSIONAL_EXPERIENCE_LIST = "currentSituation.professionalExperienceList";
        public final static String PROFESSIONAL_EXPERIENCE_COMPANY = "company";
        public final static String PROFESSIONAL_EXPERIENCE_JOB = "job";
        public final static String PROFESSIONAL_EXPERIENCE_EXPERIENCE_YEARS = "experienceYears";
        
    public final static String USER_SKILLS_LIST = "skills";
        public final static String SKILL_NAME = "name";
        public final static String SKILL_LEVEL = "level";
        
    public final static String USER_INTERESTS_LIST = "interests";
    public final static String USER_PERSONAL_CHARACTERISTICS_LIST = "personalCharacteristics";

    public final static String USER_PROFESSIONAL_VALUES_LIST = "professionalValues";
        public final static String PROFESSIONAL_VALUES_NAME = "name";
        public final static String PROFESSIONAL_VALUES_VALUATION = "valuation";


    public final static String USER_PHOTO = "photo";
    public final static String USER_PHOTO_ID = "photo.id";

    public final static String USER_NEXT_INTERVIEWS_LIST = "nextInterviews";
        public final static String NEXT_INTERVIEW_DATE = "date";
        public final static String NEXT_INTERVIEW_COMPANY = "company";
        public final static String NEXT_INTERVIEW_ADDRESS = "address";
        public final static String NEXT_INTERVIEW_NOTIFIED = "notified";
}