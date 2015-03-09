package models.entities;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Victor on 30/01/2015.
 */
public class User {

    // Step 1
    public String name;
    public String surnames;
    public String email;
    public String password;
    public String emailVerificationKey;

    public List<OrientationStep> completedOrientationSteps;

    public User(){}

    public User(String name,String surnames, String email, String password){
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.emailVerificationKey = UUID.randomUUID().toString();
        initializeCompletedOrientationSteps();
    }


    public String listToStringWithJsonFormat(){
        String result = "[";
        for(int i=0; i<this.completedOrientationSteps.size(); i++){
            result+="{\""+this.completedOrientationSteps.get(i).step.toString()+"\":\""+this.completedOrientationSteps.get(i).isCompleted+"\",";
        }
        result+="]";
        return result;
    }

    private void initializeCompletedOrientationSteps(){
        this.completedOrientationSteps = new ArrayList<OrientationStep>();
        this.completedOrientationSteps.add(new OrientationStep("CurrentSituation", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Skills", "false"));
        this.completedOrientationSteps.add(new OrientationStep("InterestIdentification", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Personal", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Professional", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Photo", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Channels", "false"));
        this.completedOrientationSteps.add(new OrientationStep("LearnTools", "false"));
        this.completedOrientationSteps.add(new OrientationStep("GetTools", "false"));
        this.completedOrientationSteps.add(new OrientationStep("TInterview", "false"));
        this.completedOrientationSteps.add(new OrientationStep("PInterview", "false"));
        this.completedOrientationSteps.add(new OrientationStep("ActInterview", "false"));
        this.completedOrientationSteps.add(new OrientationStep("QuestionsInterview", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Personal", "false"));
        this.completedOrientationSteps.add(new OrientationStep("DeadLine", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Travel", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Specialization", "false"));
        this.completedOrientationSteps.add(new OrientationStep("BestDeals", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Level", "false"));
        this.completedOrientationSteps.add(new OrientationStep("Reputation", "false"));
    }

    public class OrientationStep {
        public String step;
        public String isCompleted;

        public OrientationStep(String step, String isCompleted){
            this.step = step;
            this.isCompleted = isCompleted;
        }
    }
}
