package models.entities.orientation;

import com.google.gson.Gson;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class Photo {
    //UUID of the photo in the Amazon Server
    public String id;

    public Photo(){
        this.id = "";
    }

    public Photo(String id){
        this.id = id;
    }

    public String toJsonString(){
        return new Gson().toJson(this).toString();
    }

}
