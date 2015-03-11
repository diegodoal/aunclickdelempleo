package models.entities.orientation;

import com.google.gson.Gson;

/**
 * Created by Victor on 05/03/2015.
 */
public class Photo {
    //UUID of the photo in the Amazon Server
    public String id;

    public Photo(){

    }

    public Photo(String id){
        this.id = id;
    }

    public String toJsonString(){
        return new Gson().toJson(this).toString();
    }

}
