package utils.cv.templates;

import com.itextpdf.text.DocumentException;
import models.entities.orientation.Skill;
import utils.Files;
import utils.cv.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class TemplatesController {
    public enum Template {Template1, Template2};

    public static void createPdf(Template template, PersonalInformation personalInformation, String objetive,
                          List<WorkExperience> experienceList,
                          List<Education> educationList,
                          List<AditionalEducation> aditionalEducationList,
                          List<Language> languageList,
                          List<String> skills,
                          List<Project> projectList,
                          OtherInformation otherInformation) throws FileNotFoundException, DocumentException {
        String filePath = Files.newPathForNewFile("assets", "pdf");
        switch (template){
            case Template1:
                Template1 template1 = new Template1();
                template1.createPdf(filePath, personalInformation, objetive, experienceList, educationList, aditionalEducationList,
                        languageList, skills, projectList, otherInformation);
                break;
            case Template2:
                Template2 template2 = new Template2();
                template2.createPdf(filePath, personalInformation, objetive, experienceList, educationList, aditionalEducationList,
                        languageList, skills, projectList, otherInformation);
        }

    }
}
