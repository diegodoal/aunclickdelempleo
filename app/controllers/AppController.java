package controllers;

import models.datasource.SingletonDataSource;
import models.entities.User;
import utils.Utils;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Result;

import static play.mvc.Controller.request;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;


/**
 * Created by alfonsocamberolorenzo on 21/04/15.
 */
public class AppController {

    //METODO PARA LA APP DE ALFONSO

    public static Result submitLoginApp(){
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(request.get("email").asText());

        if(user != null && Utils.encryptWithSHA1(request.get("password").asText()).equals(user.password)){
            return ok("success");
        }

        return badRequest("fail");
    }

    public static Result getInterviews() {
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(request.get("email").asText());

        if(user != null && Utils.encryptWithSHA1(request.get("password").asText()).equals(user.password)){
            return ok(user.interviewScheduleListToJson());
        }

        return badRequest("fail");
    }
}
