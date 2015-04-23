package models.datasource;

import com.mongodb.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.UnknownHostException;

/**
 * Created by Victor on 23/04/2015.
 */
public class AmazonDataSource {

    /**
     * Object field, contructor and method for Singleton Pattern
     */
    private static AmazonDataSource INSTANCE = new AmazonDataSource();
    private AmazonDataSource(){}
    public static AmazonDataSource getInstance(){
        return INSTANCE;
    }


    public static MongoClient mongoClient;
    public static DB db = null;
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

    public static String[] getAmazonConf(){
        DBCollection collection = connectDB("mongo.projectConf");

        BasicDBObject query = new BasicDBObject().append("plattform", "amazon");
        DBObject dbObject = collection.findOne(query);

        String[] result = new String[3];
        if(dbObject != null){
            result[0] = dbObject.get("aws_s3_bucket").toString();
            result[1] = dbObject.get("aws_access_key").toString();
            result[2] = dbObject.get("aws_secret_key").toString();

            mongoClient.close();
            return result;
        }else{
            mongoClient.close();
            return null;
        }
    }

    public static void updateAmazonConf(String aws_s3_bucket, String aws_access_key, String aws_secret_key){
        DBCollection collection = connectDB("mongo.projectConf");
        BasicDBObject newDocument = new BasicDBObject();

        newDocument.put("plattform", "amazon");
        newDocument.put("aws_s3_bucket", aws_s3_bucket);
        newDocument.put("aws_access_key", aws_access_key);
        newDocument.put("aws_secret_key", aws_secret_key);

        collection.update(new BasicDBObject().append("plattform", "amazon"), newDocument);

        mongoClient.close();
    }
}
