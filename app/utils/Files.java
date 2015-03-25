package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Victor on 25/03/2015.
 */
public class Files {

    public static String newPathForNewFile(String mainFolder, String extension){
        File directory = new File(mainFolder);
        Date date = new Date();
        String folderDate = new SimpleDateFormat("dd-MM-yyyy").format(date);    //Pattern for new folder
        boolean existsFolder = false;
        File[] directories = directory.listFiles();
        for(File file : directories){
            if(file.isDirectory() && file.getName().equals(folderDate)){
                existsFolder = true;
                break;
            }
        }

        if(!existsFolder){
            File newDirectory = new File(mainFolder+"/" + folderDate);
            newDirectory.mkdir();
            return newDirectory.getPath()+"/"+UUID.randomUUID().toString()+"."+extension;
        }else{
            return mainFolder+"/"+folderDate+"/"+ UUID.randomUUID().toString()+"."+extension;
        }
    }


}
