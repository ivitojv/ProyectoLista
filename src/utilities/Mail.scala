package utilities
import javax.mail._
import javax.mail.internet._
import java.util.Date
import java.util.Properties
import scala.collection.JavaConversions._

class MailAgent(to: String,
                cc: String,
                bcc: String,
                from: String,
                subject: String,
                content: String,
                smtpHost: String)
{
  var message: Message = null

  message = createMessage
  message.setFrom(new InternetAddress(from))
  setToCcBccRecipients

  message.setSentDate(new Date())
  message.setSubject(subject)
  message.setText(content)

  // throws MessagingException
  def sendMessage {
    Transport.send(message)
  }

  def createMessage: Message = {
    val properties = new Properties()
    properties.put("mail.smtp.host", smtpHost)
    val session = Session.getDefaultInstance(properties, null)
    return new MimeMessage(session)
  }

  // throws AddressException, MessagingException
  def setToCcBccRecipients {
    setMessageRecipients(to, Message.RecipientType.TO)
    if (cc != null) {
      setMessageRecipients(cc, Message.RecipientType.CC)
    }
    if (bcc != null) {
      setMessageRecipients(bcc, Message.RecipientType.BCC)
    }
  }

  // throws AddressException, MessagingException
  def setMessageRecipients(recipient: String, recipientType: Message.RecipientType) {
    // had to do the asInstanceOf[...] call here to make scala happy
    val addressArray = buildInternetAddressArray(recipient).asInstanceOf[Array[Address]]
    if ((addressArray != null) && (addressArray.length > 0))
    {
      message.setRecipients(recipientType, addressArray)
    }
  }

  // throws AddressException
  def buildInternetAddressArray(address: String): Array[InternetAddress] = {
    // could test for a null or blank String but I'm letting parse just throw an exception
    return InternetAddress.parse(address)
  }

}
/*
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
           message.setFrom(new InternetAddress("javier.ibanez.soloaga.st@everis.com"));
  
           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress("javier.ibanez.soloaga.st@everis.com"));
  
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
*/