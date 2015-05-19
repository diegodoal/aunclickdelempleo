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
import com.itextpdf.text.pdf.BaseFont;



public class Template2 {
    private Document document;
    public static final Font font1;
    public static final Font font2;
    public static final Font font3;
    public static final Font font4;
    public static final Font font5;
    public static final Font font6;

    public  static final String BACK_IMAGE = "public/images/orientation/cv-templates/CV1/ic_cv1margin.png";

    static {
        BaseFont monteserrat_regular = null;
        BaseFont monteserrat_bold = null;
        BaseFont calibri_light = null;
        try {
            monteserrat_regular = BaseFont.createFont(
                    "public/images/orientation/cv-templates/Fonts/Montserrat-Regular.ttf",
                    BaseFont.WINANSI, BaseFont.EMBEDDED);


            monteserrat_bold = BaseFont.createFont(
                    "public/images/orientation/cv-templates/Fonts/Montserrat-Bold.ttf",
                    BaseFont.WINANSI, BaseFont.EMBEDDED);

            calibri_light = BaseFont.createFont(
                    "public/images/orientation/cv-templates/Fonts/Calibri.ttf",
                    BaseFont.WINANSI, BaseFont.EMBEDDED);


        } catch (DocumentException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        font6 = new Font(calibri_light, Constants.SIZE12_T1,Font.ITALIC, Constants.COLOR_BLACK_T1);
        font5 = new Font(calibri_light, Constants.SIZE12_T1,Font.BOLD, Constants.COLOR_BLACK_T1);
        font4 = new Font(calibri_light, Constants.SIZE12_T1,Font.NORMAL, Constants.COLOR_BLACK_T1);
        font3 = new Font(monteserrat_bold, Constants.SIZE12_T2,Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_MONTSERRAT);
        font2 = new Font(monteserrat_regular, Constants.SIZE14_T2,Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_MONTSERRAT);
        font1 = new Font(monteserrat_regular, Constants.SIZE24_T2,Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_MONTSERRAT);
    }

    public void createPdf(String path, User user,  List<String> personalCharacteristics, List<Skill> skills) throws  DocumentException, IOException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        //CONTENIDO
        addImageTelephone();
        addImageEmail();
        //IMAGE
        addImage(user);
        //DATOS PERSONALES
        addPersonalInformation(user);
        //SKILLS
        addSkills( user ,personalCharacteristics, skills);
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
        //Studies and courses
        addAllTitle(user.languages,user.courses);

        //CERRAR DOCUMENTO
        document.close();

    }


    private void addPersonalInformation(User user) throws DocumentException {

        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;
        table = new PdfPTable(new float[]{0.5f,2f});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(55);
        cell.setPaddingTop(0);

        paragraph = new Paragraph(user.name.toUpperCase() + " " + user.surnames.toUpperCase(), font1);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(paragraph);
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(55);
        cell.setPaddingTop(0);

        paragraph = new Paragraph(user.birthDate + "\n", font2);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(paragraph);
        table.addCell(cell);



        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(55);
        cell.setPaddingTop(0);

        paragraph = new Paragraph(user.phoneNumber, font2);
        paragraph.setSpacingBefore(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(paragraph);
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(55);
        cell.setPaddingTop(0);

        paragraph = new Paragraph(user.email, font2);
        paragraph.setSpacingBefore(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(paragraph);
        table.addCell(cell);


        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph = new Paragraph(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        cell.setPaddingRight(10);
        cell.addElement(paragraph);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(55);
        cell.setPaddingTop(0);
        if (!user.drivingLicense.equals("No tengo carnet")) {
            paragraph = new Paragraph("\nPermiso de conducir: " + user.drivingLicense, font2);
            paragraph.setAlignment(paragraph.ALIGN_LEFT);
            cell.addElement(paragraph);
        }

        table.addCell(cell);

        document.add(table);
    }


    private void addProfessionalExperience(List<ProfessionalExperience> experienceList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<experienceList.size(); i++){

            table = new PdfPTable(new float[]{1f,0.5f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            if(i==0) {
                paragraph = new Paragraph("Practicas: ", font5);
                cell.setBorder(PdfPCell.NO_BORDER);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
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
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(experienceList.get(i).job + "." + " " +experienceList.get(i).company, font6);
            cell.setBorder(PdfPCell.NO_BORDER);

            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(experienceList.get(i).startDate + " - " +experienceList.get(i).endDate,font6);
            cell.addElement(paragraph);
            table.addCell(cell);


            document.add(table);
        }
    }
    private void addStudies(User user) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        if (!user.studyTitle.equals("")) {
            table = new PdfPTable(new float[]{1f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);


            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(user.studyTitle + ".", font4);
            cell.setBorder(PdfPCell.NO_BORDER);

            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setPaddingLeft(10);
            cell.setPaddingTop(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(user.studyLocation,font6);
            cell.addElement(paragraph);
            table.addCell(cell);

            document.add(table);
        }
    }

    private void addSoftware(List<Software> softwareList) throws DocumentException {
        Paragraph paragraph1;
        Paragraph paragraph2;
        PdfPCell cell;
        PdfPTable table;
        table = new PdfPTable(new float[]{1f});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);


        //First column
        cell = new PdfPCell();
        for (int i = 0; i < softwareList.size(); i++) {
            cell.setBorder(PdfPCell.NO_BORDER);
            if (i == 0) {
                paragraph1 = new Paragraph("Programas informáticos:", font5);
                paragraph2 = new Paragraph(softwareList.get(i).software + "." + " Nivel: " + softwareList.get(i).level, font6);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(softwareList.get(i).software + "." + " Nivel: " + softwareList.get(i).level, font6);
            }
            paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph1);
            cell.addElement(paragraph2);
        }
        table.addCell(cell);
        document.add(table);
    }

    private void addImage(User user) throws DocumentException, IOException {
        Image photo_img;
        if(!user.photo.id.equals("")){
            photo_img = Image.getInstance(String.format("https://s3.amazonaws.com/aunclickdelempleo2/"+user.photo.id));
        } else {
            photo_img = Image.getInstance(String.format("public/images/orientation/photo/ic_profile.png"));
        }

        photo_img.setAbsolutePosition(40,670);
        photo_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        photo_img.setBorder(Image.BOX);
        photo_img.setBorderWidth(10);
        photo_img.setBorderColor(BaseColor.WHITE);
        photo_img.scaleToFit(1000,130);
        document.add(photo_img);
    }
    private void addImageTelephone() throws DocumentException, IOException {
        Image phone_img;
        phone_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_mobile.png"));
        phone_img.setAbsolutePosition(175,708);
        phone_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        phone_img.setBorder(Image.BOX);
        phone_img.setBorderWidth(10);
        phone_img.setBorderColor(BaseColor.WHITE);
        phone_img.scaleToFit(1000,18);
        document.add(phone_img);
    }
    private void addImageEmail() throws DocumentException, IOException {
        Image email_img;
        email_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_mail.png"));
        email_img.setAbsolutePosition(173,690);
        email_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        email_img.setBorder(Image.BOX);
        email_img.setBorderWidth(10);
        email_img.setBorderColor(BaseColor.WHITE);
        email_img.scaleToFit(1000,11);
        document.add(email_img);
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
            paragraph = new Paragraph("Sobre mi...", font1);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Me defino como una persona de carácter " +personalCharacteristics.get(1).toLowerCase() + " y " +personalCharacteristics.get(0).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Entre mis puntos fuertes destacan las " +rankedSkills.get(0).toLowerCase() + " y las " +rankedSkills.get(1).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Considero que soy una persona activa que presenta " +rankedSkills.get(2).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph("Además, una de las características que me define es que soy " +personalCharacteristics.get(2).toLowerCase() + ".",font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
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
                cell.setPaddingLeft(35);
                cell.addElement(paragraph);
                table.addCell(cell);
            }

            document.add(table);

        }
    }
    private void addAllTitle(List<Language> languageList, List<Course> courseList) throws DocumentException {
        Paragraph paragraph1;
        Paragraph paragraph2;
        PdfPCell cell;
        PdfPTable table;
        table = new PdfPTable(new float[]{0.85f,1.7f,0.6f});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);



        //First column
        cell = new PdfPCell();
        for(int i=0; i<languageList.size(); i++) {
            cell.setBorder(PdfPCell.NO_BORDER);
            if (i == 0) {
                paragraph1 = new Paragraph("Idiomas:" , font5);
                paragraph2 = new Paragraph(languageList.get(i).language + "." + " Nivel: " +languageList.get(i).level , font6);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(languageList.get(i).language + "." + " Nivel: " +languageList.get(i).level , font6);
            }
            paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph1);
            cell.addElement(paragraph2);
        }
        table.addCell(cell);



        //First column
        cell = new PdfPCell();
        for(int j=0; j<courseList.size(); j++) {
            cell.setBorder(PdfPCell.NO_BORDER);
            if (j == 0) {
                paragraph1 = new Paragraph("Cursos:" , font5);
                paragraph2 = new Paragraph(courseList.get(j).name + "." + " Expedido por: " +courseList.get(j).company , font6);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(courseList.get(j).name + "." + " Expedido por: " +courseList.get(j).company , font6);
            }
            paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph1);
            cell.addElement(paragraph2);
        }
        table.addCell(cell);

        //First column
        cell = new PdfPCell();
        for(int j=0; j<courseList.size(); j++) {
            cell.setBorder(PdfPCell.NO_BORDER);
            if (j == 0) {
                paragraph1 = new Paragraph(" " , font1);
                paragraph2 = new Paragraph(courseList.get(j).length + " Horas", font6);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(courseList.get(j).length + " Horas", font6);
            }
            paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph1);
            cell.addElement(paragraph2);
        }
        table.addCell(cell);

        document.add(table);
    }


}



