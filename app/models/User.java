package models;

import play.db.ebean.Model;

public class User extends Model {
	private String username;
	private String password;
	
	private Status current_status;
	private enum Status{ONLY_LOGIN, COMPLETE};
	
	public User(String username, String password) {
		if(validateUsernameAndPassword(username, password) == false)
			return;
		this.username = username.trim();
		this.password = password.trim();
		this.current_status = Status.ONLY_LOGIN;
	}

	
	private boolean validateUsernameAndPassword(String username, String password){
		if(username.trim().equals(null) || password.trim().equals(null)  || password.trim().length()<6){
			return false;
		}	
		return true;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
