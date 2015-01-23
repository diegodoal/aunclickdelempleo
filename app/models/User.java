package models;

public class User  {
	
	private String email;
	private String password;
	private String repeat_password;
	
	private Status current_status;
	enum Status{ONLY_LOGIN, COMPLETE};
	
	public User(String email, String password, String repeat_password) {
		if(validateEmail(email) == false)
			return;
		if(validatePassword(password, repeat_password) == false)
			return;
		this.email = email.trim();
		this.password = password.trim();
		
		
	}

	
	private boolean validateEmail(String email){
		if(email.trim().equals(null)){
			return false;
		}	
		return true;
	}
	
	private boolean validatePassword(String password, String repeat_password){
		if(!password.equals(repeat_password))
			return false;
		if(password.trim().equals(null)  || password.trim().length()<6)
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeat_password() {
		return repeat_password;
	}
	public void setRepeat_password(String repeat_password) {
		this.repeat_password = repeat_password;
	}
	public Status getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(Status current_status) {
		this.current_status = current_status;
	}
}
