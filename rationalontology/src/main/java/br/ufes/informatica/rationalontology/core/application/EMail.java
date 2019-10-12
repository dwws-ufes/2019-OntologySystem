package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;

import javax.ejb.Singleton;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;


@Singleton
public class EMail implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final int PORT_NUMBER_SSL = 465;

	private static final int PORT_NUMBER_TLS = 587;
	
	public static void sendEMail()  {
		try {
			
			int port = 465;
			String hostName = "smtp.gmail.com";
			String user = "discipoina.dwws@gmail.com";
			String snh = "tatubola123";
			String emailAddress = "guidoni.netflix@gmail.com";

			Email email = new SimpleEmail();

			email.setHostName( hostName );
			email.setSmtpPort(port);
			email.setSSLCheckServerIdentity(false);
			email.setSSLOnConnect(port == PORT_NUMBER_SSL);
			email.setStartTLSEnabled(port == PORT_NUMBER_TLS);

			//email.setAuthenticator(new DefaultAuthenticator(user, snh));
			email.setAuthentication(user, snh);
			email.setFrom(user);
			email.setSubject("Teste");
			email.setMsg("Isso é um teste");
			email.addTo(emailAddress);
			
			email.send();

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}