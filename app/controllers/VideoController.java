/**
 * Created by anquegi on 09/06/14.
 */
package controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import models.S3File;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.UUID;


@Api(value = "/Video",  description = "CRUD operations about videos", position = 1)    
public class VideoController extends Controller {

    /*
    @GET
    @Path("/api/video")
    @ApiOperation(position = 1, nickname ="videos", value = "Finds a video with a given name", notes = " ",
            response = S3File.class, httpMethod = "GET")
    public static Result getVideo(@ApiParam(value = "The name of the video to search", required = true) @QueryParam("name")String name) {
        // TODO obtener los vídeos
        return TODO;
    }
    */
/*
    @POST
    @Path("/api/video")
    @ApiOperation(position = 2, nickname ="videos", value = "Creates a video",
            httpMethod = "POST")
*/
    public static Result postVideo() {
        // TODO arreglar este método para sincronizar con campña y quizá asíncrono
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadFilePart = body.getFile("upload");
        if (uploadFilePart != null) {
            S3File s3File = new S3File();

            //try {
            //    s3File.name = URLEncoder.encode(uploadFilePart.getFilename(),"UTF-8");
            //} catch (UnsupportedEncodingException e) {
            //    ok("video name invalid");
            //}

            String s = uploadFilePart.getFilename();
            if(s.contains("."))
                s3File.name = UUID.randomUUID().toString() + s.substring(s.lastIndexOf('.')) ;
            else
                s3File.name = UUID.randomUUID().toString();

            s3File.file = uploadFilePart.getFile();
            s3File.save();

            try {
                return ok(s3File.getUrl().toString());
            } catch (MalformedURLException e) {
                return ok("uploaded video but can not get url");
            }
        }
        else {
            return badRequest("File upload error");
        }

    }
/*
    @PUT
    @Path("/api/video")
    @ApiOperation(position = 3, nickname ="videos", value = "Updates a video", httpMethod = "PUT")
    public static Result putVideo() {
        // TODO method for update video
        return TODO;
    }

    @DELETE
    @Path("/api/video")
    @ApiOperation(position = 4, nickname ="videos", value = "Deletes a video", httpMethod = "DELETE")
    public static Result deleteVideo(@ApiParam(value = "The name of the video to delete", required = true) @QueryParam("name")String name){
        // TODO method for delete video
        return TODO;

    }
*/
}
