package utils.pdf.cv_templates;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.*;
import models.entities.User;
import models.entities.orientation.*;
import play.Logger;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class Template4 {
    private Document document;
    Font font1 = FontFactory.getFont(Constants.FONT_ARIAL_T4, Constants.SIZE12_T4, Font.BOLD, Constants.COLOR_BLACK_T4);
    Font font2 = FontFactory.getFont(Constants.FONT_ARIAL_T4, Constants.SIZE12_T4, Constants.COLOR_GRAY_T4);

    public void createPdf(String path, User user,  List<String> personalCharacteristics, List<Skill> skills) throws  DocumentException, IOException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        //CONTENIDO
        //IMAGE
        addImage(user);
        //DATOS PERSONALES
        addPersonalInformation(user);
        //STUDIES
        addStudies(user);
        //EXPERIENCE
        if(!user.currentSituation.professionalExperienceList.isEmpty()){
            addProfessionalExperience(user.currentSituation.professionalExperienceList);
        }
        //PROGRAMS
        if(!user.softwareList.isEmpty()){
            addSoftware(user.softwareList);
        }
        //COURSES
        if(!user.courses.isEmpty()){
            addCourses(user.courses);
        }
        //LANGUAGES
        if(!user.languages.isEmpty()){
            addLanguage(user.languages);
        }
        //SKILLS
        addSkills( user ,personalCharacteristics, skills);
        //CERRAR DOCUMENTO
        document.close();

    }


    private void addPersonalInformation(User user) throws DocumentException {

        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;
        table = new PdfPTable(new float[]{1f,1.45f, 0.8f});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.RIGHT);
        paragraph = new Paragraph("INFORMACIÓN PERSONAL", font1);
        cell.setBorder(PdfPCell.RIGHT);

        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);

        paragraph = new Paragraph("");
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        //  IMG column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph("");
        cell.addElement(paragraph);
        table.addCell(cell);


        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.RIGHT);
        paragraph = new Paragraph("Nombre completo", font2);
        cell.setBorder(PdfPCell.RIGHT);

        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph(user.name + " " + user.surnames, font2);
        cell.addElement(paragraph);
        table.addCell(cell);

        //  IMG column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph("");
        cell.addElement(paragraph);
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.RIGHT);
        paragraph = new Paragraph("Dirección", font2);
        cell.setBorder(PdfPCell.RIGHT);

        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph("Calle " + user.residenceAddress + " , " + " Nº " + user.residenceNumber + " , " + " CP: " + user.residenceZipCode + " , " + user.residenceCity, font2);
        cell.addElement(paragraph);
        table.addCell(cell);

        //  IMG column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph("");
        cell.addElement(paragraph);
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.RIGHT);
        paragraph = new Paragraph("Teléfono", font2);
        cell.setBorder(PdfPCell.RIGHT);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph(user.phoneNumber, font2);
        cell.addElement(paragraph);
        table.addCell(cell);

        //  IMG column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph("");
        cell.addElement(paragraph);
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.RIGHT);
        paragraph = new Paragraph("Email", font2);
        cell.setBorder(PdfPCell.RIGHT);

        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph(user.email, font2);
        cell.addElement(paragraph);
        table.addCell(cell);

        //  IMG column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph("");
        cell.addElement(paragraph);
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.RIGHT);
        paragraph = new Paragraph("Fecha de nacimiento", font2);
        cell.setBorder(PdfPCell.RIGHT);

        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph(user.birthDate, font2);
        cell.addElement(paragraph);
        table.addCell(cell);

        //  IMG column
        cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setPaddingTop(0);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph("");
        cell.addElement(paragraph);
        table.addCell(cell);

        document.add(table);
    }


    private void addProfessionalExperience(List<ProfessionalExperience> experienceList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<experienceList.size(); i++){

            table = new PdfPTable(new float[]{1f,2.25f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                paragraph = new Paragraph("EXPERIENCIA PROFESIONAL", font1);
                cell.setBorder(PdfPCell.RIGHT);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("");
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
                paragraph = new Paragraph("Título", font2);
                cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(experienceList.get(i).job,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Empresa y lugar", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(experienceList.get(i).company,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Fecha", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(experienceList.get(i).startDate + " - " + experienceList.get(i).endDate ,font2);
            cell.addElement(paragraph);
            table.addCell(cell);


            document.add(table);
        }
    }
    private void addStudies(User user) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;


            table = new PdfPTable(new float[]{1f,2.25f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(!user.studyTitle.equals("")) {
                paragraph = new Paragraph("EXPERIENCIA ACADÉMICA", font1);
                cell.setBorder(PdfPCell.RIGHT);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("");
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Título", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(user.studyTitle, font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Centro y lugar", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(user.studyLocation,font2);
            cell.addElement(paragraph);
            table.addCell(cell);


            document.add(table);
        }

    private void addSoftware(List<Software> softwareList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<softwareList.size(); i++){

            table = new PdfPTable(new float[]{1f,2.25f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                paragraph = new Paragraph("PROGRAMAS INFORMÁTICOS", font1);
                cell.setBorder(PdfPCell.RIGHT);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("");
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Nombre", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(softwareList.get(i).software,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Nivel", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(softwareList.get(i).level,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            document.add(table);
        }
    }
    private void addCourses(List<Course> courseList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<courseList.size(); i++){

            table = new PdfPTable(new float[]{1f,2.25f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                paragraph = new Paragraph("CURSOS", font1);
                cell.setBorder(PdfPCell.RIGHT);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("");
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Título", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(courseList.get(i).name,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Centro y lugar", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(courseList.get(i).company,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Número de horas", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(courseList.get(i).length,font2);
            cell.addElement(paragraph);
            table.addCell(cell);


            document.add(table);
        }
    }
    private void addLanguage(List<Language> languageList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<languageList.size(); i++){

            table = new PdfPTable(new float[]{1f,2.25f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                paragraph = new Paragraph("IDIOMAS", font1);
                cell.setBorder(PdfPCell.RIGHT);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("");
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Nombre", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(languageList.get(i).language,font2);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            paragraph = new Paragraph("Nivel", font2);
            cell.setBorder(PdfPCell.RIGHT);

            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(languageList.get(i).level,font2);
            cell.addElement(paragraph);
            table.addCell(cell);


            document.add(table);
        }
    }

    private void addImage(User user) throws DocumentException, IOException {
        Image photo_img;
        if(!user.photo.id.equals("")){
            photo_img = Image.getInstance(String.format("https://s3.amazonaws.com/aunclickdelempleo2/"+user.photo.id));
        } else {
            photo_img = Image.getInstance(String.format("public/images/orientation/photo/ic_profile.png"));
        }

        photo_img.setAbsolutePosition(450,690);
        photo_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        photo_img.setBorder(Image.BOX);
        photo_img.setBorderWidth(10);
        photo_img.setBorderColor(BaseColor.WHITE);
        photo_img.scaleToFit(1000,115);
        document.add(photo_img);
    }


    public List<String> selectSkills(List<Skill> skills){
        List<String> result = new ArrayList<>();

        for(int i=0; i<skills.size(); i++){
            if(skills.get(i).level.equals("Excelente")){
                result.add(skills.get(i).name);
            }
        }

        if(result.size() >= 3){
            return result;
        }

        for(int i=0; i<skills.size(); i++){
            if(skills.get(i).level.equals("Bien")){
                result.add(skills.get(i).name);
            }
        }

        if(result.size() >= 3){
            return result;
        }

        for(int i=0; i<skills.size(); i++){
            if(skills.get(i).level.equals("Normal")){
                result.add(skills.get(i).name);
            }
        }
        return result;
    }

    private void addSkills(User user,  List<String> personalCharacteristics, List<Skill> skills) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        List<String> rankedSkills = selectSkills(skills);
        if (personalCharacteristics.size() != 0 && rankedSkills.size() != 0) {
            table = new PdfPTable(new float[]{1f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("HABILIDADES PERSONALES", font1);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Me defino como una persona de carácter " +personalCharacteristics.get(1).toLowerCase() + " y " +personalCharacteristics.get(0).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Entre mis puntos fuertes destacan las " +rankedSkills.get(0).toLowerCase() + " y las " +rankedSkills.get(1).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Considero que soy una persona activa que presenta " +rankedSkills.get(2).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Además, una de las características que me define es que soy " +personalCharacteristics.get(2).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.addElement(paragraph);
            table.addCell(cell);

            if(!user.drivingLicense.equals("No tengo carnet")) {
                //First column
                cell = new PdfPCell();
                cell.setBorder(PdfPCell.NO_BORDER);
                paragraph = new Paragraph("Permiso de conducir: " +user.drivingLicense + ".", font2);
                cell.setBorder(PdfPCell.NO_BORDER);
                paragraph.setAlignment(Paragraph.ALIGN_LEFT);
                cell.setPaddingRight(10);
                cell.addElement(paragraph);
                table.addCell(cell);
            }

            document.add(table);

        }
    }
}

