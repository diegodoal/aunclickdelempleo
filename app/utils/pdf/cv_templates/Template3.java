package utils.pdf.cv_templates;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import models.entities.User;
import utils.Constants;

import javax.imageio.ImageReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;

public class Template3 {

    public static final String BACK_IMAGE = "public/images/orientation/cv-templates/CV3/ic_cv3background.png";
    public static final String PHOTO_IMAGE = "public/images/orientation/photo/ic_profile.png";
    private Document document;

    public void createPdf(String path, User user) throws FileNotFoundException, DocumentException, MalformedURLException, IOException {
        document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

        Image back_img = Image.getInstance(BACK_IMAGE);
        back_img.setAbsolutePosition((PageSize.A4.getWidth() - back_img.getScaledWidth()) / 2,
                                (PageSize.A4.getHeight() - back_img.getScaledHeight()) / 2);
        document.add(back_img);

        Image photo_img = Image.getInstance(String.format(PHOTO_IMAGE));
        photo_img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        photo_img.setBorder(Image.BOX);
        photo_img.setBorderWidth(10);
        photo_img.setBorderColor(BaseColor.WHITE);
        photo_img.scaleToFit(1000, 72);
        document.add(photo_img);

        Paragraph p = new Paragraph("Foobar Film Festival", new Font(Font.FontFamily.HELVETICA, 24));
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);

        String address = user.residenceAddress + " " + user.residenceNumber + " - " + user.residenceZipCode + " " + user.residenceCity;
        //addPersonalInformation(user.name, user.surnames, user.birthDate, address, user.email, user.phoneNumber);

        //document.add(Chunk.NEWLINE);

        //addEducation(user.currentSituation.educationLevelList);
        //document.add(Chunk.NEWLINE);

        document.close();
    }

    private void addPersonalInformation(String name, String surnames, String birthDate, String address, String email, String phoneNumber) throws DocumentException {
        Paragraph paragraph;
        Font font = FontFactory.getFont("Calibri", 20, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);

        document.add(new Paragraph(name.toUpperCase() + " " + surnames.toUpperCase(), font));
        document.add(new Paragraph(birthDate + " | " + address + " | " + email +" | " + phoneNumber));
    }

}
