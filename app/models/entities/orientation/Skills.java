package models.entities.orientation;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import models.entities.orientation.CurrentSituation.ProfessionalExperience;

/**
 * Created by Victor on 11/03/2015.
 */
public class Skills {
	public List<String> skillsList;
	
	  public Skills(){
	        this.skillsList = new ArrayList<>();
	    }
	  
	  public Skills(List<String> skillsList){
	        this.skillsList = skillsList;
	    }
	  
	  public void addskills(String level){
	        this.skillsList.add(level);
	    }

	  
	  public String toJsonString(){
	        return new Gson().toJson(this).toString();
	    }
	  
/*
    public String name;
    public String level;

    public Skill(String name, String level){
        this.name = name;
        this.level = level;
    }*/
}




    