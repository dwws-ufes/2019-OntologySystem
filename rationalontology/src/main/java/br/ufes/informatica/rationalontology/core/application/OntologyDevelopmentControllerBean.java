package br.ufes.informatica.rationalontology.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.persistence.OntologyDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class OntologyDevelopmentControllerBean implements OntologyDevelopmentController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private OntologyDAO ontologyDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public String saveOntology(Ontology onto) {
		// FIXME: auto-generated method stub
		return null;
	}

}