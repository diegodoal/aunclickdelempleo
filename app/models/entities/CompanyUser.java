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
	
	public CompanyUser() {}
	
	public CompanyUser(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String showUserInfo(){
		String info = "";
		info += "Email: "+this.email;
		info += "\nPassword: "+this.password;
		info += "\n";
		
		return info;
	}

}
