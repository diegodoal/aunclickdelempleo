package controllers;

import com.itextpdf.text.DocumentException;
import play.mvc.Result;
import utils.cv.Project;
import utils.cv.templates.Template1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.cv.*;
import utils.cv.templates.TemplatesController;

import static play.mvc.Results.redirect;


/**
 * Created by Victor on 16/02/2015.
 */
public class PDFController {

    public static Result fakeCV(){
        OtherInformation otherInformation = new OtherInformation("personal.website.com");
        List<WorkExperience> experienceList = new ArrayList<WorkExperience>();
        List<Education> educationList = new ArrayList<Education>();
        List<AditionalEducation> aditionalEducationList = new ArrayList<AditionalEducation>();
        List<Language> languageList = new ArrayList<Language>();
        List<String> skillsList = new ArrayList<String>();
        List<Project> projectList = new ArrayList<Project>();

        for(int i=0; i<3; i++){
            educationList.add(new Education("Universidad"+i, "Madrid", new Date(2009+i-1900, 1+i, 0), null,"Grado en ................ "+i, "Descripción del grado............."+i));
        }

        for(int i=0; i<4; i++){
            aditionalEducationList.add(new AditionalEducation("Company"+i, "Location"+i, new Date(2009+i-1900, i+1, 0), null, "Course title "+i, "Description "+i));
        }

        for(int i=0; i<5; i++){
            experienceList.add(new WorkExperience("Talentum"+i, "Telefonica", i+1, 2009+i, i+1, 2010+i, "LAB con Fundación Adecco "));
        }

        for(int i=0; i<10; i++){
            skillsList.add("Skill #"+i);
        }

        for(int i=0; i<3; i++){
            languageList.add(new Language("Language"+i, "Certificate"+i, "Level"+i));
        }

        for(int i=0; i<4; i++){
            projectList.add(new Project("Project"+i, "Esta es la descripción del proyecto. Esta es la descripción del proyecto. Esta es la descripción del proyecto. Esta es la descripción del proyecto. Esta es la descripción del proyecto. Esta es la descripción del proyecto. "+i, new Date(2009+i-1900, 1+i, 0), null));
        }

        for (int i=0; i<3; i++){
            otherInformation.addSocialNetwork("SocialNetwork"+i, "User"+i);
        }


        try {
            TemplatesController.createPdf(TemplatesController.Template.Template2, "public/pdf/template1.pdf",
                    new PersonalInformation("victor", "Garcia Zarco", new Date(1994 - 1900, 4, 20), "Española", "Calle falsa, 123", "victor.gzarco@gmail.com", 666777888),
                    "Adquirir experencia laboral sin que esto suponga una dificultad en el estudio de la carrera universitaria, además de poder aplicar los conocimientos estudiados hasta el momento en un ámbito de trabajo relacionado con las salidas profesionales que aporta el estudio de Grado.",
                    experienceList,
                    educationList,
                    aditionalEducationList,
                    languageList,
                    skillsList,
                    projectList,
                    otherInformation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return redirect(routes.Assets.at("pdf/template1.pdf"));
    }

}
