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



public class Template1 {
    private Document document;
    //Font font1 = FontFactory.getFont(Constants.FONT_ARIAL_T4, Constants.SIZE12_T4, Font.BOLD, Constants.COLOR_BLACK_T4);
   // Font font2 = FontFactory.getFont(Constants.FONT_ARIAL_T4, Constants.SIZE12_T4, Constants.COLOR_GRAY_T4);
    public static final Font font1;
    public static final Font font2;
    public static final Font font3;
    public static final Font font4;
     public  static final String BACK_IMAGE = "public/images/orientation/cv-templates/CV1/ic_cv1margin.png";

    static {
        BaseFont calibri_light = null;
        BaseFont caviar_dream = null;
        try {
            calibri_light = BaseFont.createFont(
                    "public/images/orientation/cv-templates/Fonts/Calibri.ttf",
                    BaseFont.WINANSI, BaseFont.EMBEDDED);
            caviar_dream = BaseFont.createFont(
                    "public/images/orientation/cv-templates/Fonts/CaviarDreams.ttf",
                    BaseFont.WINANSI, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
         font2 = new Font(calibri_light, Constants.SIZE12_T1,Font.NORMAL, Constants.COLOR_BLACK_T1);
         font1 = new Font(caviar_dream, Constants.SIZE18_T1,Font.NORMAL, Constants.COLOR_BLACK_T1);
         font3 = new Font(calibri_light, Constants.SIZE12_T1,Font.NORMAL, Constants.COLOR_GRAY_T1);
        font4 = new Font(caviar_dream, Constants.SIZE14_T1,Font.NORMAL, Constants.COLOR_BLACK_T1);
    }

    public void createPdf(String path, User user,  List<String> personalCharacteristics, List<Skill> skills) throws  DocumentException, IOException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        //CONTENIDO
        addImageBackgroundTop();
        addImageBackgroundBottom();
       addImageTelephone();
       addImageAddress();
       addImageEmail();
       addImageLine();
        addImageLine1();
        addImageLine2();
        addImageLine3();
        addImageLine4();
        addImageLine5();
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

        paragraph = new Paragraph(user.name + " " + user.surnames, font1);
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

        paragraph = new Paragraph(user.birthDate + "\n", font4);
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

        paragraph = new Paragraph("Calle " +user.residenceAddress + " Nº " + user.residenceNumber + " " + user.residenceZipCode + " " + user.residenceCity+ "\n", font2);
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
                paragraph = new Paragraph("Experiencia Profesional", font1);
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
            paragraph = new Paragraph(experienceList.get(i).job + "." + " " +experienceList.get(i).company, font2);
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
            paragraph = new Paragraph(experienceList.get(i).startDate + " - " +experienceList.get(i).endDate,font3);
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
            paragraph = new Paragraph("Estudios", font1);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
            cell.addElement(paragraph);
            table.addCell(cell);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph = new Paragraph(user.studyTitle + "." + " " +user.studyLocation , font2);
            cell.setBorder(PdfPCell.NO_BORDER);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.setPaddingRight(10);
            cell.setPaddingLeft(35);
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
                paragraph1 = new Paragraph("Programas informáticos:", font1);
                paragraph2 = new Paragraph(softwareList.get(i).software + "." + " Nivel: " + softwareList.get(i).level, font2);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(softwareList.get(i).software + "." + " Nivel: " + softwareList.get(i).level, font2);
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
    private void addImageAddress() throws DocumentException, IOException {
        Image address_img;
        address_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_location.png"));
        address_img.setAbsolutePosition(173,730);
        address_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        address_img.setBorder(Image.BOX);
        address_img.setBorderWidth(10);
        address_img.setBorderColor(BaseColor.WHITE);
        address_img.scaleToFit(1000,18);
        document.add(address_img);
    }
    private void addImageLine() throws DocumentException, IOException {
        Image line_img;
        line_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_barra_lineas3.png"));
        line_img.setAbsolutePosition(40,50);
        line_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        line_img.setBorder(Image.BOX);
        line_img.setBorderWidth(10);
        line_img.setBorderColor(BaseColor.WHITE);
        line_img.scaleToFit(1000,100);
        document.add(line_img);
    }
    private void addImageLine1() throws DocumentException, IOException {
        Image line_img;
        line_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_barra_lineas3.png"));
        line_img.setAbsolutePosition(40,150);
        line_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        line_img.setBorder(Image.BOX);
        line_img.setBorderWidth(10);
        line_img.setBorderColor(BaseColor.WHITE);
        line_img.scaleToFit(1000,100);
        document.add(line_img);
    }
    private void addImageLine2() throws DocumentException, IOException {
        Image line_img;
        line_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_barra_lineas3.png"));
        line_img.setAbsolutePosition(40,250);
        line_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        line_img.setBorder(Image.BOX);
        line_img.setBorderWidth(10);
        line_img.setBorderColor(BaseColor.WHITE);
        line_img.scaleToFit(1000,100);
        document.add(line_img);
    }
    private void addImageLine3() throws DocumentException, IOException {
        Image line_img;
        line_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_barra_lineas3.png"));
        line_img.setAbsolutePosition(40,350);
        line_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        line_img.setBorder(Image.BOX);
        line_img.setBorderWidth(10);
        line_img.setBorderColor(BaseColor.WHITE);
        line_img.scaleToFit(1000,100);
        document.add(line_img);
    }
    private void addImageLine4() throws DocumentException, IOException {
        Image line_img;
        line_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_barra_lineas3.png"));
        line_img.setAbsolutePosition(40,450);
        line_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        line_img.setBorder(Image.BOX);
        line_img.setBorderWidth(10);
        line_img.setBorderColor(BaseColor.WHITE);
        line_img.scaleToFit(1000,100);
        document.add(line_img);
    }
    private void addImageLine5() throws DocumentException, IOException {
        Image line_img;
        line_img = Image.getInstance(String.format("public/images/orientation/cv-templates/CV1/ic_barra_lineas3.png"));
        line_img.setAbsolutePosition(40,550);
        line_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        line_img.setBorder(Image.BOX);
        line_img.setBorderWidth(10);
        line_img.setBorderColor(BaseColor.WHITE);
        line_img.scaleToFit(1000,100);
        document.add(line_img);
    }

    private void addImageBackgroundTop() throws DocumentException, IOException {
        Image back_img = Image.getInstance(BACK_IMAGE);
        back_img.setAbsolutePosition((PageSize.A4.getWidth() - back_img.getScaledWidth()) / 2, (PageSize.A4.getHeight() - back_img.getScaledHeight()) / 2);
        back_img.setAbsolutePosition(0,823);
        document.add(back_img);
    }
    private void addImageBackgroundBottom() throws DocumentException, IOException {
        Image back_img = Image.getInstance(BACK_IMAGE);
        back_img.setAbsolutePosition((PageSize.A4.getWidth() - back_img.getScaledWidth()) / 2, (PageSize.A4.getHeight() - back_img.getScaledHeight()) / 2);
        back_img.setAbsolutePosition(0,0);
        document.add(back_img);
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
                paragraph1 = new Paragraph("Idiomas:" , font1);
                paragraph2 = new Paragraph(languageList.get(i).language + "." + " Nivel: " +languageList.get(i).level , font2);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(languageList.get(i).language + "." + " Nivel: " +languageList.get(i).level , font2);
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
                paragraph1 = new Paragraph("Cursos:" , font1);
                paragraph2 = new Paragraph(courseList.get(j).name + "." + " Expedido por: " +courseList.get(j).company , font2);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(courseList.get(j).name + "." + " Expedido por: " +courseList.get(j).company , font2);
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
                paragraph2 = new Paragraph(courseList.get(j).length + " Horas", font3);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                paragraph1 = new Paragraph("");
                paragraph2 = new Paragraph(courseList.get(j).length + " Horas", font3);
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

