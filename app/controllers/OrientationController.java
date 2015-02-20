package controllers;
import java.util.List;
import models.datasource.JobDataSource;
import play.mvc.Controller;
import play.mvc.Result;

public class OrientationController extends Controller {
	public static Result blank() {
        return ok(views.html.orientation.orientation.render());
    }



    /* Punto de partida  */

    public static Result skills() {
        return ok(views.html.orientation.skills.render());
    }


    public static Result currentsituation(){
        return ok(views.html.orientation.currentsituation.render());
    }

    public static Result interestidentification(){
        return ok(views.html.orientation.interestidentification.render());
    }

    public static Result personal() { return ok(views.html.orientation.personal.render()); }

    public static Result professional() { return ok(views.html.orientation.professional.render()); }

    /*Planificate*/
    public static Result deadline(){return ok(views.html.orientation.deadline.render());}

    public static Result photo() { return ok(views.html.orientation.photo.render()); }


    /*Preparate*/

    public static Result learntools() { return ok(views.html.orientation.learntools.render()); }

    public static Result gettools() { return ok(views.html.orientation.gettools.render()); }

    public static Result reputation() { return ok(views.html.orientation.reputation.render()); }

    public static Result networking() { return ok(views.html.orientation.networking.render()); }


    /* Proceso de seleccion */

    public static Result tinterview() { return ok(views.html.orientation.tinterview.render()); }

    public static Result pinterview() { return ok(views.html.orientation.pinterview.render()); }

    public static Result actinterview() { return ok(views.html.orientation.actinterview.render()); }

    public static Result level() { return ok(views.html.orientation.level.render()); }

    public static Result specialization() { return ok(views.html.orientation.specialization.render()); }

    public static Result bestdeals() { return ok(views.html.orientation.bestdeals.render()); }


}