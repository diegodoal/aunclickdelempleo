package models.datasource;

import java.net.UnknownHostException;

import models.entities.ParticularUser;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
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
		append("password", particularUser.password);
		
		collection.insert(WriteConcern.SAFE, query);
		
		// Close connection
		mongoClient.close();
		
		// Returns the new user
		return particularUser;
	}
}
