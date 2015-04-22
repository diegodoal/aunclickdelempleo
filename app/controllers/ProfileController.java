package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.datasource.SingletonDataSource;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;
import utils.EmailUtil;
import utils.Utils;

import java.util.Date;

/**
 * Created by Victor on 08/04/2015.
 */
public class ProfileController extends Controller {

    public static Result blank(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if (user == null){
            return redirect("/");
        }
        return ok(views.html.user_profile.profile.render(user));
    }

    public static Result updatePersonalInformation(){
        JsonNode request = request().body().asJson();

        String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>(){}.getType());

        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        if (user != null){
            String oldEmail = user.email;

            if(!result[0].equals(""))
                user.name = result[0];
            if(!result[1].equals(""))
                user.surnames = result[1];
            if(!result[2].equals("")) {
                User auxUser = SingletonDataSource.getInstance().getUserByEmail(result[2]);
                if (!result[2].equals(user.email) && auxUser != null){
                    return badRequest();
                }
                user.email = result[2];
            }
            if(!result[3].equals(""))
                user.password = Utils.encryptWithSHA1(result[3]);


            String subject = "Cambiar informacion basica en \"A un click del empleo\"";
            String message = "Atencion, le informamos que se ha cambiado su informacion basica en A un click del empleo. Si no ha sido usted, contacte con nosotros.";
            EmailUtil.emailMaker(oldEmail, subject, message);

            user.connectionTimestamp = new Date().toString();

            session("email", user.email);
            session("name", user.name);
            session("timestamp", user.connectionTimestamp);
            SingletonDataSource.getInstance().updateAllUserData(user);

            return ok();
        }

        return badRequest();
    }

    public static Result textPlainInfo(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if (user == null){
            return redirect("/");
        }
        return ok(views.html.user_profile.user_info.render(user));
    }
}
