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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.*;
import models.entities.User;
import models.entities.orientation.ProfessionalExperience;
import models.entities.orientation.Software;
import models.entities.orientation.Language;
import models.entities.orientation.Skill;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Template3 {

    public static final String BACK_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3background.png";
    public static final String SHORT_LINE_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3shortline.png";
    public static final String LONG_LINE_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3longline.png";
    private Document document;

    public static final Font font1;
    public static final Font font2;
    public static final Font font3;

    static {
        BaseFont roboto_light = null;
        BaseFont roboto_thin = null;
        try {
            roboto_light = BaseFont.createFont("public/images/orientation/cv-templates/Fonts/Roboto-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            roboto_thin = BaseFont.createFont("public/images/orientation/cv-templates/Fonts/Roboto-Thin.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        font1 = new Font(roboto_light, Constants.SIZE14_T3, Font.NORMAL, Constants.COLOR_BLUE_T3);
        font2 = new Font(roboto_thin, Constants.SIZE14_T3, Font.NORMAL, Constants.COLOR_BLACK_T3);
        font3 = new Font(roboto_thin, Constants.SIZE12_T3, Font.NORMAL, Constants.COLOR_BLACK_T3);
    }

    public void createPdf(String path, User user, List<String> personalCharacteristics, List<Skill> skills) throws DocumentException, IOException {
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
        addSkills(user, personalCharacteristics, skills);
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
        short_line_img.setAbsolutePosition(213, 756);
        short_line_img.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
        short_line_img.setBorderWidth(10);
        short_line_img.setBorderColor(BaseColor.WHITE);
        short_line_img.scaleToFit(300, 55);
        document.add(short_line_img);
    }

    private void addPersonalInformation(User user) throws DocumentException, IOException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;
        Image photo_img;
        if(!user.photo.id.equals("")){
            photo_img = Image.getInstance(String.format("https://s3.amazonaws.com/aunclickdelempleo2/" + user.photo.id));
        } else {
            photo_img = Image.getInstance(String.format("public/images/orientation/photo/ic_profile.png"));
        }

        table = new PdfPTable(new float[]{3,7});
        table.setWidthPercentage(100);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingRight(15);
        cell.setPaddingLeft(50);
        cell.setPaddingTop(30);

        photo_img.setBorder(Image.BOX);
        photo_img.setBorderColor(BaseColor.WHITE);
        photo_img.scaleToFit(1000, 115);

        cell.addElement(photo_img);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(55);
        cell.setPaddingTop(19);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph = new Paragraph("Datos Personales", font1);
        paragraph.setAlignment(paragraph.ALIGN_LEFT);
        cell.addElement(paragraph);

        paragraph = new Paragraph(user.name + " " + user.surnames, font2);
        paragraph.setAlignment(paragraph.ALIGN_LEFT);
        paragraph.setSpacingBefore(8);
        cell.addElement(paragraph);

        paragraph = new Paragraph(user.birthDate, font3);
        paragraph.setAlignment(paragraph.ALIGN_LEFT);
        cell.addElement(paragraph);

        paragraph = new Paragraph("Calle " + user.residenceAddress + ", Nº " + user.residenceNumber + ", Ciudad " + user.residenceCity, font3);
        paragraph.setAlignment(paragraph.ALIGN_LEFT);
        cell.addElement(paragraph);

        paragraph = new Paragraph("Teléfono: " + user.phoneNumber, font3);
        paragraph.setAlignment(paragraph.ALIGN_LEFT);
        cell.addElement(paragraph);

        paragraph = new Paragraph(user.email, font3);
        paragraph.setAlignment(paragraph.ALIGN_LEFT);
        cell.addElement(paragraph);

        if (!user.drivingLicense.equals("No tengo carnet")) {
            paragraph = new Paragraph("Permiso de conducir: " + user.drivingLicense, font3);
            paragraph.setAlignment(paragraph.ALIGN_LEFT);
            paragraph.setSpacingBefore(10);
            cell.addElement(paragraph);
        }

        table.addCell(cell);
        document.add(table);
    }

    private void addAcademicExperience(User user) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell1, cell2, cell3;
        PdfPTable table1, table2, table3;

        // TABLE 1
        table1 = new PdfPTable(new float[]{5});
        table1.setWidthPercentage(100);
        cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPaddingRight(15);
        cell1.setPaddingLeft(50);
        cell1.setPaddingTop(15);

        paragraph1 = new Paragraph("Experiencia académica", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell1.addElement(paragraph1);
        table1.addCell(cell1);
        document.add(table1);

        // TABLE 2
        table2 = new PdfPTable(new float[]{9,1});
        table2.setWidthPercentage(100);

        // First column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPaddingTop(5);
        Image long_line_img = Image.getInstance(LONG_LINE_IMAGE);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(475, 50);
        cell2.addElement(long_line_img);
        //cell2.setBorderColor(Constants.COLOR_BLUE_T3);
        //cell2.setBorderWidthTop(2);
        table2.addCell(cell2);

        // Second column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell2);

        document.add(table2);

        // TABLE 3
        table3 = new PdfPTable(new float[]{5});
        table3.setWidthPercentage(100);
        cell3 = new PdfPCell();
        cell3.setBorder(PdfPCell.NO_BORDER);
        cell3.setPaddingRight(15);
        cell3.setPaddingLeft(50);
        cell3.setPaddingTop(-1);
        paragraph2 = new Paragraph(user.studyTitle.toUpperCase() + ".\n" + user.studyLocation.toUpperCase() + ".", font3);
        paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
        cell3.addElement(paragraph2);
        table3.addCell(cell3);
        document.add(table3);
    }

    private void addProfessionalExperience(List<ProfessionalExperience> experienceList) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell1, cell2, cell3;
        PdfPTable table1, table2, table3;

        // TABLE 1
        table1 = new PdfPTable(new float[]{5});
        table1.setWidthPercentage(100);
        cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPaddingRight(15);
        cell1.setPaddingLeft(50);
        cell1.setPaddingTop(15);

        paragraph1 = new Paragraph("Experiencia Profesional", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell1.addElement(paragraph1);
        table1.addCell(cell1);
        document.add(table1);

        // TABLE 2
        table2 = new PdfPTable(new float[]{9,1});
        table2.setWidthPercentage(100);

        // First column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPaddingTop(5);
        Image long_line_img = Image.getInstance(LONG_LINE_IMAGE);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(475, 50);
        cell2.addElement(long_line_img);
        table2.addCell(cell2);

        // Second column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell2);

        document.add(table2);

        // TABLE 3
        for(int i=0; i<experienceList.size(); i++) {
            table3 = new PdfPTable(new float[]{6,4});
            table3.setWidthPercentage(100);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            if(i == 0) {
                cell3.setPaddingTop(-1);
            }

            paragraph2 = new Paragraph(experienceList.get(i).job.toUpperCase() + ".\n" +experienceList.get(i).company.toUpperCase() + ".", font3);
            paragraph2.setSpacingBefore(10);
            cell3.addElement(paragraph2);

            table3.addCell(cell3);

            //Second column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            if(i == 0) {
                cell3.setPaddingTop(-1);
            }

            paragraph2 = new Paragraph(experienceList.get(i).startDate.toUpperCase() + " - " + experienceList.get(i).endDate.toUpperCase() + ".", font3);
            paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
            cell3.addElement(paragraph2);

            table3.addCell(cell3);
            document.add(table3);
        }
    }

    private void addSoftware(List<Software> softwareList) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell1, cell2, cell3;
        PdfPTable table1, table2, table3;

        // TABLE 1
        table1 = new PdfPTable(new float[]{5});
        table1.setWidthPercentage(100);
        cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPaddingRight(15);
        cell1.setPaddingLeft(50);
        cell1.setPaddingTop(15);

        paragraph1 = new Paragraph("Programas informáticos", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell1.addElement(paragraph1);
        table1.addCell(cell1);
        document.add(table1);

        // TABLE 2
        table2 = new PdfPTable(new float[]{9,1});
        table2.setWidthPercentage(100);

        // First column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPaddingTop(5);
        Image long_line_img = Image.getInstance(LONG_LINE_IMAGE);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(475, 50);
        cell2.addElement(long_line_img);
        table2.addCell(cell2);

        // Second column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell2);

        document.add(table2);

        // TABLE 3
        for(int i=0; i<softwareList.size(); i++) {
            table3 = new PdfPTable(new float[]{6,4});
            table3.setWidthPercentage(100);
            table3.setSpacingBefore(5);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            if(i == 0) {
                cell3.setPaddingTop(-1);
            }

            paragraph2 = new Paragraph(softwareList.get(i).software + ".", font3);
            paragraph2.setSpacingBefore(10);
            cell3.addElement(paragraph2);

            table3.addCell(cell3);

            //Second column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            if(i == 0) {
                cell3.setPaddingTop(-1);
            }

            paragraph2 = new Paragraph(softwareList.get(i).level + ".", font3);
            paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
            cell3.addElement(paragraph2);

            table3.addCell(cell3);
            document.add(table3);
        }
    }

    private void addLanguages(List<Language> languages) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell1, cell2, cell3;
        PdfPTable table1, table2, table3;

        // TABLE 1
        table1 = new PdfPTable(new float[]{5});
        table1.setWidthPercentage(100);
        cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPaddingRight(15);
        cell1.setPaddingLeft(50);
        cell1.setPaddingTop(15);

        paragraph1 = new Paragraph("Idiomas", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell1.addElement(paragraph1);
        table1.addCell(cell1);
        document.add(table1);

        // TABLE 2
        table2 = new PdfPTable(new float[]{9,1});
        table2.setWidthPercentage(100);

        // First column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPaddingTop(5);
        Image long_line_img = Image.getInstance(LONG_LINE_IMAGE);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(475, 50);
        cell2.addElement(long_line_img);
        table2.addCell(cell2);

        // Second column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell2);

        document.add(table2);

        //TABLE 3
        for(int i=0; i<languages.size(); i++) {
            table3 = new PdfPTable(new float[]{6});
            table3.setWidthPercentage(100);
            table3.setSpacingBefore(5);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            if(i == 0) {
                cell3.setPaddingTop(-1);
            }

            paragraph2 = new Paragraph(languages.get(i).language + ". " + languages.get(i).level + ".", font3);
            paragraph2.setSpacingBefore(10);
            cell3.addElement(paragraph2);

            table3.addCell(cell3);
            document.add(table3);
        }
    }

    public List<String> selectSkills(List<Skill> skills){
        List<String> result = new ArrayList<String>();

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

    private void addSkills(User user,  List<String> personalCharacteristics, List<Skill> skills) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell1, cell2, cell3;
        PdfPTable table1, table2, table3;

        // TABLE 1
        table1 = new PdfPTable(new float[]{5});
        table1.setWidthPercentage(100);
        cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPaddingRight(15);
        cell1.setPaddingLeft(50);
        cell1.setPaddingTop(15);

        paragraph1 = new Paragraph("Cualidades", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell1.addElement(paragraph1);
        table1.addCell(cell1);
        document.add(table1);

        // TABLE 2
        table2 = new PdfPTable(new float[]{9,1});
        table2.setWidthPercentage(100);

        // First column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPaddingTop(5);
        Image long_line_img = Image.getInstance(LONG_LINE_IMAGE);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.WHITE);
        long_line_img.scaleToFit(475, 50);
        cell2.addElement(long_line_img);
        table2.addCell(cell2);

        // Second column
        cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell2);

        document.add(table2);

        // TABLE 3
        List<String> rankedSkills = selectSkills(skills);
        if (personalCharacteristics.size() != 0 && rankedSkills.size() != 0) {
            table3 = new PdfPTable(new float[]{6});
            table3.setWidthPercentage(100);
            table3.setSpacingBefore(5);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2 = new Paragraph("Me defino como una persona de carácter " +personalCharacteristics.get(1).toLowerCase() + " y " +personalCharacteristics.get(0).toLowerCase() + ".", font3);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            cell3.setPaddingTop(-1);

            cell3.addElement(paragraph2);
            table3.addCell(cell3);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2 = new Paragraph("Entre mis puntos fuertes destacan las " +rankedSkills.get(0).toLowerCase() + " y las " +rankedSkills.get(1).toLowerCase() + ".", font3);
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            cell3.addElement(paragraph2);
            table3.addCell(cell3);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2 = new Paragraph("Considero que soy una persona activa que presenta " +rankedSkills.get(2).toLowerCase() + ".", font3);
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            cell3.addElement(paragraph2);
            table3.addCell(cell3);

            //First column
            cell3 = new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2 = new Paragraph("Además, una de las características que me define es que soy " +personalCharacteristics.get(2).toLowerCase() + ".", font3);
            cell3.setBorder(PdfPCell.NO_BORDER);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            cell3.setPaddingRight(15);
            cell3.setPaddingLeft(50);
            cell3.addElement(paragraph2);
            table3.addCell(cell3);

            document.add(table3);

        }
    }
}
