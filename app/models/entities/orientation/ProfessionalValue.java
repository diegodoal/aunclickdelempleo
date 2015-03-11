package models.entities.orientation;

/**
 * Created by Victor on 11/03/2015.
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
