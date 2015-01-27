package models.entities;

import java.util.UUID;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.libs.Json;

public class ParticularUser {

	// Step 1
	@Required()
	@Email()
	public String email;
	
	@Required()
	@MinLength(4)
	public String password;
	
	public String emailVerificationKey;

	// Step 2
	
	public ParticularUser() {}
	
	public ParticularUser(String email, String password) {
		this.email = email;
		this.password = password;
		this.emailVerificationKey = UUID.randomUUID().toString();
	}
	
	public String showUserInfo(){
		return Json.toJson(this).toString();
	}
}
