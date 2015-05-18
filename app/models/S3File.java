package models;

import com.amazonaws.services.s3.model.*;
import org.h2.util.IOUtils;
import play.Logger;
import plugins.S3Plugin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by anquegi on 10/06/14.
 */

public class S3File{

    private String bucket;

    public String name;

    public File file;

    public URL getUrl() throws MalformedURLException {
        return new URL("https://s3.amazonaws.com/" + bucket + "/" + getActualFileName());
    }

    public String getUserUrl(String photoId){
        if (S3Plugin.amazonS3 == null) {
            Logger.error("Could not load because amazonS3 was null");
            throw new RuntimeException("Could not load");
        }else{
            return ("https://s3.amazonaws.com/"+ S3Plugin.s3Bucket + "/"+photoId);
        }
    }

    private String getActualFileName() {
        return name;
    }


    public void save() {
        if (S3Plugin.amazonS3 == null) {
            Logger.error("Could not save because amazonS3 was null");
            throw new RuntimeException("Could not save");
        }
        else {
            this.bucket = S3Plugin.s3Bucket;

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, getActualFileName(), file);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
            S3Plugin.amazonS3.putObject(putObjectRequest); // upload file
        }
    }


    public void delete() {
        if (S3Plugin.amazonS3 == null) {
            Logger.error("Could not delete because amazonS3 was null");
            throw new RuntimeException("Could not delete");
        }
        else {
            S3Plugin.amazonS3.deleteObject(bucket, getActualFileName());
            this.file=null;
        }
    }

    public int getNumberOfFiles() {
        if(S3Plugin.amazonS3 == null){
            Logger.error("Could not delete because amazonS3 was null");
            throw new RuntimeException("Could not delete");
        }else {
            this.bucket = S3Plugin.s3Bucket;
            ObjectListing listing = S3Plugin.amazonS3.listObjects(bucket);
            List<S3ObjectSummary> summaries = listing.getObjectSummaries();

            while (listing.isTruncated()) {
                listing = S3Plugin.amazonS3.listNextBatchOfObjects(listing);
                summaries.addAll(listing.getObjectSummaries());
            }
            return summaries.size();
        }
    }

    public void downloadFile(String keyName){
        if (S3Plugin.amazonS3 == null) {
            Logger.error("Could not save because amazonS3 was null");
            throw new RuntimeException("Could not download");
        }
        else {
            this.bucket = S3Plugin.s3Bucket;

            String path = "public/pics/"+keyName;
            GetObjectRequest request = new GetObjectRequest(bucket, keyName);
            S3Object object = S3Plugin.amazonS3.getObject(request);
            S3ObjectInputStream objectContent = object.getObjectContent();
            try {
                IOUtils.copy(objectContent, new FileOutputStream(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
