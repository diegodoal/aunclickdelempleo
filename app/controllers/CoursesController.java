package controllers;

import java.util.List;

import models.datasource.CourseDataSource;
import models.entities.Course;

import com.mongodb.DBObject;

import play.mvc.Controller;
import play.mvc.Result;

public class CoursesController extends Controller{

	// JOB OFFERS
		public static Result listCourses(){
			List<DBObject> all = CourseDataSource.getAllCourses();
			String courses = "";
			for(int i=0; i<all.size(); i++){
				courses+=all.get(i)+"\n\n";
			}
			return ok(courses);
		}
		
		public static Result findCourse(int id){
			Course query = CourseDataSource.getCourseOffer(id);
			if(query==null)
				return badRequest("No existe la oferta de curso con id: "+id);
			else
				return ok(query.showCourseOfferInfo());
		}
		
		public static Result initializeCoursesDB(){
			CourseDataSource.initializeCoursesDB();
			return ok("Colección de Course Offers inicializada correctamente");
		}
}
