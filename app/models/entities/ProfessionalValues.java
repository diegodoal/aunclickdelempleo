package models.entities;

import java.util.List;

/**
 * Created by Victor on 05/03/2015.
 */
public class ProfessionalValues {

    public List<ProfessionalValue> professionalValuesList;

    public ProfessionalValues(List<ProfessionalValue> professionalValuesList){
        this.professionalValuesList = professionalValuesList;
    }
    
    public class ProfessionalValue{
        //Valuation must be one of: "Si, No, No se"
        public String name;
        public String valuation;

        public ProfessionalValue(String name, String valuation){
            this.name = name;
            this.valuation = valuation;
        }
    }
}
