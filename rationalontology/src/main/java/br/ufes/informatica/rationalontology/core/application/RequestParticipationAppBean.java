package br.ufes.informatica.rationalontology.core.application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.domain.Access;
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

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List findOntologyByName(String name) {
		// FIXME: auto-generated method stub
		return null;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public String requestParticipation(Access access) {
		// FIXME: auto-generated method stub
		return null;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List getRequests(long id) {
		return accessDAO.getRequests(id);
	}

}