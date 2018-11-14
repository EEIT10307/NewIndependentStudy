package maintenance;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {
	/*maven專案 下載這個jar 檔
	  		<dependency>
			<groupId>javax.mail</groupId>
			<artifactId>javax.mail-api</artifactId>
			<version>1.6.0</version>
		</dependency>*/
	
	public static void main(String[] args) {
		String host = "smtp.gmail.com";
		  int port = 587;
		  final String username = "madohomuhomu@gmail.com";//帳號
		  final String password = "phoenixgod2016";//your password

		  Properties props = new Properties();
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.port", port);
		  Session session = Session.getInstance(props, new Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(username, password);
		   }
		  });

		  try {

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress("madohomuhomu@gmail.com"));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bookwater168@gmail.com"));
		   message.setSubject("ROBIN出BUG抓蟲囉");//
		   message.setText("GGGGGGGGGGGGGGGGGGGGGGGGG");//
		   Transport transport = session.getTransport("smtp");
		   transport.connect(host, port, username, password);

		   Transport.send(message);

		   System.out.println("寄送email結束.");

		  } catch (MessagingException e) {
		   throw new RuntimeException(e);
		  }
	}
	}