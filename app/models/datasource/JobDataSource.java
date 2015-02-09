package models.datasource;

import java.util.List;
import java.util.ArrayList;
import models.entities.Job;
import utils.Constants;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

public class JobDataSource extends DataSource{

	/**
	 * This method insert a new Job Offer in the collection
	 * @param job - Job Offer to be inserted
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
			append("address", job.address).
            append("province", job.province).
			append("contract_type", job.contract_type).
			append("workday", job.workday).
			append("salary", job.salary).
			append("general_terms", job.general_terms).
            append("experience", job.experience).
            append("fromHome", job.fromHome).
			append("requirements", job.requirements).
			append("certificateOf33Disability", job.certificateOf33Disability).
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
     * This method filters a Jobs List by some parameters
     * @param keywords
     * @param sector
     * @param province
     * @param online
     * @return A Jobs List with all the filtered objects
     */
    public static List<Job> getJobsByFilter(String keywords, String sector, String province, String experience,
                                            String disabilityRadio, String online){
        DBCollection collection = connectDB(Constants.MONGO_JOBS_COLLECTION);
        BasicDBObject query = new BasicDBObject();

        if(sector != null){
            query.append("sector", sector);
        }

        if(province != null){
            query.append("province", province);
        }

        if(experience != null){
            query.append("experience", experience);
        }

        if(disabilityRadio != null){
            if(disabilityRadio.equals("true")){
                query.append("certificateOf33Disability", true);
            }else{
                query.append("certificateOf33Disability", false);
            }
        }

        if(online != null){
            query.append("fromHome", true);
        }

        List<DBObject> dblist = collection.find(query).toArray();

        List<Job> queryResult = dbObjectsListToJobList(dblist);

        if(!keywords.trim().equals("")){
            return filterByKeyword(queryResult, keywords);
        }

        return queryResult;
    }

    /**
     * This method filters a Jobs List by the keyword in params
     * @param firstList The list with all the objects to filter
     * @param keyword The keyword for filter the list
     * @return A List with all the objects that contains in its Title/Description the keyword
     */
    private static List<Job> filterByKeyword(List<Job> firstList, String keyword){
        List<Job> filteredList = new ArrayList<Job>();
        Job auxJob = null;
        for(int i=0; i<firstList.size(); i++){
            auxJob = firstList.get(i);
            if(auxJob.title.toLowerCase().contains(keyword.toLowerCase()) || auxJob.description.toLowerCase().contains(keyword.toLowerCase())){
                filteredList.add(auxJob);
            }
        }
        return filteredList;
    }

    /**
     * Converts a DBObject List to a Job List
     * @param dblist The DBObject list
     * @return A Job List
     */
    public static List<Job> dbObjectsListToJobList(List<DBObject> dblist){
        List<Job> jobsList = new ArrayList<Job>();
        for(int i=0; i<dblist.size(); i++){
            jobsList.add(new Gson().fromJson(dblist.get(i).toString(), Job.class));
        }
        return jobsList;
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
					"Description"+i,"Date"+i, "Address"+i, "Badajoz", "Contract type"+i,
					"Workday"+i,2000.0, "General Terms"+i, "+5 años", true,
					new Job.ContactProfile("Name"+i, "Email"+i+"@contact", 612345678+i), (Math.random()<0.5)));
		}
	}
}
