import actors.EmailActor;
import actors.FolderCleanerActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import models.datasource.ConfDataSource;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        super.onStart(app);

        //Initialize database
        ConfDataSource.initializeAdminUser();

        ActorRef folderCleanerActor = Akka.system().actorOf(new Props(FolderCleanerActor.class));

        Cancellable cleaner = Akka.system().scheduler().schedule(
                Duration.create(2, TimeUnit.SECONDS),
                Duration.create(24, TimeUnit.HOURS),
                folderCleanerActor,
                "cleanFolders",
                Akka.system().dispatcher(), null
        );

        ActorRef emailActor = Akka.system().actorOf(new Props(EmailActor.class));

        Cancellable hello = Akka.system().scheduler().schedule(
                Duration.create(5, TimeUnit.SECONDS), //Initial delay 5 seconds
                Duration.create(12, TimeUnit.HOURS),     //Frequency 12 hours
                emailActor,
                "sendEmails",
                Akka.system().dispatcher(), null);
    }


}