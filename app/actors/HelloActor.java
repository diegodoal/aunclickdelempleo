package actors;

import akka.actor.UntypedActor;

/**
 * Created by Victor on 10/03/2015.
 */
public class HelloActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println(message.toString());
    }
}
