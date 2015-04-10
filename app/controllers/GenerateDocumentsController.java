package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by Victor on 17/03/2015.
 */
public class GenerateDocumentsController {


    public static Result step2(){
        return ok(views.html.complete_user_profile.complete_user_profile_2.render());
    }

    public static Result step3(){
        return ok(views.html.complete_user_profile.complete_user_profile_3.render());
    }

    public static Result cv1(){return ok(views.html.complete_cv.complete_cv_1.render());}

    public static Result cv2(){return ok(views.html.complete_cv.complete_cv_2.render());}

    public static Result cv3(){return ok(views.html.complete_cv.complete_cv_3.render());}

    public static Result lp1(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        return ok(views.html.complete_letter_presentation.letter_presentation_1.render(user));
    }

    public static Result submitLp1(){
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));


        if(user != null){
            String[] personalInformation = new Gson().fromJson(request.toString(), new TypeToken<String[]>(){}.getType());

            user.name = personalInformation[0];
            user.surnames = personalInformation[1];
            user.birthDate = personalInformation[2];
            user.residenceCity = personalInformation[3];
            user.residenceAddress = personalInformation[4];
            user.residenceNumber = personalInformation[5];
            user.residenceZipCode = personalInformation[6];
            //user.email = personalInformation[7];
            user.phoneNumber = personalInformation[8];

            if(!personalInformation[9].equals("")){
                //Add academic experience
                String[] academicExp = new Gson().fromJson(personalInformation[9].toString(), new TypeToken<String[]>(){}.getType());
                user.studyTitle = academicExp[0];
                user.studyLocation = academicExp[1];
            }else{
                user.studyTitle = null;
                user.studyLocation = null;
            }

            List<String> personalCharacteristics = new Gson().fromJson(personalInformation[10].toString(), new TypeToken<List<String>>() {
            }.getType());

            user.personalCharacteristics = personalCharacteristics;

            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation/gettools/lp2");
    }

    public static Result lp2(){return ok(views.html.complete_letter_presentation.letter_presentation_2.render());}

    public static Result lp3(){return ok(views.html.complete_letter_presentation.letter_presentation_3.render());}
}
