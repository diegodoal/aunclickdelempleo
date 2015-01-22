package models;

import java.util.List;

public class PrivateUser extends User{
	private String name;
	private String surname;
	
	//Personal information
	private String birth_date;
	private String province;
	private boolean hasDisability;
	private String typeOfDisability;
	private String levelOfDisability;
	private String studyLevel;
	private List<WorkExperience> workExperience;
	private String[] professionalInterests;
	
	//Atachments
	
	public class WorkExperience{
		private String sector;
		private String job;
		private String company;
		private String startDate;
		private String endDate;
		
		public WorkExperience(String sector, String job, String company, String startDate, String endDate){
			this.sector = sector;
			this.job = job;
			this.company = company;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
	public PrivateUser(String name, String surname, String email, String password, String repeat_password) {
		super(email, password, repeat_password);
		if(validateName(name) == false)
			return;
		this.name = name;
		if(validateSurname(surname) == false){
			return;
		}
		this.surname = surname;
		this.setCurrent_status(Status.ONLY_LOGIN);
	}
	
	public void addWorkExperience(WorkExperience workExp){
		this.workExperience.add(workExp);
	}
	
	private boolean validateName(String name){
		if(name.trim().equals(null))
			return false;
		return true;
	}
	
	private boolean validateSurname(String name){
		if(surname.trim().equals(null))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public boolean isHasDisability() {
		return hasDisability;
	}
	public void setHasDisability(boolean hasDisability) {
		this.hasDisability = hasDisability;
	}
	public String getTypeOfDisability() {
		return typeOfDisability;
	}
	public void setTypeOfDisability(String typeOfDisability) {
		this.typeOfDisability = typeOfDisability;
	}
	public String getLevelOfDisability() {
		return levelOfDisability;
	}
	public void setLevelOfDisability(String levelOfDisability) {
		this.levelOfDisability = levelOfDisability;
	}
	public String getStudyLevel() {
		return studyLevel;
	}
	public void setStudyLevel(String studyLevel) {
		this.studyLevel = studyLevel;
	}
	public List<WorkExperience> getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(List<WorkExperience> workExperience) {
		this.workExperience = workExperience;
	}
	public String[] getProfessionalInterests() {
		return professionalInterests;
	}
	public void setProfessionalInterests(String[] professionalInterests) {
		this.professionalInterests = professionalInterests;
	}
}
