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
		ParticularUser query = ParticularUserDataSource.getParticularUser(email);
		if(query==null)
			return badRequest("No existe el usuario con email: "+email);
		else
			return ok(query.showUserInfo());
	}
	
	public static Result verifyParticularUser(String email, String emailVerificationKey){
		ParticularUser query = ParticularUserDataSource.getParticularUser(email);
		if(query == null){
			return badRequest("Error, el email indicado no está registrado. Contacte con el administrador");
		}else if(query.emailVerificationKey == null){
			return badRequest("Atención, el usuario ya está verificado");
		}else if(query.emailVerificationKey.equals(emailVerificationKey)){
			ParticularUserDataSource.updateEmailVerificationKey(email);
			return ok("Enhorabuena! Cuenta validada correctamente");
		}else{
			return badRequest("Error, la clave indicada no se corresponde con la registrada. Contacte con el administrador");
		}
	}

	public static Result initializeParticularUserDB(){
		ParticularUserDataSource.initializeParticularUsersDB();
		return ok("Base de datos ParticularUser inicializada correctamente");
	}

}
