package controllers;
import java.util.List;
import models.datasource.JobDataSource;
import play.mvc.Controller;
import play.mvc.Result;

public class OrientationController extends Controller {
	public static Result blank() {
        return ok(views.html.orientation.orientation.render());
    }

    public static Result skills() {
        return ok(views.html.orientation.skills.render());
    }

    public static Result currentSituation(){
        return ok(views.html.orientation.currentSituation.render());
    }

    public static Result interestIdentification(){
        return ok(views.html.orientation.interestIdentification.render());
    }


    public static Result deadline(){
        return ok(views.html.orientation.deadline.render());
    }





}