package models.entities;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class CompanyUser {

	@Required()
	@Email()
	public String email;
	
	@Required()
	@MinLength(4)
	public String password;
	
	
	public CompanyUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
