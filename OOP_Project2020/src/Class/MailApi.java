/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author C50-A
 */
public class MailApi {
    public static void sendmail(String recepient){
        System.out.println("Preparing to send the email..");
        Properties properties = new Properties();
        
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
         properties.put("mail.smtp.port", "587");
         
         final String myAccountEmail="senderMail@gmail.com";
         final String password ="senderMailPassword";
         Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
             
        });
         Message message = prepareMessage(session,myAccountEmail,recepient);
         
        try {
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            Logger.getLogger(MailApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient){
        Message message= new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Repair Job is Done!");
            message.setText("Dear valued customer, \n your repair job is just got finished!. you can now take it \n Thank you.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
