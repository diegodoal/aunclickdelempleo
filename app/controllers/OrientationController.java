package controllers;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import models.S3File;
import models.datasource.SingletonDataSource;
import models.entities.User;
import models.entities.orientation.InterviewSchedule;
import models.entities.orientation.ProfessionalValue;
import models.entities.orientation.Skill;

import org.apache.commons.codec.binary.Base64;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class OrientationController extends Controller {
	public static Result blank() {
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        return ok(views.html.orientation.orientation.render(user));
    }

    /* PUNTO 1: CONOCETE A TI MISMO */
    public static Result currentsituation(){
    	String error_msg = "";
        return ok(views.html.orientation.currentSituation.render(error_msg));
    }

    public static Result submitCurrentSituation(){
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        if(user != null) {
            String[] result = new Gson().fromJson(request.toString(), new TypeToken<String[]>() {}.getType());
            for (int i = 0; i < result.length - 1; i++) {
                if (!user.currentSituation.educationLevelList.contains(result[i])) {
                    user.currentSituation.addEducationLevel(result[i]);
                }
            }

            String[][] experience = new Gson().fromJson(result[result.length - 1].toString(), new TypeToken<String[][]>() {
            }.getType());
            for (int i = 0; i < experience.length; i++) {
                user.currentSituation.addProfessionalExperience(experience[i][0], experience[i][1], experience[i][2]);
            }

            user.completedOrientationSteps.currentSituation = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");

    }

    public static Result skills() {
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        return ok(views.html.orientation.skills.render(user));
    }

    public static Result submitSkills(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        JsonNode request = request().body().asJson();

        if(user != null) {
            String[][] skills = new Gson().fromJson(request.toString(), new TypeToken<String[][]>() {
            }.getType());
            if(user.skill.isEmpty()){
                for (int i = 0; i < skills.length; i++) {
                    user.skill.add(i, new Skill(skills[i][0], skills[i][1]));
                }
            }else{
                for (int i=0; i<skills.length; i++){
                    user.skill.get(i).level = skills[i][1];
                }
            }
            user.completedOrientationSteps.skills = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result interestIdentification(){
        return ok(views.html.orientation.interestIdentification.render());
    }

    public static Result submitInterestIdentification(){
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        if(user != null) {
            List<String> interests = new Gson().fromJson(request.toString(), new TypeToken<List<String>>() {
            }.getType());

            user.interests = interests;
            user.completedOrientationSteps.interestIdentification = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result personal() { return ok(views.html.orientation.personal.render()); }

    public static Result submitPersonal(){
    	JsonNode request = request().body().asJson();
    	User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        if(user != null) {
            List<String> personalCharacteristics = new Gson().fromJson(request.toString(), new TypeToken<List<String>>() {
            }.getType());

            user.personalCharacteristics = personalCharacteristics;
            user.completedOrientationSteps.personal = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result professional() { return ok(views.html.orientation.professional.render()); }

    public static Result submitProfessional(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        JsonNode request = request().body().asJson();

        if(user != null) {
            String[][] professionalValues = new Gson().fromJson(request.toString(), new TypeToken<String[][]>() {
            }.getType());

            for (int i = 0; i < professionalValues.length; i++) {
                user.professionalValues.add(new ProfessionalValue(professionalValues[i][0], professionalValues[i][1]));
            }

            user.completedOrientationSteps.professional = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

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
        String photo_id;
        if(s.contains("."))
            photo_id = UUID.randomUUID().toString() + s.substring(s.lastIndexOf('.')) ;
        else
            photo_id = UUID.randomUUID().toString();

        s3File.name = photo_id;
        s3File.file = temp;
        s3File.save();

        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user!= null){
            user.photo.id = photo_id;
            user.completedOrientationSteps.photo = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    /* PUNTO 2: PREPARATE PARA LA BUSQUEDA DE EMPLEO */
    public static Result channels(){return ok(views.html.orientation.channels.render());}

    public static Result submitChannels(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null) {
            user.completedOrientationSteps.channels = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result learnTools() { return ok(views.html.orientation.learntools.render()); }

    public static Result submitLearnTools(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null) {
            user.completedOrientationSteps.learnTools = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result getTools() { return ok(views.html.orientation.gettools.render()); }

    public static Result submitGetTools(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null) {
            user.completedOrientationSteps.getTools = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    /* PUNTO 3: PROCESO DE SELECCION */
    public static Result tinterview() { return ok(views.html.orientation.tinterview.render()); }

    public static Result submitTinterview(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.tInterview = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result pinterview() { return ok(views.html.orientation.pinterview.render()); }

    public static Result submitPinterview(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.pInterview = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result actinterview() { return ok(views.html.orientation.actinterview.render()); }

    public static Result submitActInterview(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.actInterview = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result questionsinterview() { return ok(views.html.orientation.questionsinterview.render()); }

    public static Result submitQuestionsInterview(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.questionsInterview = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    /* PUNTO 4: PLANIFICATE */
    public static Result deadline(){
        User user = SingletonDataSource.getUserByEmail(session().get("email"));
        return ok(views.html.orientation.deadline.render(user));
    }

    public static Result submitDeadLine(){
        JsonNode request = request().body().asJson();
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));

        if(user != null){
            String[][] result = new Gson().fromJson(request.toString(), new TypeToken<String[][]>() {}.getType());
            for(int i=0; i<result.length; i++){
                InterviewSchedule auxInterviewSchedule = new InterviewSchedule(result[i][0] + " " + result[i][1], result[i][2], result[i][3]);
                if(!user.interviewScheduleList.contains(auxInterviewSchedule)){
                    user.interviewScheduleList.add(auxInterviewSchedule);
                }
            }

            user.completedOrientationSteps.deadLine = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }

        return redirect("/orientation");
    }

    public static Result travel(){return ok(views.html.orientation.travel.render());}

    public static Result submitTravel(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.travel = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    /* PUNTO 5: MEJORA PROFESIONALMENTE */
    public static Result specialization() { return ok(views.html.orientation.specialization.render()); }

    public static Result submitSpecialization(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null) {
            user.completedOrientationSteps.specialization = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result bestdeals() { return ok(views.html.orientation.bestdeals.render()); }

    public static Result submitBestDeals(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.bestDeals = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result level() { return ok(views.html.orientation.level.render()); }

    public static Result submitLevel(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.level = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }

    public static Result reputation() { return ok(views.html.orientation.reputation.render()); }

    public static Result submitReputation(){
        User user = SingletonDataSource.getInstance().getUserByEmail(session().get("email"));
        if(user != null){
            user.completedOrientationSteps.reputation = String.valueOf(true);
            SingletonDataSource.getInstance().updateAllUserData(user);
        }
        return redirect("/orientation");
    }
}