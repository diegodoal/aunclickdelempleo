package models.datasource;

import java.util.List;
import utils.Constants;
import models.entities.CompanyUser;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

public class CompanyUserDataSource extends DataSource{

	
	
	/**
	 * This method insert a new Company User in the collection
	 * @param companyUser - companyUser to be inserted
	 * @return A new companyUser
	 */
	public static CompanyUser insertIntoCompanyUser(CompanyUser companyUser){
		// Get the collection (connection to our mongo database)
		DBCollection collection = connectDB(Constants.MONGO_COMPANY_USERS_COLLECTION);
		
		// Create the query
		BasicDBObject query = new BasicDBObject().
        append("name", companyUser.name).
		append("email", companyUser.email).
		append("password", companyUser.password).
		append("emailVerificationKey", companyUser.emailVerificationKey);
		
		collection.insert(WriteConcern.SAFE, query);
		
		// Close connection
		mongoClient.close();
		
		// Returns the new user
		return companyUser;
	}
	
	/**
	 * This method gets all Company Users registered in mongoDB
	 * @return a DBCursor with all DBObjects (it can be changed to a List after)
	 */
	public static List<DBObject> getAllCompanyUsers(){
		DBCollection collection = connectDB(Constants.MONGO_COMPANY_USERS_COLLECTION);
		List<DBObject> all = collection.find().toArray();
		
		mongoClient.close();
		
		return all;
	}
	
	/**
	 * This method find a Company User by its email
	 * @param email The email of the registered user
	 * @return a DBObject that contains the user of the query
	 */
	public static CompanyUser getCompanyUser(String email){
		DBCollection collection = connectDB(Constants.MONGO_COMPANY_USERS_COLLECTION);
		BasicDBObject query = new BasicDBObject().append("email", email);
		DBObject user = collection.findOne(query);
		
		if(user != null){
			CompanyUser companyUser = new CompanyUser();
			String userStr = user.toString();
			
			companyUser = new Gson().fromJson(userStr, CompanyUser.class);
			
			mongoClient.close();
			return companyUser;
		}else{
			mongoClient.close();
			return null;
		}
	}
	
	/**
	 * Method to update the emailVerificationKey when email is verified
	 * @param username String with the username to update
	 */
	public static void updateEmailVerificationKey(String username){
		DBCollection collection = connectDB(Constants.MONGO_COMPANY_USERS_COLLECTION);
		BasicDBObject query = new BasicDBObject().append("email", username);
		DBObject user = collection.findOne(query);
		
		if(user != null){
			BasicDBObject updateQuery = new BasicDBObject().append("$set", new BasicDBObject().append("emailVerificationKey", null));
			collection.update(query, updateQuery);
		}
		
		mongoClient.close();
	}
	
	/**
	 * This method creates a fake DB with some company users
	 */
	public static void initializeCompanyUsersDB(){
		for(int i=0; i<15; i++){
			insertIntoCompanyUser(new CompanyUser("Name"+i, "email"+i+"@company", "password"+i));
		}
	}
}
