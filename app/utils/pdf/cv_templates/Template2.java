package utils.pdf.cv_templates;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.entities.User;
import utils.Constants;
import java.io.FileOutputStream;
import java.io.IOException;

public class Template2 {

    private Document document;

    public static final String LINE_POINT_IMAGE = "public/images/orientation/cv-templates/ic_cv2line.png";
    public static final Font font1;
    public static final Font font2;
    public static final Font font3;
    public static final Font font4;


    static {
        BaseFont monteserrat_regular = null;
        BaseFont monteserrat_bold = null;
        BaseFont calibri_light = null;
        try {
            monteserrat_regular = BaseFont.createFont("public/images/orientation/cv-templates/Fonts/Montserrat-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            monteserrat_bold = BaseFont.createFont("public/images/orientation/cv-templates/Fonts/Montserrat-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
           calibri_light = BaseFont.createFont("public/images/orientation/cv-templates/Fonts/Montserrat-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        font1 = new Font(monteserrat_regular, Constants.SIZE24_T2, Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_MONTSERRAT);
        font2 = new Font(monteserrat_regular, Constants.SIZE14_T2, Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_MONTSERRAT);
        font3 = new Font(monteserrat_bold, Constants.SIZE14_T2, Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_MONTSERRAT);
        font4 = new Font(calibri_light,Constants.SIZE12_T2, Font.NORMAL,Constants.T2_BASE_COLOR_CUSTOM_CALIBRI);
        //falta calibri bold

    }


    public void createPdf(String path, User user) throws DocumentException, IOException  {

        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        //CONTENIDO

        //IMAGE
        addImage(user);

        //DATOS PERSONALES
        addPersonalInformation(user);

        //STUDIES
//        addStudies(user);
        addAcademicExperience(user);

        //EXPERIENCE
        //     if(!user.currentSituation.professionalExperienceList.isEmpty()){
        //        addProfessionalExperience(user.currentSituation.professionalExperienceList);
        //   }

        //PROGRAMS
        //   if(!user.softwareList.isEmpty()){
        //     addSoftware(user.softwareList);
        //  }

        //COURSES
        // if(!user.courses.isEmpty()){
        //    addCourses(user.courses);
        // }

        //LANGUAGES
        //if(!user.languages.isEmpty()){
        //  addLanguage(user.languages);
        //}


        //SKILLS
        //addSkills( user ,personalCharacteristics, skills);
        //CERRAR DOCUMENTO
        document.close();
    }


    private void addImage(User user) throws DocumentException, IOException {
        Image photo_img;
        if(!user.photo.id.equals("")){
            photo_img = Image.getInstance(String.format("https://s3.amazonaws.com/aunclickdelempleo2/"+user.photo.id));
        } else {
            photo_img = Image.getInstance(String.format("public/images/orientation/photo/ic_profile.png"));
        }

        photo_img.setAbsolutePosition(100,690);
        photo_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        photo_img.setBorder(Image.BOX);
        photo_img.setBorderWidth(10);
        photo_img.setBorderColor(BaseColor.WHITE);
        photo_img.scaleToFit(1000,115);
        document.add(photo_img);
    }




    private void addPersonalInformation(User user) throws DocumentException {

        Paragraph paragraph1, paragraph2, paragraph3, paragraph4, paragraph5, paragraph6, paragraph7;
        //Image photo_img = Image.getInstance(String.format(PHOTO_IMAGE));
        PdfPCell cell;
        PdfPTable table;

        table = new PdfPTable(new float[]{3,7});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);

        //First column
        cell = new PdfPCell();
        cell.setPaddingLeft(55);
        cell.setPaddingTop(10);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        //Second column
        cell = new PdfPCell();
        cell.setPaddingLeft(55);
        cell.setPaddingTop(10);
        cell.setBorder(PdfPCell.NO_BORDER);

        paragraph1 = new Paragraph(user.name.toUpperCase() + " " + user.surnames.toUpperCase(), font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell.addElement(paragraph1);

        paragraph2 = new Paragraph(user.birthDate,font2);
        paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
        paragraph2.setSpacingBefore(10);
        cell.addElement(paragraph2);

        paragraph3 = new Paragraph(user.phoneNumber, font2);
        paragraph3.setAlignment(paragraph3.ALIGN_LEFT);
        cell.addElement(paragraph3);

        paragraph4 = new Paragraph(user.email, font2);
        paragraph4.setAlignment(paragraph4.ALIGN_LEFT);
        cell.addElement(paragraph4);


        if (!user.drivingLicense.equals("No tengo carnet")) {
            paragraph5 = new Paragraph("\nPermiso de conducir: " + user.drivingLicense, font2);
            paragraph5.setAlignment(paragraph5.ALIGN_LEFT);
            cell.addElement(paragraph5);
        }

        table.addCell(cell);

        document.add(table);
    }

    private void addAcademicExperience(User user) throws DocumentException, IOException {
        Paragraph paragraph1, paragraph2;
        PdfPCell cell;
        PdfPTable table;
        //table 1

        table = new PdfPTable(new float[]{5});

        table.setWidthPercentage(100);

        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingRight(15);
        cell.setPaddingLeft(50);
        cell.setPaddingTop(25);
        Image long_line_img = Image.getInstance(LINE_POINT_IMAGE);
        long_line_img.setBorderWidth(10);
        long_line_img.setBorderColor(BaseColor.BLACK);
        long_line_img.scaleToFit(475,50);
        cell.addElement(long_line_img);
        table.addCell(cell);


        //table2
        table = new PdfPTable(new float[]{5});
        table.setWidthPercentage(100);

        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingRight(15);
        cell.setPaddingLeft(50);
        cell.setPaddingTop(25);

        paragraph1 = new Paragraph(user.studyTitle.toUpperCase() + ".\n" + user.studyLocation.toUpperCase() + ".", font3);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        paragraph1.setSpacingBefore(10);
        cell.addElement(paragraph1);

        table.addCell(cell);

        document.add(table);
    }

}

