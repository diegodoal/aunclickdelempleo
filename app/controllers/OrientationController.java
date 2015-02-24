package controllers;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import models.S3File;
import models.datasource.JobDataSource;
import play.mvc.Controller;
import play.mvc.Http;
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
        return ok(views.html.orientation.currentSituation.render());
    }

    public static Result interestidentification(){
        return ok(views.html.orientation.interestIdentification.render());
    }

    public static Result personal() { return ok(views.html.orientation.personal.render()); }

    public static Result professional() { return ok(views.html.orientation.professional.render()); }

    /*Planificate*/
    public static Result deadline(){return ok(views.html.orientation.deadline.render());}

    public static Result photo() { return ok(views.html.orientation.photo.render(null)); }

    public static Result uploadPhoto() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadFilePart = body.getFile("upload");
        if (uploadFilePart != null) {
            S3File s3File = new S3File();

            String s = uploadFilePart.getFilename();
            if(s.contains("."))
                s3File.name = UUID.randomUUID().toString() + s.substring(s.lastIndexOf('.')) ;
            else
                s3File.name = UUID.randomUUID().toString();

            s3File.file = uploadFilePart.getFile();
            s3File.save();

            try {
                return ok(views.html.orientation.photo.render("Foto subida correctamente: "+s3File.getUrl().toString()));
            } catch (MalformedURLException e) {
                return ok("uploaded video but can not get url");
            }
        }
        else {
            return badRequest("File upload error");
        }
    }


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