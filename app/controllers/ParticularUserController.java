package controllers;

import java.util.List;

import models.datasource.ParticularUserDataSource;
import models.entities.ParticularUser;

import com.mongodb.DBObject;

import play.mvc.Controller;
import play.mvc.Result;

public class ParticularUserController extends Controller{

	// PARTICULAR USERS
	public static Result newParticularUser(String email, String password){
		ParticularUser particularUser = new ParticularUser(email, password);
		ParticularUserDataSource.insertIntoParticularUser(particularUser);
		return ok("Usuario: "+email + " con contraseña: "+password+" añadido.");
	}

	public static Result listParticularUsers(){
		List<DBObject> all = ParticularUserDataSource.getAllParticularUsers();
		String users = "";
		for(int i=0; i<all.size(); i++){
			users+=all.get(i)+"\n";
		}
		return ok(users);
	}

	public static Result findParticularUser(String email){
		DBObject query = ParticularUserDataSource.getParticularUser(email);
		if(query==null)
			return badRequest("No existe el usuario con email: "+email);
		else
			return ok(query.toString());
	}

	public static Result initializeParticularUserDB(){
		ParticularUserDataSource.initializeParticularUsersDB();
		return ok("Base de datos ParticularUser inicializada correctamente");
	}

}
