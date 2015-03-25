import actors.EmailActor;
import actors.FolderCleanerActor;
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


        ActorRef folderCleanerActor = Akka.system().actorOf(new Props(FolderCleanerActor.class));

        Cancellable cleaner = Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.MILLISECONDS),
                Duration.create(10, TimeUnit.SECONDS),
                folderCleanerActor,
                "cleanFolders",
                Akka.system().dispatcher(), null
        );

        ActorRef emailActor = Akka.system().actorOf(new Props(EmailActor.class));

        Cancellable hello = Akka.system().scheduler().schedule(
                Duration.create(5, TimeUnit.SECONDS), //Initial delay 0 milliseconds
                Duration.create(24, TimeUnit.HOURS),     //Frequency 5 minutes
                emailActor,
                "sendEmails",
                Akka.system().dispatcher(), null);
    }


}