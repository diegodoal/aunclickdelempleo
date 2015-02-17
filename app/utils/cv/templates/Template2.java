package utils.cv.templates;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import utils.Constants;
import utils.cv.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Victor on 16/02/2015.
 */
public class Template2 {

    private Document document;

    public void createPdf(String filePath, PersonalInformation personalInformation, String objetive,
                          List<WorkExperience> experienceList,
                          List<Education> educationList,
                          List<AditionalEducation> aditionalEducationList,
                          List<Language> languageList,
                          List<String> skills,
                          List<Project> projectList,
                          OtherInformation otherInformation) throws FileNotFoundException, DocumentException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        addPersonalInformation(personalInformation);

        document.add(Chunk.NEWLINE);

        addObjetive(objetive);

        document.add(Chunk.NEWLINE);

        addWorkExperience(experienceList);

        document.add(Chunk.NEWLINE);

        addEducation(educationList);

        document.add(Chunk.NEWLINE);

        addAditionalEducation(aditionalEducationList);

        document.add(Chunk.NEWLINE);

        addLanguages(languageList);

        document.add(Chunk.NEWLINE);

        addSkills(skills);

        document.add(Chunk.NEWLINE);

        addProjects(projectList);

        document.add(Chunk.NEWLINE);

        addOtherInformation(otherInformation);

        document.close();
    }

    private void addPersonalInformation(PersonalInformation personalInformation) throws DocumentException {
        Paragraph paragraph;
        Font font = FontFactory.getFont(Constants.T2_FONT_STYLE, 20, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);

        document.add(new Paragraph(personalInformation.getName().toUpperCase() + " " +personalInformation.getSurname().toUpperCase(), font));

        document.add(Chunk.NEWLINE);

        paragraph = new Paragraph("Datos Personales", FontFactory.getFont(Constants.T2_FONT_STYLE, Constants.T2_FONT_SIZE_TITLE_2, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
        paragraph.setSpacingAfter(0);
        document.add(paragraph);

        LineSeparator lineSeparator = new LineSeparator(1, 100, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER, -2);
        document.add(lineSeparator);

        PdfPTable table;
        PdfPCell cell;

        table = new PdfPTable(new float[]{2,2});
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);

        /* FIRST ROW */
            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            PdfPTable sub_table = new PdfPTable(new float[]{2, 2});
            sub_table.setWidthPercentage(100);
            cell.addElement(sub_table);

            //Fist column of first column
            PdfPCell sub_cell = new PdfPCell();
            paragraph = new Paragraph("Fecha de nacimento:", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);

            //Second column of first column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph(personalInformation.getBirthDate(), FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);
            table.addCell(cell);
            cell.addElement(sub_table);

            //Second column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            sub_table = new PdfPTable(new float[]{2, 4});
            sub_table.setWidthPercentage(90);
            cell.addElement(sub_table);

            //Fist column of second column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph("Teléfono:", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);

            //Second column of second column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph(""+personalInformation.getPhoneNumber(), FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);
            table.addCell(cell);
            cell.addElement(sub_table);

        /* SECOND ROW */
            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            sub_table = new PdfPTable(new float[]{2, 2});
            sub_table.setWidthPercentage(100);
            cell.addElement(sub_table);

            //Fist column of first column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph("Nacionalidad:", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);

            //Second column of first column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph(personalInformation.getNationality(), FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);
            table.addCell(cell);
            cell.addElement(sub_table);

            //Second column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            sub_table = new PdfPTable(new float[]{2, 4});
            sub_table.setWidthPercentage(90);
            cell.addElement(sub_table);

            //Fist column of second column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph("Email:", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);

            //Second column of second column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph(personalInformation.getEmail(), FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);
            table.addCell(cell);
            cell.addElement(sub_table);

        /* THIRD ROW */
            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            sub_table = new PdfPTable(new float[]{2, 2});
            sub_table.setWidthPercentage(100);
            cell.addElement(sub_table);

            //Fist column of first column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph("Dirección:", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);

            //Second column of first column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph(personalInformation.getAddress(), FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);
            table.addCell(cell);
            cell.addElement(sub_table);

            //Second column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            sub_table = new PdfPTable(new float[]{2, 4});
            sub_table.setWidthPercentage(90);
            cell.addElement(sub_table);

            //Fist column of second column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph("", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);

            //Second column of second column
            sub_cell = new PdfPCell();
            paragraph = new Paragraph("", FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
            sub_cell.setBorder(PdfPCell.NO_BORDER);
            sub_cell.addElement(paragraph);
            sub_table.addCell(sub_cell);
            table.addCell(cell);
            cell.addElement(sub_table);

        document.add(table);
    }

    private void addObjetive(String objetive) throws DocumentException {
        Paragraph paragraph;
        PdfPTable table;
        PdfPCell cell;

        paragraph = new Paragraph("Objetivos", FontFactory.getFont(Constants.T2_FONT_STYLE, Constants.T2_FONT_SIZE_TITLE_2, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
        paragraph.setSpacingAfter(0);
        document.add(paragraph);

        LineSeparator lineSeparator = new LineSeparator(1, 100, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER, -2);
        document.add(lineSeparator);

        Font font = FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);
        paragraph = new Paragraph(objetive, font);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);

    }

    private void addWorkExperience(List<WorkExperience> experienceList) throws DocumentException {
        Paragraph paragraph;
        PdfPTable table;
        PdfPCell cell;

        paragraph = new Paragraph("Experiencia", FontFactory.getFont(Constants.T2_FONT_STYLE, Constants.T2_FONT_SIZE_TITLE_2, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY));
        paragraph.setSpacingAfter(0);
        document.add(paragraph);

        LineSeparator lineSeparator = new LineSeparator(1, 100, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER, -2);
        document.add(lineSeparator);

        for (int i = 0; i < experienceList.size(); i++) {
            table = new PdfPTable(new float[]{2, 4, 2});

            Font bold_font = FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Font.BOLD, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);
            Font normal_font = FontFactory.getFont(Constants.T2_FONT_STYLE, Font.DEFAULTSIZE, Constants.T2_BASE_COLOR_CUSTOM_DARK_GREY);

            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            cell = new PdfPCell();
            paragraph = new Paragraph(experienceList.get(i).getCompany(), normal_font);
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            cell.addElement(paragraph);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(10);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell();
            paragraph = new Paragraph(experienceList.get(i).getTitle(), bold_font);
            cell.addElement(paragraph);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(10);
            table.addCell(cell);

            cell = new PdfPCell();
            paragraph = new Paragraph(experienceList.get(i).getStartDate() + " - " + experienceList.get(i).getEndDate(), normal_font);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setRowspan(2);
            cell.addElement(paragraph);
            table.addCell(cell);

            cell = new PdfPCell();
            paragraph = new Paragraph(experienceList.get(i).getDescription(), normal_font);
            cell.addElement(paragraph);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingRight(10);
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

    private void addAditionalEducation(List<AditionalEducation> educationList) throws DocumentException {
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
                paragraph = new Paragraph("FORMACIÓN COMPLEMENTARIA", font);
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

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD);
            paragraph = new Paragraph(educationList.get(i).getCourseTitle(), font);
            cell.addElement(paragraph);

            paragraph = new Paragraph(educationList.get(i).getCompany().toUpperCase() + ", " +
                    educationList.get(i).getLocation().toUpperCase() + ", ("+
                    educationList.get(i).getStartDate().toUpperCase() + " - " + educationList.get(i).getEndDate().toUpperCase() + ")");
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY));
            cell.addElement(paragraph);

            paragraph = new Paragraph(educationList.get(i).getDescription());
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

            paragraph = new Paragraph(languageList.get(i).getLanguage().toUpperCase());
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph(languageList.get(i).getCertificate(), font);
            cell.addElement(paragraph);

            paragraph = new Paragraph(languageList.get(i).getLevel());
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addSkills(List<String> skillsList) throws DocumentException {
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

            paragraph = new Paragraph(skillsList.get(i).toString());
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addProjects(List<Project> projectList) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;

        for(int i=0; i<projectList.size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(i==0) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("PROYECTOS", font);
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

            paragraph = new Paragraph(projectList.get(i).getName().toUpperCase());
            paragraph.setFont(FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD));
            cell.addElement(paragraph);

            Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, BaseColor.GRAY);
            paragraph = new Paragraph(projectList.get(i).getStartDate() +" - " + projectList.get(i).getEndDate(), font);
            cell.addElement(paragraph);

            paragraph = new Paragraph(projectList.get(i).getDescription());
            cell.addElement(paragraph);

            table.addCell(cell);
            document.add(table);
        }
    }

    private void addOtherInformation(OtherInformation otherInformation) throws DocumentException {
        Paragraph paragraph;
        PdfPCell cell;
        PdfPTable table;
        boolean firstTime = true;

        for(int i=0; i<otherInformation.getSocialNetworksList().size(); i++){
            table = new PdfPTable(new float[]{2,7});
            table.setWidthPercentage(100);
            table.setSpacingBefore(5);

            //First column
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.RIGHT);
            if(firstTime == true) {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD, Constants.BASE_COLOR_CUSTOM_BLUE_1);
                paragraph = new Paragraph("INFORMACIÓN ADICIONAL", font);
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

            if(firstTime == true){
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD);
                paragraph = new Paragraph(otherInformation.getPersonalWebSite(), font);
                cell.addElement(paragraph);
                table.addCell(cell);
                i--;
                firstTime = false;
            }else {
                Font font = FontFactory.getFont("Calibri", Font.DEFAULTSIZE, Font.BOLD);
                paragraph = new Paragraph(otherInformation.getSocialNetworksList().get(i).getNetwork()
                        + " - " + otherInformation.getSocialNetworksList().get(i).getUser(), font);
                cell.addElement(paragraph);

                table.addCell(cell);
            }
            document.add(table);
        }
    }

}
