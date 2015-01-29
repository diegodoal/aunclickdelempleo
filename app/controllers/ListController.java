package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ListController extends Controller{

	public static Result blank(){
		return ok(views.html.jobslist.jobslist.render());
	}
	
}
