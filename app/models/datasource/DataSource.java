package models.datasource;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class DataSource {
	public static MongoClient mongoClient;
	//public static DB db;
	static Config config = ConfigFactory.load("db");
	static Config configSecurity = ConfigFactory.load("application");

	/**
	 * This method returns a MongoDB collection
	 * @return A DBCollection specified by db.conf with mongo.host, mongo.port, mongo.database and mongo.particularUsersColection
	 */

	public static DBCollection connectDB(String mongoCollectionName) {
		try {
			// Creates a new MongoClient using settings mongo.host and mongo.port specified inside db.conf
			mongoClient = new MongoClient(config.getString("mongo.host"), config.getInt("mongo.port"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// Select the database mongo.database
		DB db = mongoClient.getDB(config.getString("mongo.database"));

		// Returns the collection
		DBCollection collection = db.getCollection(config.getString(mongoCollectionName));
		return collection;
	}
}
