package models.entities.orientation;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class ProfessionalValue {
    //Valuation must be one of: "Si, No, No se"
    public String name;
    public String valuation;

    public ProfessionalValue(String name, String valuation){
        this.name = name;
        this.valuation = valuation;
    }
}
