import actors.EmailActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        super.onStart(app);
        ActorRef emailActor = Akka.system().actorOf(new Props(EmailActor.class));

        Cancellable hello = Akka.system().scheduler().schedule(
                Duration.create(5, TimeUnit.SECONDS), //Initial delay 0 milliseconds
                Duration.create(5, TimeUnit.SECONDS),     //Frequency 5 minutes
                emailActor,
                "sendEmails",
                Akka.system().dispatcher(), null);
    }
}