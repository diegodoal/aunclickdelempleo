package utils;

import models.datasource.SingletonDataSource;
import models.entities.User;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 22/04/2015.
 */
public class Stats {

    public static Integer[] getUsersWithDrivingLicense(List<User> users){
        int withLicense = 0;
        int totalUsers = users.size();
        for(User user : users){
            if (user.drivingLicense != null){
                withLicense++;
                totalUsers--;
            }
        }
        Integer[] result = new Integer[2];
        result[0] = withLicense;
        result[1] = totalUsers;
        return result;
    }

    public static Integer[] getCertificatesOfDisability(List<User> users){
        int withoutCertificate = users.size();
        int lessThan33Certificate = 0;
        int moreThan33Certificate = 0;
        int moreThan66Certificate = 0;
        for(User user : users){
            if(user.certificateOfDisability != null){
                if(user.certificateOfDisability.equals("Menos del 33%")){
                    lessThan33Certificate++;
                    withoutCertificate--;
                }else if(user.certificateOfDisability.contains("66")){
                    moreThan66Certificate++;
                    withoutCertificate--;
                }else{
                    moreThan33Certificate++;
                    withoutCertificate--;
                }
            }
        }
        Integer[] result = new Integer[4];
        result[0] = withoutCertificate;
        result[1] = lessThan33Certificate;
        result[2] = moreThan33Certificate;
        result[3] = moreThan66Certificate;
        return result;
    }

    public static Integer[] getEducationLevel(List<User> users){

        Integer[] result = new Integer[14];

        for(int i=0; i<result.length; i++){
            result[i] = 0;
        }
        for(User user: users){
            for(String educationLevel: user.currentSituation.educationLevelList){
                if(educationLevel.equals("No tengo estudios"))
                    result[0] = result[0]+1;
                if(educationLevel.equals("Grado"))
                    result[1] = result[1]+1;
                if(educationLevel.contains("PCPI"))
                    result[2] = result[2]+1;
                if(educationLevel.equals("Licenciado / Arquitecto / Ingeniero"))
                    result[3] = result[3]+1;
                if(educationLevel.equals("Certificado de escolaridad / Graduado en ESO"))
                    result[4] = result[4]+1;
                if(educationLevel.contains("propio / Carrera"))
                    result[5] = result[5]+1;
                if(educationLevel.equals("BUP / Bachillerato / COU"))
                    result[6] = result[6]+1;
                if(educationLevel.contains("propio de postgrado"))
                    result[7] = result[7]+1;
                if(educationLevel.contains("FP 1"))
                    result[8] = result[8]+1;
                if(educationLevel.equals("Doctorado"))
                    result[9] = result[9]+1;
                if(educationLevel.contains("FP 2"))
                    result[10] = result[10]+1;
                if(educationLevel.equals("Oposiciones"))
                    result[11] = result[11]+1;
                if(educationLevel.contains("Diplomado"))
                    result[12] = result[12]+1;
                if(educationLevel.equals("Otros Cursos"))
                    result[13] = result[13]+1;
            }
        }

        return result;
    }

    public static int getNumberOfUsersWithFullProfile(List<User> users){
        int result = 0;
        for(User user : users){
            if(user.getCompletedOrientationPercentage() == 100){
                result++;
            }
        }
        return result;
    }
}
