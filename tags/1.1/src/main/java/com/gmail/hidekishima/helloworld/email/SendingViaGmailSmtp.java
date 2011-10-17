package com.gmail.hidekishima.helloworld.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Sending an email via Gmail SMTP
 * Reference: http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 * 
 * @author Hideki Shima
 */
public class SendingViaGmailSmtp {
  
  private static String username = "YOUR_GMAIL_ACCOUNT@gmail.com"; 
  private static String password = "YOUR_GMAIL_PASSWORD";
  private static String sender   = "YOUR_GMAIL_ACCOUNT@gmail.com"; 
  private static String to       = "RECEPIENT_EMAIL_ADDRESS@example.com";
  
  //Below is the Japanese defacto-standard encoding.
  //English speakers: use "ISO-8859-1"
  //Other language speakers: use "UTF-8"
  private static String encoding = "ISO-2022-JP";
  
  public static void main(String[] args) {
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "465");
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.socketFactory.port", "465");
    p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    
    Authenticator a = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
      }
    };
    Session session = Session.getDefaultInstance(p, a);

    try {
//      Store store = session.getStore("pop3");
//      store.connect(pop3Host, userId, password);
      
      MimeMessage msg = new MimeMessage(session);
      msg.setRecipients(Message.RecipientType.TO, to);
      InternetAddress from = new InternetAddress(sender, "YOUR NAME");
      msg.setFrom(from);
      msg.setSubject("THIS IS THE SUBJECT", encoding);
      msg.setText("HELLO WORLD!", encoding);
      Transport.send(msg);
      
//      store.close();
      Thread.sleep(5000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}