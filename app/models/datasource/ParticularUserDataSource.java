package models.datasource;

import java.net.UnknownHostException;
import java.util.List;

import models.entities.ParticularUser;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ParticularUserDataSource {

	public static MongoClient mongoClient;
	//public static DB db;
	static Config config = ConfigFactory.load("db");
	static Config configSecurity = ConfigFactory.load("application");
	
	/**
	 * This method returns a MongoDB collection
	 * @return A DBCollection specified by db.conf with mongo.host, mongo.port, mongo.database and mongo.particularUsersColection
	 */
	
	public static DBCollection connectDB() {
		try {
			// Creates a new MongoClient using settings mongo.host and mongo.port specified inside db.conf
			mongoClient = new MongoClient(config.getString("mongo.host"), config.getInt("mongo.port"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		// Select the database mongo.database
		DB db = mongoClient.getDB(config.getString("mongo.database"));
		
		// Returns the collection mongo.particularUsersCollection
		DBCollection collection = db.getCollection(config.getString("mongo.particularUsersCollection"));
		return collection;
	}

	/**
	 * This method insert a new Particular User in the collection
	 * @param particularUser - particularUser to be inserted
	 * @return A new particularUser
	 */
	public static ParticularUser insertIntoParticularUser(ParticularUser particularUser){
		// Get the collection (connection to our mongo database)
		DBCollection collection = connectDB();
		
		// Create the query
		BasicDBObject query = new BasicDBObject().
		append("email", particularUser.email).
		append("password", particularUser.password).
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
		DBCollection collection = connectDB();
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
		DBCollection collection = connectDB();
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
		DBCollection collection = connectDB();
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
			insertIntoParticularUser(new ParticularUser("email"+i+"@particular", "password"+i));
		}
	}
	
}
