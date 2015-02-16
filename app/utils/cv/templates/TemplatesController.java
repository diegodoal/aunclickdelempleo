package utils.cv.templates;

import com.itextpdf.text.DocumentException;
import utils.cv.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Victor on 16/02/2015.
 */
public class TemplatesController {
    public enum Template {Template1, Template2};

    public static void createPdf(Template template, String filePath, PersonalInformation personalInformation, String objetive,
                          List<WorkExperience> experienceList,
                          List<Education> educationList,
                          List<AditionalEducation> aditionalEducationList,
                          List<Language> languageList,
                          List<String> skills,
                          List<Project> projectList,
                          OtherInformation otherInformation) throws FileNotFoundException, DocumentException {
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
