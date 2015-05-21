package utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by:
 * Victor Garcia Zarco - victor.gzarco@gmail.com
 * Mikel Garcia Najera - mikel.garcia.najera@gmail.com
 * Carlos Fernandez-Lancha Moreta - carlos.fernandez.lancha@gmail.com
 * Victor Rodriguez Latorre - viypam@gmail.com
 * Stalin Yajamin Quisilema - rimid22021991@gmail.com
 */
public class EmailUtil {
 
    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
 
          msg.setFrom(new InternetAddress("no_reply@gmail.com", "A un click del empleo"));
          msg.setReplyTo(InternetAddress.parse("no_reply@gmail.com", false));
          msg.setSubject(subject, "UTF-8");
          msg.setText(body, "UTF-8");
          msg.setSentDate(new Date());
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          
          Transport.send(msg);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

    public static void emailMaker(String toEmail, String subject, String message){
        final String fromEmail = "aunclickdelempleo@gmail.com"; //requires valid gmail id
        final String password = "webadecco"; // correct password for gmail id
        // Por ejemplo poner toEmail = "useradecco@gmail.com" // can be any email id

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        // Cambiar el mensaje del título del mail y el cuerpo del mensaje

        EmailUtil.sendEmail(session, toEmail, subject, message);
    }
}