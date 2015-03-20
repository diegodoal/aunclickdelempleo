package actors;

import akka.actor.UntypedActor;
import models.datasource.ObjectDataSource;
import models.datasource.UserDataSource;
import play.Logger;
import utils.EmailChecker;

import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 12/03/2015.
 */
public class EmailActor extends UntypedActor {


    @Override
    public void onReceive(Object message) throws Exception {
        Logger.info("["+new Date().toString()+"]\n");
    }

    private String getNextInterviewsAndSendEmail(){
        EmailChecker emailChecker = new EmailChecker();

        /**
         * Recives the days before the interview
         */
        List<EmailChecker.UserInterview> usersToNotify = emailChecker.getUsersWithNextInterviews(1);

        if(usersToNotify == null){
            return "No users to notify";
        }

        String result = "\n";

        for(int i=0; i<usersToNotify.size(); i++){
            result+= "{"+usersToNotify.get(i).name + " - "+usersToNotify.get(i).email + " : "+usersToNotify.get(i).interviewSchedule.date.toString() +"}\n";
        }
        return result;
    }

    private String getUser(){
        ObjectDataSource objectDataSource = new ObjectDataSource();
        String result = objectDataSource.getAllUsers().toString();
        objectDataSource.close();
        return result;
    }
}
