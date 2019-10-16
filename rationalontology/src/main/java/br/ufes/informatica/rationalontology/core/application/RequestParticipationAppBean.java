package br.ufes.informatica.rationalontology.core.application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.domain.Access;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.RegistrationFailedException;
import br.ufes.informatica.rationalontology.core.persistence.AccessDAO;
import br.ufes.informatica.rationalontology.core.persistence.OntologyDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class RequestParticipationAppBean implements RequestParticipationApp {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private AccessDAO accessDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private OntologyDAO ontologyDAO;
	
	@Override
	public void cancellParticipation(Access access) throws RegistrationFailedException{
		save(access);
	}
	public void saveNewParticipation(Access access) throws RegistrationFailedException{
		save(access);
		EMail.sendEMail();
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Ontology> findOntologyByName(String name) {
		return ontologyDAO.findOntologyByName(name);		
	}
	
	@Override
	public List<Ontology> findOntologyByNameNotMine(String name) {
		User user = SessionContext.getInstance().getUsuarioLogado();
		return ontologyDAO.findOntologyByNameNotMine(name, user);		
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Access> getRequests(long id) {
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