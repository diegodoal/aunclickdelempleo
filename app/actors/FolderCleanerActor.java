package actors;

import akka.actor.UntypedActor;
import play.Logger;
import utils.Files;

import java.io.File;
import java.util.Date;

/**
 * Created by Victor on 25/03/2015.
 */
public class FolderCleanerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        Logger.info("[Deleting old folders at: " + new Date().toString() + "]\n");
        Files.deleteOldFolders("assets");
    }
}
