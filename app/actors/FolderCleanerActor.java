package actors;

import akka.actor.UntypedActor;
import play.Logger;
import utils.Files;

import java.io.File;
import java.util.Date;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class FolderCleanerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        Logger.info("[Deleting old folders at: " + new Date().toString() + "]\n");
        Files.deleteOldFolders("public/pdf");
    }
}
