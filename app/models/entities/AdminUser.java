package models.entities;

import java.util.Date;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
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
