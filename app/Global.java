import actors.HelloActor;
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
        ActorRef helloActor = Akka.system().actorOf(new Props(HelloActor.class));

        Cancellable hello = Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
                Duration.create(5, TimeUnit.SECONDS),     //Frequency 5 minutes
                helloActor,
                "hello",
                Akka.system().dispatcher(), null);
    }
}