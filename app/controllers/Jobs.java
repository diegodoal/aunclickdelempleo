package app.controllers;

public class Jobs extends Controller{
	
	//LIST JOBS
	public static Result newJobs(String email, String password){
		Jobs query = ParticularUserDataSource.getParticularUser(email);
		if(query != null){
			return badRequest("Ya existe un usuario con ese email registrado, inténtelo con otro...");
		}
		
		ParticularUser particularUser = new ParticularUser(email, password);
		ParticularUserDataSource.insertIntoParticularUser(particularUser);
		return redirect("/particular/sendvalidation/"+email);
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
