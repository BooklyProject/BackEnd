package mat.unical.it.bookly.persistance;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Mail {

    Session newSession = null;
    MimeMessage mimeMessage = null;
    String fromUser = "booklyproject2023@gmail.com";

    public void sendEmail() throws MessagingException {
        String fromUserPassword = "ucgrmipahbyaieah";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }

    public MimeMessage draftEmail(String c,String dest) throws MessagingException, UnsupportedEncodingException {
        String [] emailReceipients = {dest};
        String emailSubject = "Recupero credenziali password";
        String codice = c;
        String emailBody = "Ciao,<br><br> Ecco il tuo codice di recupero per la password: <b>" + codice + "</b><br><br>Grazie.<br><br>Bookly";
        mimeMessage = new MimeMessage(newSession);

        for(int i = 0; i < emailReceipients.length; i++){

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setSubject(emailSubject);
        mimeMessage.setFrom(new InternetAddress(fromUser,"Bookly"));

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        mimeMessage.setContent(multipart);
        return mimeMessage;

    }

    public void setupMailServerProperties(){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        newSession = Session.getDefaultInstance(properties, null);
    }

}
