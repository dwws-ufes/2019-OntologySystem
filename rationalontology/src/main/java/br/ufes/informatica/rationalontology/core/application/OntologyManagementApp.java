package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.User;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface OntologyManagementApp extends Serializable {
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public List<Ontology> getOntologiesByUser(User user);

	public void saveNewOntology(Ontology ontology, User user);

	public void saveOntology(Ontology ontology, User user);
	
	public void deleteOntology(Ontology ontology);
	
	public void checkAuthorization();
	
}