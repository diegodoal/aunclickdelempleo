package utils.cv;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class Education {

    private String company;
    private String location;
    private String startDate;
    private String endDate;
    private String courseTitle;
    private String description;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM-yyyy");


    public Education(String company, String location, Date startDate, Date endDate,
                     String courseTitle, String description){
        this.company = company;
        this.location = location;
        this.startDate = dateFormatter.format(startDate);
        if(endDate == null){
            this.endDate = "Actualmente";
        }else{
            this.endDate = dateFormatter.format(endDate);
        }
        this.courseTitle = courseTitle;
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getDescription() {
        return description;
    }
}
