package models.entities;


/**
 * Created by Victor on 30/01/2015.
 */
public class User {

    // Step 1
    public String email;

    public String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
