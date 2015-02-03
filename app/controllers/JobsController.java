package controllers;

import java.util.List;

import models.datasource.JobDataSource;
import models.entities.Job;
import com.mongodb.DBObject;
import play.mvc.Controller;
import play.mvc.Result;

public class JobsController extends Controller{

	// JOB OFFERS
	public static Result listJobs(){
		List<DBObject> all = JobDataSource.getAllJobs();
		String jobs = "";
		for(int i=0; i<all.size(); i++){
			jobs+=all.get(i)+"\n\n";
		}
		return ok(jobs);
	}
	
	public static Result findJob(int id){
		Job query = JobDataSource.getJobOffer(id);
		if(query==null)
			return badRequest("No existe la oferta de trabajo con id: "+id);
		else
			return ok(query.showJobOfferInfo());
	}
	
	public static Result initializeJobsDB(){
		JobDataSource.initializeJobsDB();
		return ok("Colección de Job Offers inicializada correctamente");
	}

    public static Result blank(){
        List<Job> jobs = JobDataSource.getJobs();
        return ok(views.html.jobslist.jobslist.render(jobs));
    }

    public static Result jobDetails(String id){

        Job job = JobDataSource.getJobOffer(Integer.parseInt(id));

        if (job == null){

            return badRequest("Error, la oferta indicada ya no está disponible");
        }

        return ok(views.html.jobslist.jobsdetails.render(job));
    }
	
}
