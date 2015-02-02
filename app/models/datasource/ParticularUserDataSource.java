package models.datasource;

import java.util.List;

import play.data.DynamicForm.Dynamic;
import utils.Constants;
import models.entities.ParticularUser;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

public class ParticularUserDataSource extends DataSource{

	/**
	 * This method insert a new Particular User in the collection
	 * @param particularUser - particularUser to be inserted
	 * @return A new particularUser
	 */
	public static ParticularUser insertIntoParticularUser(ParticularUser particularUser){
		// Get the collection (connection to our mongo database)
		DBCollection collection = connectDB(Constants.MONGO_PARTICULAR_USERS_COLLECTION);
		
		// Create the query
		BasicDBObject query = new BasicDBObject().
        append("name", particularUser.name).
        append("surnames", particularUser.surnames).
		append("email", particularUser.email).
		append("password", particularUser.password).
		append("verifyPassword", particularUser.verifyPassword).
		append("emailVerificationKey", particularUser.emailVerificationKey);
		
		collection.insert(WriteConcern.SAFE, query);
		
		// Close connection
		mongoClient.close();
		
		// Returns the new user
		return particularUser;
	}
	
	/**
	 * This method gets all Particular Users registered in mongoDB
	 * @return a DBCursor with all DBObjects (it can be changed to a List after)
	 */
	public static List<DBObject> getAllParticularUsers(){
		DBCollection collection = connectDB(Constants.MONGO_PARTICULAR_USERS_COLLECTION);
		List<DBObject> all = collection.find().toArray();
		
		mongoClient.close();
		
		return all;
	}
	
	/**
	 * This method find a Particular User by its email
	 * @param email The email of the registered user
	 * @return a DBObject that contains the user of the query
	 */
	public static ParticularUser getParticularUser(String email){
		DBCollection collection = connectDB(Constants.MONGO_PARTICULAR_USERS_COLLECTION);
		BasicDBObject query = new BasicDBObject().append("email", email);
		DBObject user = collection.findOne(query);
		
		if(user != null){
			ParticularUser particularUser = new ParticularUser();
			String userStr = user.toString();
			
			particularUser = new Gson().fromJson(userStr, ParticularUser.class);
			
			mongoClient.close();
			return particularUser;
		}else{
			mongoClient.close();
			return null;
		}
	}
	
	/**
	 * Method to update the emailVerificationKey when email is verified
	 * @param email String with the username to update
	 */
	public static void updateEmailVerificationKey(String username){
		DBCollection collection = connectDB(Constants.MONGO_PARTICULAR_USERS_COLLECTION);
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
	public static void initializeParticularUsersDB(){
		for(int i=0; i<15; i++){
			insertIntoParticularUser(new ParticularUser("User"+i, "email"+i+"@particular", "password"+i));
		}
	}
}
