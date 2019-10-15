package br.ufes.informatica.rationalontology.core.application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.domain.CompetenceQuestion;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;
import br.ufes.informatica.rationalontology.core.persistence.CompetenceQuestionDAO;
import br.ufes.informatica.rationalontology.core.persistence.DataDictionaryDAO;
import br.ufes.informatica.rationalontology.core.persistence.OntologyDAO;
import br.ufes.informatica.rationalontology.core.persistence.SubOntologyDAO;

@Stateless
public class RelOntologyAppBean implements RelOntologyApp{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private OntologyDAO ontologyDAO;
	
	@EJB
	private SubOntologyDAO subOntologyDAO;
	
	@EJB
	private CompetenceQuestionDAO comptetenceQuestionDAO;
	
	@EJB
	private DataDictionaryDAO dataDictionaryDAO;

	@Override
	public Ontology getOntologyByID(long id){
		return  ontologyDAO.findOntologyByID(id);
	}
	
	@Override
	public List<SubOntology> getSubOntologyByIdOntology(long id) {
		return subOntologyDAO.getSubOntologyByIdOntology(id);
	}
	
	@Override
	public List<CompetenceQuestion> getCompetenceQuestionBySubOntology(List<SubOntology> list){
		return comptetenceQuestionDAO.getCompetenceQuestionBySubOntology(list);
	}

	@Override
	public List<DataDictionary> getDataDictinary(Ontology ontology) {
		return dataDictionaryDAO.getDataDictionary(ontology);
	}
}
