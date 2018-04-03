package utilities
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Mail(val to:String,val from:String,val host:String) {
  val properties:Properties = System.getProperties
  properties.setProperty("mail.smtp.host", host)
  val session:Session = Session.getDefaultInstance(properties)
  
  
  def sendMessage(content:String){
    try {
           // Create a default MimeMessage object.
           val message:MimeMessage = new MimeMessage(session);
  
           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));
  
           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
  
           // Set Subject: header field
           message.setSubject("This is the Subject Line!");
  
           // Now set the actual message
           message.setText(content);
  
           // Send message
           Transport.send(message);
           println("Sent message successfully....");
        } catch {
          case mex :Exception => mex.printStackTrace();
        }
    }
}
