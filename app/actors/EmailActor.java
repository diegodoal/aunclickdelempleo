package actors;

import akka.actor.UntypedActor;
import play.Logger;
import utils.EmailChecker;
import utils.EmailUtil;
import utils.NextInterviews;
import utils.UserInterview;

import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 12/03/2015.
 */
public class EmailActor extends UntypedActor {


    @Override
    public void onReceive(Object message) throws Exception {
        List<UserInterview> nextInterviews = NextInterviews.getAllInterviews();
        Logger.info("[Sending "+nextInterviews.size()+" emails with next interviews at: "+new Date().toString()+"]\n");
        sendEmails(nextInterviews);
    }

    private void sendEmails(List<UserInterview> nextInterviews){
        for(int i=0; i<nextInterviews.size(); i++){
            EmailUtil.emailMaker(nextInterviews.get(i).email, "Aviso de próxima entrevista", "Hola "+nextInterviews.get(i).name+", te recordamos que tienes " +
                    "una entrevista programada. Los datos son: \n" +
                    "Empresa: "+nextInterviews.get(i).interviewSchedule.company+"\n" +
                    "Dirección: "+nextInterviews.get(i).interviewSchedule.address+"\n" +
                    "Fecha: "+nextInterviews.get(i).interviewSchedule.date);
        }
    }
}
