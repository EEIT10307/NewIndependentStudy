package everybikeInfo.robin.dao;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Repository;

@Repository
public class TestEmailDao implements TestEmailIFaceDao{

	@Override
	public int sendemail(String or, String em) {
		String host = "smtp.gmail.com";
		int port = 587;
		final String username = "bookwater168@gmail.com";// 帳號
		final String password = "Handsomeboy6";// 密碼

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
			message.setFrom(new InternetAddress("bookwater168@gmail.com"));// 從上面打的帳號傳
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bookwater168@gmail.com"));
			message.setSubject("感謝您的惠顧");
			message.setText("http://localhost:8080/motorcycleiiieduproject/robinStar.html?email="+em+"&order="+or+"");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("寄送email結束.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}

}
