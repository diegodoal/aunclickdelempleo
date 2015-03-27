package models.datasource;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import models.entities.User;
import models.entities.orientation.CurrentSituation;
import models.entities.orientation.InterviewSchedule;
import models.entities.orientation.Skill;
import utils.Constants;
import utils.Utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 20/03/2015.
 */
public class SingletonDataSource {

    /**
     * Object field, contructor and method for Singleton Pattern
     */
    private static SingletonDataSource INSTANCE = new SingletonDataSource();
    private SingletonDataSource(){}
    public static SingletonDataSource getInstance(){
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

    public static User insertIntoUsersCollection(User user){
        // Get the collection (connection to our mongo database)
        DBCollection collection = connectDB("mongo.usersCollection");

        // Create the query
        BasicDBObject query = new BasicDBObject().
                append(Constants.USER_NAME, user.name).
                append(Constants.USER_SURNAMES, user.surnames).
                append(Constants.USER_EMAIL, user.email).
                append(Constants.USER_PASSWORD, Utils.encryptWithSHA1(user.password)).
                append(Constants.USER_EMAIL_VERIFICATION_KEY, user.emailVerificationKey).
                append(Constants.USER_CONNECTION_TIMESTAMP, user.connectionTimestamp).
                append(Constants.USER_ORIENTATION_STEPS, JSON.parse(user.completedOrientationSteps.orientationStepsToJson())).
                append(Constants.USER_CURRENT_SITUATION, JSON.parse(user.currentSituation.toJsonString())).
                append(Constants.USER_SKILLS_LIST, JSON.parse(user.skillstoJson())).
                append(Constants.USER_INTERESTS_LIST, user.interests).
                append(Constants.USER_PERSONAL_CHARACTERISTICS_LIST, user.personalCharacteristics).
                append(Constants.USER_PROFESSIONAL_VALUES_LIST, JSON.parse(user.professionalValuesToJson())).
                append(Constants.USER_PHOTO, JSON.parse(user.photo.toJsonString())).
                append(Constants.USER_NEXT_INTERVIEWS_LIST, JSON.parse(user.interviewScheduleListToJson()));
        collection.insert(WriteConcern.SAFE, query);

        // Close connection
        mongoClient.close();

        // Returns the new user
        return user;
    }

    public static User getUserByEmail(String email){
        DBCollection collection = connectDB("mongo.usersCollection");
        BasicDBObject query = new BasicDBObject().append("email", email);
        DBObject dbObject = collection.findOne(query);

        if(dbObject != null){
            User user = new User();
            String userStr = dbObject.toString();

            //Get user object from JSON
            user = new Gson().fromJson(userStr, User.class);

            // Deserialize Current Situation object
            user.currentSituation = new Gson().fromJson(dbObject.get(Constants.USER_CURRENT_SITUATION).toString(), CurrentSituation.class);

            // Deserialize ArrayList of Skills
            user.skills = new Gson().fromJson(dbObject.get(Constants.USER_SKILLS_LIST).toString(), new TypeToken<List<Skill>>(){}.getType());

            //Add to USER the completedOrientationSteps object stored in JSON
            user.completedOrientationSteps = new Gson().fromJson(dbObject.get(Constants.USER_ORIENTATION_STEPS).toString(), User.CompletedOrientationSteps.class);
            // Deserialize ArrayList of InterviewSchedule Objects
            user.interviewScheduleList = new Gson().fromJson(dbObject.get(Constants.USER_NEXT_INTERVIEWS_LIST).toString(), new TypeToken<List<InterviewSchedule>>(){}.getType());


            mongoClient.close();
            return user;
        }else{
            mongoClient.close();
            return null;
        }
    }

    public static List<User> getAllUsers(){
        DBCollection collection = connectDB("mongo.usersCollection");
        List<DBObject> all = collection.find().toArray();
        List<User> resultList = new ArrayList<>();

        for (int i = 0; i < all.size(); i++){
            resultList.add(getUserByEmail(all.get(i).get("email").toString()));
        }

        mongoClient.close();

        return resultList;
    }

    public static void dropUserCollection(){
        DBCollection myCollection = connectDB("mongo.usersCollection");
        myCollection.drop();
        mongoClient.close();
    }

    public static void updateAllUserData(User user){
        DBCollection collection = connectDB("mongo.usersCollection");
        BasicDBObject newDocument = new BasicDBObject();

        newDocument.put(Constants.USER_NAME, user.name);
        newDocument.put(Constants.USER_SURNAMES, user.surnames);
        newDocument.put(Constants.USER_EMAIL, user.email);
        newDocument.put(Constants.USER_PASSWORD, user.password);
        newDocument.put(Constants.USER_EMAIL_VERIFICATION_KEY, user.emailVerificationKey);
        newDocument.put(Constants.USER_CONNECTION_TIMESTAMP, user.connectionTimestamp);
        newDocument.put(Constants.USER_ORIENTATION_STEPS, JSON.parse(user.completedOrientationSteps.orientationStepsToJson()));
        newDocument.put(Constants.USER_CURRENT_SITUATION, JSON.parse(user.currentSituation.toJsonString()));
        newDocument.put(Constants.USER_SKILLS_LIST, JSON.parse(user.skillstoJson()));
        newDocument.put(Constants.USER_INTERESTS_LIST, user.interests);
        newDocument.put(Constants.USER_PERSONAL_CHARACTERISTICS_LIST, user.personalCharacteristics);
        newDocument.put(Constants.USER_PROFESSIONAL_VALUES_LIST, JSON.parse(user.professionalValuesToJson()));
        newDocument.put(Constants.USER_PHOTO, JSON.parse(user.photo.toJsonString()));
        newDocument.put(Constants.USER_NEXT_INTERVIEWS_LIST, JSON.parse(user.interviewScheduleListToJson()));

        collection.update(new BasicDBObject().append("email", user.email), newDocument);

        mongoClient.close();
    }

    /* DO NOT USE. INSTEAD OF THIS METHOD, USE updateAllUserData()
     */
    public static void updateUserData(String email, String key, String newValue){
        DBCollection collection = connectDB("mongo.usersCollection");
        BasicDBObject query = new BasicDBObject().append("email", email);
        DBObject user = collection.findOne(query);

        if(user != null){
            BasicDBObject updateQuery = new BasicDBObject().append("$set", new BasicDBObject().append(key, newValue));
            collection.update(query, updateQuery);
        }
        mongoClient.close();
    }

}
