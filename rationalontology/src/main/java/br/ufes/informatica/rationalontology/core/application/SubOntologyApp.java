package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface SubOntologyApp extends Serializable {
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public void saveSubOntology(SubOntology subOnt);
	
	public List<SubOntology> getSubOntologiesByOntology(Ontology ontology);
	
	public void checkAuthorization();
}