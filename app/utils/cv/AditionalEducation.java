package utils.cv;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Victor on 13/02/2015.
 */
public class AditionalEducation {

    private String company;
    private String location;
    private String startDate;
    private String endDate;
    private String courseTitle;
    private String description;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM-yyyy");

    public AditionalEducation(String company, String location, Date startDate, Date endDate,
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

    public String getDescription() {
        return description;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLocation() {
        return location;
    }
}
