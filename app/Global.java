import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings{
    @Override
    public void onStart(Application application) {
        Logger.info("Hola gente");
        super.onStart(application);
    }
}