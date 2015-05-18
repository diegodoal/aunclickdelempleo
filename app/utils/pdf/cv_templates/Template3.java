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
import models.entities.orientation.ProfessionalExperience;
import models.entities.orientation.Software;
import models.entities.orientation.Language;
import models.entities.orientation.Skill;
import utils.Constants;
import java.util.List;

public class Template3 {

    public static final String BACK_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3background.png";
    public static final String SHORT_LINE_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3shortline.png";
    public static final String LONG_LINE_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3longline.png";
    public static final String PHOTO_IMAGE = "public/images/orientation/photo/ic_profile.png";
    private Document document;
    Font font1 = FontFactory.getFont(Constants.FONT_ARIAL_T4, Constants.SIZE12_T4, Font.BOLD, Constants.COLOR_BLACK_T4);
    Font font2 = FontFactory.getFont(Constants.FONT_ARIAL_T4, Constants.SIZE12_T4, Constants.COLOR_GRAY_T4);

    public void createPdf(String path, User user) throws DocumentException, IOException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();
        //CONTENT
        //IMAGES
        addBackgroundImage();
        addLineImage();
        //PERSONAL INFORMATION
        addPersonalInformation(user);
        //STUDIES
        addAcademicExperience(user);
        //EXPERIENCE
        if(!user.currentSituation.professionalExperienceList.isEmpty()) {
            addProfessionalExperience(user.currentSituation.professionalExperienceList);
        }
        //PROGRAMS
        if(!user.softwareList.isEmpty()) {
            addSoftware(user.softwareList);
        }
        //LANGUAGES
        if(!user.languages.isEmpty()) {
            addLanguages(user.languages);
        }
        //SKILLS
        addSkills(user, user.personalCharacteristics, user.skill);
        //CLOSE DOCUMENT
        document.close();
    }

    private void addBackgroundImage() throws DocumentException, IOException {
        Image back_img = Image.getInstance(BACK_IMAGE);
        back_img.setAbsolutePosition((PageSize.A4.getWidth() - back_img.getScaledWidth()) / 2,
                (PageSize.A4.getHeight() - back_img.getScaledHeight()) / 2);
        document.add(back_img);
    }

    private void addLineImage() throws DocumentException, IOException {
        Image short_line_img = Image.getInstance(SHORT_LINE_IMAGE);
        short_line_img.setAbsolutePosition(236, 758);
        short_line_img.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
        short_line_img.setBorderWidth(10);
        short_line_img.setBorderColor(BaseColor.WHITE);
        short_line_img.scaleToFit(300, 55);
        document.add(short_line_img);

        Image long_line_img = Image.getInstance(LONG_LINE_IMAGE);
        long_line_img.setAbsolutePosition(37, 577);
        long_line_img.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(500, 10);
        document.add(long_line_img);

        long_line_img.setAbsolutePosition(37, 481);
        long_line_img.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(500, 10);
        document.add(long_line_img);

        long_line_img.setAbsolutePosition(37, 338);
        long_line_img.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(500, 10);
        document.add(long_line_img);

        short_line_img.setAbsolutePosition(37, 229);
        short_line_img.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
        short_line_img.setBorderWidth(10);
        short_line_img.setBorderColor(BaseColor.WHITE);
        short_line_img.scaleToFit(300, 55);
        document.add(short_line_img);
    }

    private void addPersonalInformation(User user) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2, paragraph3, paragraph4, paragraph5, paragraph6, paragraph7;
        Image photo_img = Image.getInstance(String.format(PHOTO_IMAGE));
        PdfPCell cell;
        PdfPTable table;

        table = new PdfPTable(new float[]{3,7});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingRight(15);
        cell.setPaddingLeft(50);
        cell.setPaddingTop(30);

        //photo_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        photo_img.setBorder(Image.BOX);
        //photo_img.setBorderWidth(10);
        photo_img.setBorderColor(BaseColor.WHITE);
        photo_img.scaleToFit(1000, 115);

        cell.addElement(photo_img);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(55);
        cell.setPaddingTop(20);
        cell.setBorder(PdfPCell.NO_BORDER);

        Font font1 = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
        paragraph1 = new Paragraph("Datos Personales", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell.addElement(paragraph1);

        Font font2 = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);
        paragraph2 = new Paragraph(user.name + " " + user.surnames, font2);
        paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
        paragraph2.setSpacingBefore(10);
        cell.addElement(paragraph2);

        paragraph3 = new Paragraph(user.birthDate, font2);
        paragraph3.setAlignment(paragraph3.ALIGN_LEFT);
        cell.addElement(paragraph3);

        paragraph4 = new Paragraph("Calle " + user.residenceAddress + ", Nº " + user.residenceNumber + ", Ciudad " + user.residenceCity, font2);
        paragraph4.setAlignment(paragraph4.ALIGN_LEFT);
        cell.addElement(paragraph4);

        paragraph5 = new Paragraph("Teléfono: " + user.phoneNumber, font2);
        paragraph5.setAlignment(paragraph5.ALIGN_LEFT);
        cell.addElement(paragraph5);

        paragraph6 = new Paragraph(user.email, font2);
        paragraph6.setAlignment(paragraph6.ALIGN_LEFT);
        cell.addElement(paragraph6);

        if (!user.drivingLicense.equals("No tengo carnet")) {
            paragraph7 = new Paragraph("\nPermiso de conducir: " + user.drivingLicense, font2);
            paragraph7.setAlignment(paragraph7.ALIGN_LEFT);
            cell.addElement(paragraph7);
        }

        table.addCell(cell);

        document.add(table);
    }

    private void addAcademicExperience(User user) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell;
        PdfPTable table;

        table = new PdfPTable(new float[]{5});
        table.setWidthPercentage(100);

        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingRight(15);
        cell.setPaddingLeft(50);
        cell.setPaddingTop(25);

        Font font1 = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
        paragraph1 = new Paragraph("Experiencia académica", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell.addElement(paragraph1);

        Font font2 = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);
        paragraph2 = new Paragraph(user.studyTitle.toUpperCase() + ".\n" + user.studyLocation.toUpperCase() + ".", font2);
        paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
        paragraph2.setSpacingBefore(10);
        cell.addElement(paragraph2);

        table.addCell(cell);

        document.add(table);
    }

    private void addProfessionalExperience(List<ProfessionalExperience> experienceList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<experienceList.size(); i++) {
            table = new PdfPTable(new float[]{6,4});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(15);
            cell.setPaddingLeft(50);

            if(i==0) {
                cell.setPaddingTop(25);
            } else {
                cell.setPaddingTop(5);
            }

            if(i==0) {
                Font font = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("Experiencia Profesional", font);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.addElement(paragraph);

            paragraph = new Paragraph(experienceList.get(i).job.toUpperCase() + ".\n" +experienceList.get(i).company.toUpperCase() + ".");
            paragraph.setFont(FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL));
            paragraph.setSpacingBefore(10);
            cell.addElement(paragraph);

            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(15);
            cell.setPaddingLeft(50);

            if(i==0) {
                cell.setPaddingTop(25);
            } else {
                cell.setPaddingTop(5);
            }


            Font font = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL, BaseColor.BLACK);
            if (i == 0) {
                paragraph = new Paragraph(" ", font);
                cell.addElement(paragraph);
                paragraph = new Paragraph(experienceList.get(i).startDate.toUpperCase() + " - " + experienceList.get(i).endDate.toUpperCase() + ".", font);
                paragraph.setSpacingBefore(10);
                paragraph.setAlignment(paragraph.ALIGN_LEFT);
                cell.addElement(paragraph);
            } else {
                paragraph = new Paragraph(experienceList.get(i).startDate.toUpperCase() + " - " + experienceList.get(i).endDate.toUpperCase() + ".", font);
                paragraph.setAlignment(paragraph.ALIGN_LEFT);
                cell.addElement(paragraph);
            }
            table.addCell(cell);
            document.add(table);
        }
    }

    private void addSoftware(List<Software> softwareList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<softwareList.size(); i++) {
            table = new PdfPTable(new float[]{6,4});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(15);
            cell.setPaddingLeft(50);

            if(i==0) {
                cell.setPaddingTop(25);
            } else {
                cell.setPaddingTop(5);
            }

            if(i==0) {
                Font font = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("Informática", font);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.addElement(paragraph);

            paragraph = new Paragraph(softwareList.get(i).software + ".");
            paragraph.setFont(FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL));
            paragraph.setSpacingBefore(10);
            cell.addElement(paragraph);

            table.addCell(cell);

            //Second column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(15);
            cell.setPaddingLeft(50);

            if(i==0) {
                cell.setPaddingTop(25);
            } else {
                cell.setPaddingTop(5);
            }


            Font font = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL, BaseColor.BLACK);
            if (i == 0) {
                paragraph = new Paragraph(" ", font);
                cell.addElement(paragraph);
                paragraph = new Paragraph(softwareList.get(i).level + ".", font);
                paragraph.setSpacingBefore(10);
                paragraph.setAlignment(paragraph.ALIGN_LEFT);
                cell.addElement(paragraph);
            } else {
                paragraph = new Paragraph(softwareList.get(i).level + ".", font);
                paragraph.setAlignment(paragraph.ALIGN_LEFT);
                cell.addElement(paragraph);
            }
            table.addCell(cell);
            document.add(table);
        }
    }

    private void addLanguages(List<Language> languages) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<languages.size(); i++) {
            table = new PdfPTable(new float[]{6});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(15);
            cell.setPaddingLeft(50);

            if(i==0) {
                cell.setPaddingTop(25);
            } else {
                cell.setPaddingTop(5);
            }

            if(i==0) {
                Font font = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("Idiomas", font);
            }else{
                paragraph = new Paragraph("");
            }
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            cell.addElement(paragraph);

            paragraph = new Paragraph(languages.get(i).language + ". " + languages.get(i).level + ".");
            paragraph.setFont(FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL));
            paragraph.setSpacingBefore(10);
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    /*public List<String> selectSkills(List<Skill> skills){
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
    }*/
}
