package models.entities;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 05/03/2015.
 */
public class Skills {

    public List<Skill> skillList;

    public Skills(){
        this.skillList = new ArrayList<>();
    }

    public Skills(List<Skill> skillList){
        this.skillList = skillList;
    }

    public void addSkill(String name, String level){
        this.skillList.add(new Skill(name, level));
    }

    public String toJsonString(){
        return new Gson().toJson(this).toString();
    }

    /**
     * SKILL CLASS
     */
    public class Skill{

        // Level must be one of: "Normal, Bien, Excelente"
        public String name;
        public String level;

        public Skill(String name, String level){
            this.name = name;
            this.level = level;
        }
    }
}
