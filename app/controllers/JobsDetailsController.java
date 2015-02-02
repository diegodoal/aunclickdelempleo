package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class JobsDetailsController extends Controller {
    public static Result func(){
        return ok(views.html.jobslist.jobslist.render());
    }
}
