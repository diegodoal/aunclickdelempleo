package utils.cv;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
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
