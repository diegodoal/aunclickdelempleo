package models.entities;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class ParticularUser {

	// Step 1
	@Required()
	@Email()
	public String email;
	
	@Required()
	@MinLength(4)
	public String password;

	// Step 2
	
	
	public ParticularUser(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
