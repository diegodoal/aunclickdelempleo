import org.junit.Test;
import utils.Files;
import utils.Utils;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Victor on 25/03/2015.
 */
public class FilesTest {

    @Test
    public void searchForOldFoldersTest(){
        File file = new File("assets/23-03-2015");
        file.mkdir();

        Files.deleteOldFolders("assets");
        //fail("Cannot delete some folders....");
        //assertEquals(Files.folderWhereSaveNewFile("assets"), "assets/"+Files.newFolderName(new Date()));

    }
}
