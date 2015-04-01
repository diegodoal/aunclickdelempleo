package utils;

import org.h2.store.fs.FileUtils;

import java.io.File;
import java.text.ParseException;
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


    public static void deleteOldFolders(String mainFolder){
        Date date = new Date();
        String folderDate = new SimpleDateFormat("dd-MM-yyyy").format(date);

        File[] subDirectories = new File(mainFolder).listFiles();

        for(File file : subDirectories){
            if(file.isDirectory() && isOlder(file.getName())){
                FileUtils.deleteRecursive(file.getPath(), true);
                file.delete();
            }
        }
    }

    public static boolean isOlder(String oldFolderName){
        Date oldFolderDate = null;
        try {
            oldFolderDate = new SimpleDateFormat("dd-MM-yyyy").parse(oldFolderName);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(Utils.getDiffBetweenTwoDates(oldFolderDate, new Date()) > 1){
            return true;
        }else{
            return false;
        }

    }

}
