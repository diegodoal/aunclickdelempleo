package utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import models.entities.orientation.Skill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 13/04/2015.
 */
public class PresentationLetter {

    private Document document;

    public void createPdf(String path, String name, String surnames, String studyName, String studyLocation, String companyName, String jobName,
                          List<String> attachments, List<String> personalCharacteristics, List<Skill> skills, String email, String phoneNumber)
            throws FileNotFoundException, DocumentException {

        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();

        document.add(new Paragraph("Estimado encargado de Recursos Humanos:"));

        document.add(Chunk.NEWLINE);

        String paragraph = "Mi nombre es " + name + " " + surnames + " y he estudiado " + studyName + " en " + studyLocation + ". Le escribo para solicitar el " +
                "puesto de " + jobName + " en " + companyName + ".";

        if(!attachments.isEmpty()){
            paragraph += " Conforme a lo solicitado, adjunto " + formatAttachments(attachments) + " donde se puede comprobar mi trayectoria.";
        }

        document.add(new Paragraph(paragraph));

        document.add(Chunk.NEWLINE);

        if(personalCharacteristics.size() > 2) {
            paragraph = "Este trabajo me parece una oportunidad muy interesante para crecer profesionalmente, y creo que " +
                    personalCharacteristics.get(0).toLowerCase() + " y " + personalCharacteristics.get(1).toLowerCase() + " me hacen un " +
                    "candidato muy competitivo y adecuado para este puesto.";
        }
        document.add(new Paragraph(paragraph));

        document.add(Chunk.NEWLINE);

        List<String> rankedSkills = selectSkills(skills);
        if(personalCharacteristics.size() != 0 || rankedSkills.size() != 0){
            paragraph = "Los puntos fuertes que poseo para el éxito en este puesto son:";
            paragraph += "\n - ";
            for(int i=0; i<personalCharacteristics.size()-1; i++){
                paragraph += personalCharacteristics.get(i) + ", ";
            }
            paragraph += personalCharacteristics.get(personalCharacteristics.size()-1) + ".";

            paragraph += "\n - ";
            if(rankedSkills.size() >=3){
                paragraph += rankedSkills.get(0) + ", " + rankedSkills.get(1) + ", " + rankedSkills.get(2) + ".";
            }else if(rankedSkills.size() == 2){
                paragraph += rankedSkills.get(0) + ", " + rankedSkills.get(1) + ".";
            }else if(rankedSkills.size() == 1){
                paragraph += rankedSkills.get(0) + ".";
            }
        }

        document.add(new Paragraph(paragraph));

        document.add(Chunk.NEWLINE);

        paragraph = "Pueden contactar en cualquier momento a través de correo electrónico en " + email + " o por teléfono: "+phoneNumber + ".";

        document.add(new Paragraph(paragraph));

        document.add(Chunk.NEWLINE);

        paragraph = "Gracias por su tiempo y su atención. Espero poder hablar pronto con usted acerca de esta oportunidad.";

        document.add(new Paragraph(paragraph));

        document.add(Chunk.NEWLINE);

        paragraph = "Atentamente,\n" + name;

        document.add(new Paragraph(paragraph));

        document.close();
    }


    public String formatAttachments(List<String> attachments){
        if(attachments.size() == 1){
            return attachments.get(0);
        }else if(attachments.size() == 2){
            return attachments.get(0) + " y " + attachments.get(1);
        }else if(attachments.size() == 3){
            return attachments.get(0) + ", " + attachments.get(1) + " y " + attachments.get(2);
        }else if(attachments.size() == 4){
            return attachments.get(0) + ", " + attachments.get(1) + ", " + attachments.get(2) + " y " + attachments.get(3);
        }
        return null;
    }

    public List<String> selectSkills(List<Skill> skills){
        List<String> result = new ArrayList<>();

        for(int i=0; i<skills.size(); i++){
            if(skills.get(i).level.equals("Excelente")){
                result.add(skills.get(i).name);
            }
        }

        if(skills.size() >= 3){
            return result;
        }

        for(int i=0; i<skills.size(); i++){
            if(skills.get(i).level.equals("Bien")){
                result.add(skills.get(i).name);
            }
        }

        if(skills.size() >= 3){
            return result;
        }

        for(int i=0; i<skills.size(); i++){
            if(skills.get(i).level.equals("Normal")){
                result.add(skills.get(i).name);
            }
        }
        return result;
    }
}
