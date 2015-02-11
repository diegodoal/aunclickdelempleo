package controllers;
import java.util.List;
import models.datasource.JobDataSource;
import play.mvc.Controller;
import play.mvc.Result;

public class OrientationController extends Controller {
	public static Result blank() {
        return ok(views.html.orientation.orientation.render());
    }
}