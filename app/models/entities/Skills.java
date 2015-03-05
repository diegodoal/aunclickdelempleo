package models.entities;

import java.util.List;

/**
 * Created by Victor on 05/03/2015.
 */
public class Skills {

    public List<Skill> skillList;

    public Skills(List<Skill> skillList){
        this.skillList = skillList;
    }

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
