package br.ufes.informatica.rationalontology.core.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.informatica.rationalontology.core.application.email.Mailer;
import br.ufes.informatica.rationalontology.core.application.email.MailerTemplate;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.Access;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.MailerException;
import br.ufes.informatica.rationalontology.core.exception.RegistrationFailedException;
import br.ufes.informatica.rationalontology.core.persistence.AccessDAO;
import br.ufes.informatica.rationalontology.core.persistence.OntologyDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
@RolesAllowed("SysAdmin")
public class RequestParticipationAppBean implements RequestParticipationApp {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private AccessDAO accessDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private OntologyDAO ontologyDAO;
	
	//@Inject
	//private Event<MailEvent> mailEvent;
	
	@Inject
	private Mailer mail;
	
	@Override
	public void cancellParticipation(Access access) throws RegistrationFailedException{
		save(access);
	}
	public void saveNewParticipation(Access access) throws RegistrationFailedException{
		save(access);
		//EMail.sendEMail();
		//send();
	}
	
	private void send() {
		// Creates the data model with the information needed to send an e-mail with the reset code.
		Map<String, Object> dataModel = new HashMap<>();

		// Then, fire an e-mail event so the e-mail gets sent.
		try {
			mail.sendEmail("guidoni@gmai.com", MailerTemplate.RESET_PASSWORD, dataModel);
			System.out.println("ENVIOU");
		}
		catch(MailerException e) {
			System.out.println(e.getStackTrace());
		}
	}
	

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Ontology> findOntologyByName(String name) {
		return ontologyDAO.findOntologyByName(name);		
	}
	
	@Override
	public List<Ontology> findOntologyByNameNotMine(String name) {
		User user = SessionInformation.getInstance().getUsuarioLogado();
		return ontologyDAO.findOntologyByNameNotMine(name, user);		
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Access> getRequests() {
		long id = SessionInformation.getInstance().getUsuarioLogado().getId();
		return accessDAO.getRequests(id);
	}
	
	private void save(Access access) throws RegistrationFailedException {
		try {
			accessDAO.save(access);
		}
		catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			throw new RegistrationFailedException(e);
		}
	}

}