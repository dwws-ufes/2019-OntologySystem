package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;
import javax.ejb.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import br.ufes.informatica.rationalontology.core.exception.MailerException;


/**
 * TODO: document this type.
 *
 * @author Adaptado do professor Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Singleton
public class Mailer implements Serializable {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private static final int PORT_NUMBER_SSL = 465;

	/** TODO: document this field. */
	private static final int PORT_NUMBER_TLS = 587;
	
	/**
	 * TODO: document this method.
	 * 
	 * @param emailAddress
	 * @param subject
	 * @param text
	 * @throws MailerException
	 */
	public static void sendEmail(String addressee, String subject, String text) throws MailerException {
		
		int porToSend = 465;
		
		try {
			if (porToSend == PORT_NUMBER_SSL || porToSend == PORT_NUMBER_TLS) {
				final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					@Override
					public void checkClientTrusted(final java.security.cert.X509Certificate[] certs, final String authType) {}

					@Override
					public void checkServerTrusted(final java.security.cert.X509Certificate[] certs, final String authType) {}

					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				} };
				final SSLContext sc = SSLContext.getInstance(porToSend == PORT_NUMBER_SSL ? "SSL" : "TLS");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				SSLContext.setDefault(sc);
			}

			String smtpServerAddress = "smtp.gmail.com";
			String smtpUserName = "discipoina.dwws@gmail.com";
			String smtpPassword = "tatubola123";

			// Sends the message.
			Email email = new SimpleEmail();
			email.setHostName(smtpServerAddress); //
			email.setSmtpPort(porToSend);
			email.setSSLCheckServerIdentity(false);
			email.setSSLOnConnect(porToSend == PORT_NUMBER_SSL);
			email.setStartTLSEnabled(porToSend == PORT_NUMBER_TLS);
			email.setAuthenticator(new DefaultAuthenticator(smtpUserName, smtpPassword)); //config.getSmtpUsername(), config.getSmtpPassword()
			email.setFrom(smtpUserName); 
			email.setSubject(subject);
			email.setContent(text, "text/html");
			email.addTo(addressee);
			email.send();
		}
		catch (Exception e) {
			throw new MailerException(e);
		}
	}
}
