package models.entities;

import java.util.UUID;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.libs.Json;

public class ParticularUser {

	// Step 1
    @Required()
    public String name;

    @Required()
    public String surnames;
    
	@Required()
	@Email()
	public String email;
	
	@Required()
	@MinLength(4)
	public String password;

	public String emailVerificationKey;

	// Step 2
	public ParticularUser() {
		
	}
	
	// Para el signUplogin: Añadido por Mikel
	public ParticularUser(String name, String surnames, String email, String password) {
		this.name  = name;
		this.surnames = surnames;
		this.email = email;
		this.password = password;
        this.emailVerificationKey = UUID.randomUUID().toString();
	}
	
	public ParticularUser(String name, String email, String password) {
		this.name = name;
        this.email = email;
		this.password = password;
		this.emailVerificationKey = UUID.randomUUID().toString();
	}
	
	public String showUserInfo(){
		return Json.toJson(this).toString();
	}
}
