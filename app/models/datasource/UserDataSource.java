package models.datasource;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;
import models.entities.ParticularUser;
import models.entities.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Victor on 09/03/2015.
 */
public class UserDataSource extends DataSource {

    public static User insertIntoUsersCollection(User user){
        // Get the collection (connection to our mongo database)
        DBCollection collection = connectDB("mongo.usersCollection");

        // Create the query
        BasicDBObject query = new BasicDBObject().
                append("name", user.name).
                append("surnames", user.surnames).
                append("email", user.email).
                append("password", user.password).
                append("emailVerificationKey", user.emailVerificationKey).
                append("orientationSteps", JSON.parse(user.completedOrientationSteps.orientationStepsToJson())).
                append("currentSituation", JSON.parse(user.currentSituation.toJsonString())).
                append("skills", JSON.parse(user.skillstoJson())).
                append("interests", user.interests).
                append("personalCharacteristics", user.personalCharacteristics).
                append("professionalValues", JSON.parse(user.professionalValuesToJson())).
                append("photo", JSON.parse(user.photo.toJsonString()));
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

            //Add to USER the completedOrientationSteps object stored in JSON
            user.completedOrientationSteps = new Gson().fromJson(dbObject.get("orientationSteps").toString(), User.CompletedOrientationSteps.class);

            mongoClient.close();
            return user;
        }else{
            mongoClient.close();
            return null;
        }
    }

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
