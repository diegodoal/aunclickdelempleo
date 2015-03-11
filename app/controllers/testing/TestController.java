package controllers.testing;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.datasource.UserDataSource;
import models.entities.CurrentSituation;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 11/03/2015.
 */
public class TestController extends Controller {

    public static int counter = 0;

    public static Result testDataSource1(){

        CurrentSituation currentSituation = new CurrentSituation();

        currentSituation.addEducationLevel("Primaria");
        currentSituation.addEducationLevel("Secundaria");
        currentSituation.addEducationLevel("Grado");

        currentSituation.addProfessionalExperience("Telefonica", "Talentum", "5 años");
        currentSituation.addProfessionalExperience("Microsoft", "CEO", "10 años");


        return ok(currentSituation.toJsonString());
    }

    public static Result testDataSourceToMongo1(){
        counter++;
        User user = new User("Name"+counter, "Surname"+counter, "email"+counter+"@gmail.com", "password"+counter);
        user.currentSituation.addEducationLevel("Primaria");
        user.currentSituation.addEducationLevel("Secundaria");
        user.currentSituation.addEducationLevel("Grado");

        user.currentSituation.addProfessionalExperience("Telefonica", "Talentum", "5 años");
        user.currentSituation.addProfessionalExperience("Microsoft", "CEO", "10 años");

        return ok(new Gson().toJson(UserDataSource.insertIntoUsersCollection(user)).toString());
    }

}
