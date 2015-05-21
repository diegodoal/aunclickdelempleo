package models.entities.orientation;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class Course {

    public String name;
    public String company;
    public String length;

    public Course(String name, String company, String length){
        this.name = name;
        this.company = company;
        this.length = length;
    }
}
