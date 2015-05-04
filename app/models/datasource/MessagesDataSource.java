package models.datasource;

import com.google.gson.Gson;
import com.mongodb.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import models.entities.Message;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 04/05/2015.
 */
public class MessagesDataSource {
    private static MessagesDataSource INSTANCE = new MessagesDataSource();
    private MessagesDataSource(){}
    public static MessagesDataSource getInstance(){
        return INSTANCE;
    }


    public static MongoClient mongoClient;
    public static DB db = null;
    static Config config = ConfigFactory.load("db");
    static Config configSecurity = ConfigFactory.load("application");

    /**
     * This method returns a MongoDB collection
     * @return A DBCollection specified by db.conf with mongo.host, mongo.port, mongo.database and mongo.messagesCollection
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

        // Returns the collection
        DBCollection collection = db.getCollection(config.getString("mongo.messages"));
        return collection;
    }

    public static void insertNewMessage(Message message){
        DBCollection collection = connectDB();

        BasicDBObject query = new BasicDBObject().
                append("id", message.id).
                append("fromUser", message.fromUser).
                append("toUser", message.toUser).
                append("subject", message.subject).
                append("message", message.message).
                append("date", message.date).
                append("read", message.read).
                append("deletedBySender", message.deletedBySender).
                append("deletedByReceiver", message.deletedByReceiver);
        collection.insert(WriteConcern.SAFE, query);
        mongoClient.close();
    }

    public static List<Message> getMessagesBySender(String sender){
        DBCollection collection = connectDB();
        BasicDBObject query = new BasicDBObject().append("fromUser", sender);
        DBCursor cursor = collection.find(query);
        List<Message> messages = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                Message message = new Gson().fromJson(cursor.next().toString(), Message.class);

                messages.add(message);
            }
        } finally {
            cursor.close();
        }

        mongoClient.close();

        return messages;
    }

    public static List<Message> getMessagesByReceiver(String receiver){
        DBCollection collection = connectDB();
        BasicDBObject query = new BasicDBObject().append("toUser", receiver);
        DBCursor cursor = collection.find(query);
        List<Message> messages = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                Message message = new Gson().fromJson(cursor.next().toString(), Message.class);

                messages.add(message);
            }
        } finally {
            cursor.close();
        }

        mongoClient.close();

        return messages;
    }

    public static int getNumberOfNotReadMessages(String receiver){
        List<Message> messages = getMessagesByReceiver(receiver);
        int result = 0;
        for(Message message : messages){
            if(!message.read)
                result++;
        }
        return result;
    }
}
