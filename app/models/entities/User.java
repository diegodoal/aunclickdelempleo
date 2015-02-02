package models.entities;

import play.data.validation.Constraints;

/**
 * Created by Victor on 30/01/2015.
 */
public class User {
    // Step 1
    @Constraints.Required()
    @Constraints.Email()
    public String email;

    @Constraints.Required()
    @Constraints.MinLength(4)
    public String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
