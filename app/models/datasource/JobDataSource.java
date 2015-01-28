package models.datasource;

import java.util.List;

import com.google.gson.Gson;
import com.mongodb.util.JSON;
import models.entities.Job;
import utils.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

public class JobDataSource extends DataSource{


	
	/**
	 * This method insert a new Job Offer in the collection
	 * @param Job - Job Offer to be inserted
	 * @return A new job offer
	 */
	public static Job insertIntoJobsCollection(Job job){
		// Get the collection (connection to our mongo database)
		DBCollection collection = connectDB(Constants.MONGO_JOBS_COLLECTION);
		
		// Create the query
		BasicDBObject query = new BasicDBObject().
			append("job_id", job.id).
			append("title", job.title).
			append("sector", job.sector).
			append("description", job.description).
			append("date", job.date).
			append("location", job.location).
			append("contract_type", job.contract_type).
			append("workday", job.workday).
			append("salary", job.salary).
			append("general_terms", job.general_terms).
			append("requirements", job.requirements).
			append("certificate_of_33_disability", job.certificateOf33Disability).
			append("contact", JSON.parse(job.contact.toString()));
		
		collection.insert(WriteConcern.SAFE, query);
		
		// Close connection
		mongoClient.close();
		
		// Returns the new user
		return job;
	}
	
	/**
	 * This method gets all Job Offers registered in mongoDB collection
	 * @return a List with all DBObjects
	 */
	public static List<DBObject> getAllJobs(){
		DBCollection collection = connectDB(Constants.MONGO_JOBS_COLLECTION);
		List<DBObject> all = collection.find().toArray();
		mongoClient.close();
		return all;
	}
	
	/**
	 * This method finds a Job Offer by its id
	 * @param id The job_id of the registered Job Offer
	 * @return a DBObject that contains the job offer of the query
	 */
	public static Job getJobOffer(int id){
		DBCollection collection = connectDB(Constants.MONGO_JOBS_COLLECTION);
		BasicDBObject query = new BasicDBObject().append("job_id", id);
		DBObject job = collection.findOne(query);
		
		if(job != null){
			Job jobOffer = new Job();
			String jobStr = job.toString();
			
			jobOffer = new Gson().fromJson(jobStr, Job.class);
			
			mongoClient.close();
			return jobOffer;
		}else{
			mongoClient.close();
			return null;
		}
	}
	
	/**
	 * This method creates a fake DB with some company users
	 */
	public static void initializeJobsDB(){
		for(int i=0; i<15; i++){
			insertIntoJobsCollection(new Job("Title"+i, "Sector"+i,
					"Description"+i,"Date"+i, "Location"+i, "Contract type"+i,
					"Workday"+i,2000.0, "General Terms"+i, 
					new Job.ContactProfile("Name"+i, "Email"+i+"@contact", 612345678+i), (Math.random()<0.5)));
		}
	}
}
