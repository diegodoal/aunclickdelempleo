package controllers;

import java.util.List;

import models.datasource.CompanyUserDataSource;
import models.entities.CompanyUser;
import play.mvc.Controller;
import play.mvc.Result;

import com.mongodb.DBObject;

public class CompanyUserController extends Controller {

	// COMPANY USERS
	public static Result newCompanyUser(String email, String password){
		CompanyUser query = CompanyUserDataSource.getCompanyUser(email);
		if(query != null){
			return badRequest("Ya existe un usuario con ese email registrado, inténtelo con otro...");
		}

		CompanyUser companyUser = new CompanyUser(email, password);
		CompanyUserDataSource.insertIntoCompanyUser(companyUser);
		return redirect("/company/sendvalidation/"+email);
	}

	public static Result listCompanyUsers(){
		List<DBObject> all = CompanyUserDataSource.getAllCompanyUsers();
		String users = "";
		for(int i=0; i<all.size(); i++){
			users+=all.get(i)+"\n";
		}
		return ok(users);
	}

	public static Result findCompanyUser(String email){
		CompanyUser query = CompanyUserDataSource.getCompanyUser(email);
		if(query==null)
			return badRequest("No existe el usuario con email: "+email);
		else
			return ok(query.showUserInfo());
	}

	public static Result verifyCompanyUser(String email, String emailVerificationKey){
		CompanyUser query = CompanyUserDataSource.getCompanyUser(email);
		if(query == null){
			return badRequest("Error, el email indicado no está registrado. Contacte con el administrador");
		}else if(query.emailVerificationKey == null){
			return badRequest("Atención, el usuario ya está verificado");
		}else if(query.emailVerificationKey.equals(emailVerificationKey)){
			CompanyUserDataSource.updateEmailVerificationKey(email);
			return ok("Enhorabuena! Cuenta validada correctamente");
		}else{
			return badRequest("Error, la clave indicada no se corresponde con la registrada. Contacte con el administrador");
		}
	}

	public static Result initializeCompanyUserDB(){
		CompanyUserDataSource.initializeCompanyUsersDB();
		return ok("Base de datos CompanyUser inicializada correctamente");
	}

}
