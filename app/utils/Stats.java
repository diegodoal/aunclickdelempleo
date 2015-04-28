package utils;

import models.datasource.SingletonDataSource;
import models.entities.User;

import java.util.List;

/**
 * Created by Victor on 22/04/2015.
 */
public class Stats {

    public static Integer[] getUsersWithDrivingLicense(){
        List<User> users = SingletonDataSource.getInstance().findAll();
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

    public static Integer[] getCertificatesOfDisability(){
        List<User> users = SingletonDataSource.getInstance().findAll();
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
}
