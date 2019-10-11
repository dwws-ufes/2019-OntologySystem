package br.ufes.informatica.rationalontology.core.application;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;


import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailSMTP {

	/*
	public static void sendEMail() {
		SimpleEmail email = new SimpleEmail();
		
	      try {  
	          email.setDebug(true);  
	          email.setHostName("smtp.gmail.com");
	          email.setSmtpPort(465);
	          email.setAuthentication("guidoni@gmail.com", "");  
	          email.setSSL(true);  
	          email.setTLS(true);
	          email.addTo("guidoni.netflix@gmail.com"); //pode ser qualquer email  
	          email.setFrom("guidoni@gmail.com"); //será passado o email que você fará a autenticação
	          email.setSubject("Enviando email");  
	          email.setMsg("Teste de envio de email");  
	          email.send();  

	          } catch (EmailException e) {  

	          System.out.println(e.getMessage());  

	          } 
	}
	
	*/
	public static void sendEMail() {
		Properties props = System.getProperties(); //new Properties();
	  
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.connectiontimeout", "600");
		 
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() 
	           {
	                 return new PasswordAuthentication("guidoni@gmail.com", "");
	           }
	      });
	 
	    try {
	    
				Message message = new MimeMessage(session);
	
				message.setFrom(new InternetAddress("guidoni@gmail.com"));
				// Remetente
	
				Address[] toUser = InternetAddress.parse("guidoni.netflix@gmail.com"); // //Destinatário(s) ("seuamigo@gmail.com,
																				// seucolega@hotmail.com,
																				// seuparente@yahoo.com.br");
	
				message.setRecipients(Message.RecipientType.TO, toUser);
	
				message.setSubject("Enviando email com JavaMail");// Assunto
	
				message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
	
				Transport.send(message);
	
				System.out.println("Feito!!!");
	 
	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	  }
	  
}