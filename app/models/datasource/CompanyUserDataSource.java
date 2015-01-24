package models.datasource;

import java.net.UnknownHostException;

import models.entities.CompanyUser;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class CompanyUserDataSource {

	public static MongoClient mongoClient;
	static Config config = ConfigFactory.load("db");
	static Config configSecurity = ConfigFactory.load("application");
	
	/**
	 * This method returns a MongoDB collection
	 * @return A DBCollection specified by db.conf with mongo.host, mongo.port, mongo.database and mongo.companyUsersColection
	 */
	
	public static DBCollection connectDB() {
		// Creates a new MongoClient using settings mongo.host and mongo.port specified inside db.conf
		try {
			mongoClient = new MongoClient(config.getString("mongo.host"), config.getInt("mongo.port"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Select the database mongo.database
		DB db = mongoClient.getDB(config.getString("mongo.database"));
		
		// Returns the collection mongo.companyUsersCollection
		DBCollection collection = db.getCollection(config.getString("mongo.companyUsersCollection"));
		return collection;
	}
	
	/**
	 * This method insert a new Company User in the collection
	 * @param companyUser - companyUser to be inserted
	 * @return A new companyUser
	 */
	public static CompanyUser insertIntoCompanyUser(CompanyUser companyUser){
		// Get the collection (connection to our mongo database)
		DBCollection collection = connectDB();
		
		// Create the query
		BasicDBObject query = new BasicDBObject().
		append("email", companyUser.email).
		append("password", companyUser.password);
		
		collection.insert(WriteConcern.SAFE, query);
		
		// Close connection
		mongoClient.close();
		
		// Returns the new user
		return companyUser;
	}
	
	public static DBCursor getAllCompanyUsers(){
		DBCollection collection = connectDB();
		DBCursor all = collection.find();
		
		mongoClient.close();
		
		return all;
	}
	
}
