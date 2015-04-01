package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created 27/03/2015.
 */
public class PlanningController extends Controller {
    public static Result plan(){
        return ok(views.html.planning.planning.render());
    }
}
