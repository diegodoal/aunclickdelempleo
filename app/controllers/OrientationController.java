package controllers;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

import models.S3File;
import org.apache.commons.codec.binary.Base64;
import play.Logger;
import play.data.DynamicForm;

import static play.data.Form.form;
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

    public static Result photo() {
        return ok(views.html.orientation.photo.render());
    }

    public static Result uploadPhoto() {
        Http.RequestBody body = request().body();

        String base64 = body.asFormUrlEncoded().get("imgBase64")[0].toString();
        File temp = null;
        try {
            temp = File.createTempFile("prefix", ".png");

            temp.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        base64 = base64.substring(base64.indexOf(",")+1);
        byte[] data = Base64.decodeBase64(base64);

        try (OutputStream stream = new FileOutputStream(temp.getPath())) {
            stream.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        S3File s3File = new S3File();

        String s = temp.getName();
        if(s.contains("."))
            s3File.name = UUID.randomUUID().toString() + s.substring(s.lastIndexOf('.')) ;
        else
            s3File.name = UUID.randomUUID().toString();

        s3File.file = temp;
        s3File.save();

        return ok(views.html.orientation.orientation.render());
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

    public static Result questionsinterview() { return ok(views.html.orientation.questionsinterview.render()); }

    public static Result level() { return ok(views.html.orientation.level.render()); }

    public static Result specialization() { return ok(views.html.orientation.specialization.render()); }

    public static Result bestdeals() { return ok(views.html.orientation.bestdeals.render()); }


}