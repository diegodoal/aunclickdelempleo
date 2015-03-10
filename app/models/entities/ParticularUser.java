package models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.libs.Json;

public class ParticularUser {

    public List<OrientationStep> completedOrientationSteps;

	// Step 1
    public String name;

    public String surnames;

	public String email;

	public String password;

	public String emailVerificationKey;

	// Step 2
	public ParticularUser() {
		
	}
	
	// Para el signUplogin: Añadido por Mikel
	public ParticularUser(String name, String surnames, String email, String password) {
		this.name  = name;
		this.surnames = surnames;
		this.email = email;
		this.password = password;
        this.emailVerificationKey = UUID.randomUUID().toString();
        initializeCompletedOrientationSteps();
	}
	
	public ParticularUser(String name, String email, String password) {
		this.name = name;
        this.email = email;
		this.password = password;
		this.emailVerificationKey = UUID.randomUUID().toString();
        initializeCompletedOrientationSteps();
	}
	
	public String showUserInfo(){
		return Json.toJson(this).toString();
	}

    private void initializeCompletedOrientationSteps(){
        this.completedOrientationSteps = new ArrayList<OrientationStep>();
        this.completedOrientationSteps.add(new OrientationStep("CurrentSituation", false));
        this.completedOrientationSteps.add(new OrientationStep("Skills", false));
        this.completedOrientationSteps.add(new OrientationStep("InterestIdentification", false));
        this.completedOrientationSteps.add(new OrientationStep("Personal", false));
        this.completedOrientationSteps.add(new OrientationStep("Professional", false));
        this.completedOrientationSteps.add(new OrientationStep("Photo", false));
        this.completedOrientationSteps.add(new OrientationStep("Channels", false));
        this.completedOrientationSteps.add(new OrientationStep("LearnTools", false));
        this.completedOrientationSteps.add(new OrientationStep("GetTools", false));
        this.completedOrientationSteps.add(new OrientationStep("TInterview", false));
        this.completedOrientationSteps.add(new OrientationStep("PInterview", false));
        this.completedOrientationSteps.add(new OrientationStep("ActInterview", false));
        this.completedOrientationSteps.add(new OrientationStep("QuestionsInterview", false));
        this.completedOrientationSteps.add(new OrientationStep("Personal", false));
        this.completedOrientationSteps.add(new OrientationStep("DeadLine", false));
        this.completedOrientationSteps.add(new OrientationStep("Travel", false));
        this.completedOrientationSteps.add(new OrientationStep("Specialization", false));
        this.completedOrientationSteps.add(new OrientationStep("BestDeals", false));
        this.completedOrientationSteps.add(new OrientationStep("Level", false));
        this.completedOrientationSteps.add(new OrientationStep("Reputation", false));
    }

    public class OrientationStep{
        public String step;
        public boolean isCompleted;

        public OrientationStep(String step, boolean isCompleted){
            this.step = step;
            this.isCompleted = isCompleted;
        }
    }
}
