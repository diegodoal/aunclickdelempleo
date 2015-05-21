package utils;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
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
public final class FetchMail extends TimerTask {

	/* Construct and use a TimerTask and Timer */
    public static void main (String[] args) {
        TimerTask fetchMail = new FetchMail();
        // Perform the task once a day at 4 a.m., starting tomorrow morning (other styles are possible as well)
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(fetchMail, getTomorrowMorning4am(), fONCE_PER_DAY);
    }

    /* Implements TimerTask's abstract run method */
    @Override
    public void run( ){
    	final String toEmail = "useradecco@gmail.com";
        final String fromEmail = "aunclickdelempleo@gmail.com"; //requires valid gmail id
        final String password = "webadecco"; // correct password for gmail id
        // Por ejemplo poner toEmail = "useradecco@gmail.com" // can be any email id

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //create Authenticator object to pass in Session.getInstance argument
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
    	});

        try {	 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Confirmación de cuenta creada en www.aunclickdelempleo.com");
			message.setText("Su cuenta " + toEmail + " se ha creado con éxito");
 
			Transport.send(message);
 
			System.out.println("Se ha enviado un email a: " + toEmail);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }

    // Expressed in milliseconds
    private final static long fONCE_PER_DAY = 1000*60*60*24;

    private final static int fONE_DAY = 0;		// Dias que va a tardar en mandarse el aviso
    private final static int fFOUR_AM = 12;		// Hora a la que se mandará el aviso
    private final static int fZERO_MINUTES = 56;	// Minutos a la que se mandará el aviso

    private static Date getTomorrowMorning4am(){
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DATE, fONE_DAY);
		Calendar result = new GregorianCalendar(
			tomorrow.get(Calendar.YEAR),
			tomorrow.get(Calendar.MONTH),
			tomorrow.get(Calendar.DATE),
			fFOUR_AM,
			fZERO_MINUTES);
		
    return result.getTime();
  }
}