package models.datasource;

import java.util.List;

import models.entities.Course;
import models.entities.Course.ContactProfile;
import models.entities.Course.Duration;
import models.entities.Job;
import utils.Constants;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

public class CourseDataSource extends DataSource{

	/**
	 * This method insert a new Course Offer in the collection
	 * @param course - Course Offer to be inserted
	 * @return A new course offer
	 */
	public static Course insertIntoCoursesCollection(Course course){
		// Get the collection (connection to our mongo database)
		DBCollection collection = connectDB(Constants.MONGO_COURSES_COLLECTION);

		// Create the query
		BasicDBObject query = new BasicDBObject().
				append("course_id", course.id).
				append("title", course.title).
				append("sector", course.sector).
				append("duration", JSON.parse(course.duration.toString())).
				append("location", course.location).
				append("description", course.description).
				append("general_terms", course.general_terms).
				append("requirements", course.requirements).
				append("price", course.price).
				append("contact", JSON.parse(course.contact.toString()));


		collection.insert(WriteConcern.SAFE, query);

		// Close connection
		mongoClient.close();

		// Returns the new user
		return course;
	}

	/**
	 * This method gets all Course Offers registered in mongoDB collection
	 * @return a List with all DBObjects
	 */
	public static List<DBObject> getAllCourses(){
		DBCollection collection = connectDB(Constants.MONGO_COURSES_COLLECTION);
		List<DBObject> all = collection.find().toArray();
		mongoClient.close();
		return all;
	}

	/**
	 * This method finds a Course Offer by its id
	 * @param id The course_id of the registered Course Offer
	 * @return a DBObject that contains the course offer of the query
	 */
	public static Course getCourseOffer(int id){
		DBCollection collection = connectDB(Constants.MONGO_COURSES_COLLECTION);
		BasicDBObject query = new BasicDBObject().append("course_id", id);
		DBObject course = collection.findOne(query);

		if(course != null){
			Course courseOffer = new Course();
			String courseStr = course.toString();

			courseOffer = new Gson().fromJson(courseStr, Course.class);

			mongoClient.close();
			return courseOffer;
		}else{
			mongoClient.close();
			return null;
		}
	}

	/**
	 * This method creates a fake DB with some company users
	 */
	public static void initializeCoursesDB(){
		for(int i=0; i<15; i++){
			insertIntoCoursesCollection(new Course("Title"+i, "Sector"+i, 
					new Duration("Length: "+i+"hours", "Schedule: Mondays and Fridays", "Start_Date: "+i, "End_Date: "+i*2),
					"Location"+i, "Description"+i, "General_terms"+i, "Requirements"+i, 200*i, new ContactProfile("Name"+i, "email"+i+"@contact", 612345678+i)));
		}
	}

}
