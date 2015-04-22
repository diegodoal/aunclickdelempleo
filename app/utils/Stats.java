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
}
