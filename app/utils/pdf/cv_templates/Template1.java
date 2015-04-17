package utils.pdf.cv_templates;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.entities.User;
import models.entities.orientation.*;
import models.entities.orientation.Language;
import utils.Constants;
import utils.cv.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Victor on 16/02/2015.
 */
public class Template1 {

    private Document document;

    public void createPdf(String path, User user) throws FileNotFoundException, DocumentException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();

        String address = user.residenceAddress + " " + user.residenceNumber + " - " + user.residenceZipCode + " " + user.residenceCity;
        addPersonalInformation(user.name, user.surnames, user.birthDate, address, user.email, user.phoneNumber);

        document.add(Chunk.NEWLINE);

        if(!user.currentSituation.professionalExperienceList.isEmpty()){
            addProfessionalExperience(user.currentSituation.professionalExperienceList);
            document.add(Chunk.NEWLINE);
        }

        //addEducation(user.currentSituation.educationLevelList);
        //document.add(Chunk.NEWLINE);

        if(!user.courses.isEmpty()){
            addCourses(user.courses);
            document.add(Chunk.NEWLINE);
        }

        if(!user.languages.isEmpty()){
            addLanguages(user.languages);
            document.add(Chunk.NEWLINE);
        }

        if(!user.skill.isEmpty()){
            addSkills(user.skill);
        }

        document.close();
    }

    private void addPersonalInformation(String name, String surnames, String birthDate, String address, String email, String phoneNumber) throws DocumentException {
        Paragraph paragraph;
        Font font = FontFactory.getFont("Calibri", 20, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);

        document.add(new Paragraph(name.toUpperCase() + " " + surnames.toUpperCase(), font));
        document.add(new Paragraph(birthDate + " | " + address + " | " + email +" | " + phoneNumber));
    }

    private void addProfessionalExperience(List<ProfessionalExperience> experienceList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<experienceList.size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("EXPERIENCIA", font);
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

            paragraph = new Paragraph(experienceList.get(i).job.toUpperCase() + " - " +experienceList.get(i).company.toUpperCase());
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph(experienceList.get(i).startDate.toUpperCase() + " - " + experienceList.get(i).endDate.toUpperCase(), font);
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addEducation(List<Education> educationList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<educationList.size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("EDUCACIÓN", font);
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

            paragraph = new Paragraph(educationList.get(i).getCompany().toUpperCase() + ", " +
                    educationList.get(i).getLocation().toUpperCase() + ", ("+
                    educationList.get(i).getStartDate().toUpperCase() + " - " + educationList.get(i).getEndDate().toUpperCase() + ")");
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph(educationList.get(i).getCourseTitle(), font);
            cell.addElement(paragraph);

            paragraph = new Paragraph(educationList.get(i).getDescription());
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addCourses(List<Course> courses) throws DocumentException{
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<courses.size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("CURSOS", font);
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

            paragraph = new Paragraph(courses.get(i).name);
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph(courses.get(i).company, font);
            cell.addElement(paragraph);

            paragraph = new Paragraph("Duración: " + courses.get(i).length + " horas.");
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addLanguages(List<Language> languageList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<languageList.size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("IDIOMAS", font);
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

            paragraph = new Paragraph(languageList.get(i).language.toUpperCase());
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph("Nivel: " + languageList.get(i).level, font);
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addSkills(List<Skill> skillsList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<skillsList.size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("HABILIDADES", font);
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

            paragraph = new Paragraph(skillsList.get(i).name.toUpperCase());
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph("Nivel: " + skillsList.get(i).level, font);
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }
}
