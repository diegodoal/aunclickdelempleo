package models.datasource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import models.entities.AdminUser;
import play.Logger;
import utils.Utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 23/04/2015.
 */
public class ConfDataSource {

    /**
     * Object field, contructor and method for Singleton Pattern
     */
    private static ConfDataSource INSTANCE = new ConfDataSource();
    private ConfDataSource(){}
    public static ConfDataSource getInstance(){
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

    /* #### ADMIN USERS ### */
    public static void insertNewAdminUser(AdminUser adminUser){
        DBCollection collection = connectDB("mongo.adminUsers");

        BasicDBObject query = new BasicDBObject().
                append("name", adminUser.name).
                append("password", Utils.encryptWithSHA1(adminUser.password)).
                append("connectionTimestamp", adminUser.connectionTimestamp);
        collection.insert(WriteConcern.SAFE, query);
        mongoClient.close();
    }

    public static void updateAdminUser(AdminUser adminUser){
        DBCollection collection = connectDB("mongo.adminUsers");
        BasicDBObject newDocument = new BasicDBObject();

        newDocument.put("name", adminUser.name);
        newDocument.put("password", adminUser.password);
        newDocument.put("connectionTimestamp", adminUser.connectionTimestamp);

        collection.update(new BasicDBObject().append("name", adminUser.name), newDocument);

        mongoClient.close();
    }

    public static AdminUser getAdminUser(String name){
        DBCollection collection = connectDB("mongo.adminUsers");

        BasicDBObject query = new BasicDBObject().append("name", name);
        DBObject dbObject = collection.findOne(query);

        if(dbObject != null){
            AdminUser adminUser = new Gson().fromJson(dbObject.toString(), AdminUser.class);
            mongoClient.close();
            return adminUser;
        }else{
            mongoClient.close();
            return null;
        }
    }

    public static List<AdminUser> getAllAdminUsers(){
        DBCollection collection = connectDB("mongo.adminUsers");
        DBCursor cursor = collection.find();
        List<AdminUser> users = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                AdminUser adminUser = new Gson().fromJson(cursor.next().toString(), AdminUser.class);

                users.add(adminUser);
            }
        } finally {
            cursor.close();
        }

        mongoClient.close();

        return users;
    }

    public static void initializeAdminUser(){
        String name = "adecco";
        String password = "password";
        if(getAdminUser(name) == null){
            Logger.info("[There are not Super Admin users. Creating....]");
            AdminUser adminUser = new AdminUser(name, password);
            insertNewAdminUser(adminUser);
        }else {
            Logger.info("[There is an Adecco Super Admin user]");
        }
    }

    public static boolean deleteAdminUser(String name){
        DBCollection collection = connectDB("mongo.adminUsers");
        BasicDBObject query = new BasicDBObject().append("name", name);
        DBObject dbObject = collection.findOne(query);
        if(dbObject != null){
            collection.remove(dbObject);
            mongoClient.close();
            return true;
        }else{
            mongoClient.close();
            return false;
        }
    }

    /* #### AMAZON CONFIGURATION ### */
    public static void insertNewAmazonConf(String aws_s3_bucket, String aws_access_key, String aws_secret_key){
        DBCollection collection = connectDB("mongo.projectConf");
        BasicDBObject query = new BasicDBObject().
                append("plattform", "amazon").
                append("aws_s3_bucket", aws_s3_bucket).
                append("aws_access_key", aws_access_key).
                append("aws_secret_key", aws_secret_key);
        collection.insert(WriteConcern.SAFE, query);
        mongoClient.close();
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

    public static void initializeAmazonConf(){
        if(getAmazonConf() == null){
            Logger.info("[There is not Amazon Conf. Creating....]");
            insertNewAmazonConf("", "", "");
        }else {
            Logger.info("[There is an Amazon Conf. profile]");
        }
    }

    public static void addNewGeneratedDoc(){
        DBCollection collection = connectDB("mongo.projectConf");
        BasicDBObject query = new BasicDBObject().append("plattform", "generatedDocs");

        DBObject dbObject = collection.findOne(query);
        if(dbObject != null){
            int newValue = Integer.parseInt(dbObject.get("total").toString())+1;
            BasicDBObject newDocument = new BasicDBObject();

            newDocument.put("plattform", "generatedDocs");
            newDocument.put("total", newValue);

            collection.update(new BasicDBObject().append("plattform", "generatedDocs"), newDocument);
        }else{
            query.append("total", 1);
            collection.insert(WriteConcern.SAFE, query);
        }
        mongoClient.close();
    }
    public static int getNumberOfGeneratedDocs(){
        DBCollection collection = connectDB("mongo.projectConf");
        BasicDBObject query = new BasicDBObject().append("plattform", "generatedDocs");

        DBObject dbObject = collection.findOne(query);
        if(dbObject != null) {
            int total = Integer.parseInt(dbObject.get("total").toString());
            mongoClient.close();
            return total;
        }else{
            mongoClient.close();
            return 0;
        }
    }
}
