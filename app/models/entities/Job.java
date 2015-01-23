package models.entities;

import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class Job {

	@Required()
	@MinLength(4)
	public String title;
	
	@Required()
	public String sector;
	
	@Required()
	@MinLength(4)
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
	
	public Job(String title, String sector, String description, String date, String location, String contract_type, String workday, double salary) {
		this.title = title;
		this.sector = sector;
		this.description = description;
		this.date = date;
		this.location = location;
		this.contract_type = contract_type;
		this.workday = workday;
		this.salary = salary;
	}
}
