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
public class Project {

    private String name;
    private String description;
    private String startDate;
    private String endDate;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMMM-yyyy");

    public Project(String name, String description, Date startDate, Date endDate){
        this.name = name;
        this.description = description;
        this.startDate = dateFormatter.format(startDate);
        if(endDate == null){
            this.endDate = "Actualmente";
        }else{
            this.endDate = dateFormatter.format(endDate);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
