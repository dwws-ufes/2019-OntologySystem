package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.User;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface OntologyController extends Serializable {

	/** TODO: generated by FrameWeb. Should be documented. */
	public List findUserOntologies(User user);

	/** TODO: generated by FrameWeb. Should be documented. */
	public String generatePDF(Ontology onto);

}