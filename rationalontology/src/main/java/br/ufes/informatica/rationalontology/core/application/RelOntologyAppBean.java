package br.ufes.informatica.rationalontology.core.application;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.domain.CompetencyQuestion;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.persistence.CompetencyQuestionDAO;
import br.ufes.informatica.rationalontology.core.persistence.DataDictionaryDAO;
import br.ufes.informatica.rationalontology.core.persistence.OntologyDAO;
import br.ufes.informatica.rationalontology.core.persistence.SubOntologyDAO;

@Stateless
@RolesAllowed("SysAdmin")
public class RelOntologyAppBean implements RelOntologyApp{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private OntologyDAO ontologyDAO;
	
	@EJB
	private SubOntologyDAO subOntologyDAO;
	
	@EJB
	private CompetencyQuestionDAO comptetenceQuestionDAO;
	
	@EJB
	private DataDictionaryDAO dataDictionaryDAO;

	@Override
	public Ontology getOntologyByID(long id){
		return  ontologyDAO.findOntologyByID(id);
	}
	
	@Override
	public List<SubOntology> getSubOntologyByOntology(Ontology ontology) {
		return subOntologyDAO.getSubOntologyByOntology(ontology);
	}
	
	@Override
	public List<CompetencyQuestion> getCompetenceQuestionBySubOntologies(List<SubOntology> list){
		return comptetenceQuestionDAO.getCompetenceQuestionBySubOntologies(list);
	}

	@Override
	public List<DataDictionary> getDataDictinary(Ontology ontology) {
		return dataDictionaryDAO.getDataDictionary(ontology);
	}

	@Override
	public List<Ontology> getOntologiesByUser(User user) {
		return ontologyDAO.getOntologiesByUser(user);
	}

	@Override
	public void checkAuthorization() {
		System.out.println("checkAuthorization");
	}
}
