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
import sun.font.FontFamily;
import utils.Constants;


public class Template3 {

    public static final String BACK_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3background.png";
    public static final String SHORT_LINE_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3shortline.png";
    public static final String PHOTO_IMAGE = "public/images/orientation/photo/ic_profile.png";
    private Document document;

    public void createPdf(String path, User user) throws DocumentException, IOException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();

        addBackgroundImage();
        addLineImage();
        addPersonalInformation(user);
        addAcademicExperience(user);


        //String address = user.residenceAddress + " " + user.residenceNumber + " - " + user.residenceZipCode + " " + user.residenceCity;

        //document.add(Chunk.NEWLINE);

        //addEducation(user.currentSituation.educationLevelList);
        //document.add(Chunk.NEWLINE);

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
        short_line_img.setAbsolutePosition(225, 758);
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
        Paragraph paragraph1, paragraph2, paragraph3, paragraph4, paragraph5, paragraph6, paragraph7;
        Image photo_img = Image.getInstance(String.format(PHOTO_IMAGE));
        PdfPCell cell;
        PdfPTable table;

        table = new PdfPTable(new float[]{5});
        table.setWidthPercentage(100);

        //First column
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingRight(15);
        cell.setPaddingLeft(50);
        cell.setPaddingTop(30);

        Font font1 = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
        paragraph1 = new Paragraph("Experiencia académica", font1);
        paragraph1.setAlignment(paragraph1.ALIGN_LEFT);
        cell.addElement(paragraph1);

        Font font2 = FontFactory.getFont("Arial", Font.DEFAULTSIZE, Font.NORMAL, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);
        paragraph2 = new Paragraph(user.studyTitle + ".\n" + user.studyLocation + ".", font2);
        paragraph2.setAlignment(paragraph2.ALIGN_LEFT);
        paragraph2.setSpacingBefore(10);
        cell.addElement(paragraph2);

        table.addCell(cell);

        document.add(table);
    }
}
