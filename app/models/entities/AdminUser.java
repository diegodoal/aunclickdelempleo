package models.entities;

import java.util.Date;

/**
 * Created by Victor on 24/04/2015.
 */
public class AdminUser {

    public String name;
    public String password;
    public String connectionTimestamp;

    public AdminUser(){
        this.connectionTimestamp = new Date().toString();
    }

    public AdminUser(String name, String password){
        this.name = name;
        this.password = password;
        this.connectionTimestamp = new Date().toString();
    }
}
