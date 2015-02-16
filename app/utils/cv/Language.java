package utils.cv;

/**
 * Created by Victor on 13/02/2015.
 */
public class Language {

    private String language;
    private String certificate;
    private String level;

    public Language(String language, String certificate, String level){
        this.language = language;
        this.certificate = certificate;
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getLevel() {
        return level;
    }
}
