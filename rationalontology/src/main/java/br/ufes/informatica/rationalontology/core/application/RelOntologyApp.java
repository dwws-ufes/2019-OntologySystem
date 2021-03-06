package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.informatica.rationalontology.core.domain.CompetencyQuestion;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;
import br.ufes.informatica.rationalontology.core.domain.User;

@Local
public interface RelOntologyApp extends Serializable {
	
	public Ontology getOntologyByID(long id);
	
	public List<CompetencyQuestion> getCompetenceQuestionBySubOntologies(List<SubOntology> list);
	
	public List<DataDictionary> getDataDictinary(Ontology ontology);
	
	public List<Ontology> getOntologiesByUser(User user);

	public List<SubOntology> getSubOntologyByOntology(Ontology ontology);
	
	public void checkAuthorization();

}
