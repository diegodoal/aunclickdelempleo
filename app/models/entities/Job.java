package models.entities;

import play.data.validation.Constraints.Required;

public class Job {
	
	public static int id = 0;
	
	@Required()
	public String title;
	
	@Required()
	public String sector;
	
	@Required()
	public String description;
	
	@Required()
	public String date;
	
	@Required()
	public String location;
	
	@Required()
	public String contract_type;
	
	@Required()
	public String workday;
	
	@Required()
	public double salary;
	
	@Required()
	public String general_terms;
	
	@Required()
	public String requirements;
	
	@Required()
	public ContactProfile contact;
	
	@Required()
	public boolean certificateOf33Disability;
	
	public static class ContactProfile{
		@Required()
		public String name;
		
		@Required()
		public String email;
		
		@Required()
		public int phone_number;
		
		public ContactProfile(String name, String email, int phone_number){
			this.name = name;
			this.email = email;
			this.phone_number = phone_number;
		}
		
		public String toString(){
            return "{\"name\":\""+this.name+"\",\"email\":\""+this.email+"\",\"phone_number\":\""+this.phone_number+"\"}";
        }
		
	}
	
	public Job() {}
	
	public Job(String title, String sector, String description, String date, String location, 
			String contract_type, String workday, double salary, String general_terms, 
			ContactProfile contact, boolean certificateOf33Disability) {
		this.id = id+1;
		this.title = title;
		this.sector = sector;
		this.description = description;
		this.date = date;
		this.location = location;
		this.contract_type = contract_type;
		this.workday = workday;
		this.salary = salary;
		this.general_terms = general_terms;
		if(certificateOf33Disability){
			this.requirements="Será necesario disponer de un Certificado de Discapacidad de al menos el 33%";
		}
		this.contact = contact;
		this.certificateOf33Disability = certificateOf33Disability;
	}
}
