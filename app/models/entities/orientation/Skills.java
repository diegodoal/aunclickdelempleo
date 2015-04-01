package models.entities.orientation;

/**
 * Created by Victor on 11/03/2015.
 */
public class Skill {

    // Level must be one of: "Normal, Bien, Excelente"
    public String name;
    public String level;

    public Skill(String name, String level){
        this.name = name;
        this.level = level;
    }
}
