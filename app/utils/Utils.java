package utils;

import models.datasource.SingletonDataSource;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;
import models.entities.orientation.ProfessionalValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Victor on 16/03/2015.
 */
public class Utils {

    public static String formatDateToCustomPattern(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date result = null;

        try {
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encryptWithSHA1(String input) {
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static boolean checkEqualTimestamps(String userTimestamp, String sessionTimestamp){
        if(userTimestamp == null || sessionTimestamp == null)
            return false;
        return userTimestamp.equals(sessionTimestamp);
    }

    /**
     *
     * @param date1
     * @param date2
     * @return Difference between Date2 and Date1
     */
    public static long getDiffBetweenTwoDates(Date date1, Date date2){
        long diff = TimeUnit.DAYS.convert((date2.getTime() - date1.getTime()), TimeUnit.MILLISECONDS);

        return diff;
    }

    public static void initFakeDBCollection() {
        for (int counter = 0; counter < 500; counter++) {
            User user = new User("Name" + counter, "Surname" + counter, "email" + counter + "@gmail.com", "password" + counter);

            //Current Situation
            user.currentSituation.addEducationLevel("Primaria");
            user.currentSituation.addEducationLevel("Secundaria");
            user.currentSituation.addEducationLevel("Grado");
            String expID =  UUID.randomUUID().toString();
            user.currentSituation.addProfessionalExperience("Telefonica", "Talentum", "2014-10", "2015-05",expID);
            String expID2 =  UUID.randomUUID().toString();
            user.currentSituation.addProfessionalExperience("Microsoft", "CEO", "2015-05", "2016-03",expID2);

            //Skills
            //user.skills.add(new Skill("Hablar en público", "Bien"));
            //user.skills.add(new Skill("Picar código", "Muy bien"));

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
            int randomDay = new Random().nextInt((30 - 1) + 1) + 1;
            int randomMonth = new Random().nextInt((12 - 1) + 1) + 1;
            user.interviewScheduleList.add(new InterviewSchedule(randomDay + "-" + randomMonth + "-2015 20:45", "Telefonica", "Madrid International Lab"));
            randomDay = new Random().nextInt((30 - 1) + 1) + 1;
            randomMonth = new Random().nextInt((12 - 1) + 1) + 1;
            user.interviewScheduleList.add(new InterviewSchedule(randomDay + "-" + randomMonth + "-2015 17:45", "Apple", "Palo Alto"));

            SingletonDataSource.getInstance().insertIntoUsersCollection(user);
        }
    }
}
