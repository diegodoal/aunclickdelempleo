package models.entities;

import play.data.validation.Constraints.Required;
import play.libs.Json;

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
	public boolean certificateOf33Disability;
	
	@Required()
	public String requirements;
	
	@Required()
	public ContactProfile contact;
	
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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Job.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContract_type() {
        return contract_type;
    }

    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    public String getWorkday() {
        return workday;
    }

    public void setWorkday(String workday) {
        this.workday = workday;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGeneral_terms() {
        return general_terms;
    }

    public void setGeneral_terms(String general_terms) {
        this.general_terms = general_terms;
    }

    public boolean getCertificateOf33Disability() {
        return certificateOf33Disability;
    }

    public void setCertificateOf33Disability(boolean certificateOf33Disability) {
        this.certificateOf33Disability = certificateOf33Disability;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public ContactProfile getContact() {
        return contact;
    }

    public void setContact(ContactProfile contact) {
        this.contact = contact;
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
		this.certificateOf33Disability = certificateOf33Disability;
		if(this.certificateOf33Disability == true){
			this.requirements="Será necesario disponer de un Certificado de Discapacidad de al menos el 33%";
		}
		this.contact = contact;
	}
	
	public String showJobOfferInfo(){
		return Json.toJson(this).toString();
	}
}
