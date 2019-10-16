package br.ufes.informatica.rationalontology.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.RelOntologyApp;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.CompetenceQuestion;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;

@Model
public class RelOntologyController extends JSFController {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RelOntologyApp relOntologyApp;	
	
	
	//********************************************************************************
	//********** Variáveis e métodos responsáveis por EXIBIR os dados da Ontologia
	//********************************************************************************
	
	private Ontology ontology;
	
	private long idOntology;
	
	public long getIdOntology() {
		return idOntology;
	}

	public void setIdOntology(long idOntology) {
		this.idOntology = idOntology;
	}

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}
	

	
	
	//********************************************************************************
	//********** Variáveis e métodos responsáveis por EXIBIR os dados das Sub-Ontologia
	//********************************************************************************

	private List<SubOntology> subOntology;
	

	public List<SubOntology> getSubOntology() {
		return subOntology;
	}

	public void setSubOntology(List<SubOntology> subOntology) {
		this.subOntology = subOntology;
	}
	
	//********************************************************************************
	//********** Variáveis e métodos responsáveis por EXIBIR os dados das Sub-Ontologia
	//********************************************************************************
		
	private List<CompetenceQuestion> competenceQuestion;	

	public List<CompetenceQuestion> getCompetenceQuestion() {
		return competenceQuestion;
	}

	public void setCompeenceQuestion(List<CompetenceQuestion> competenceQuestion) {
		this.competenceQuestion = competenceQuestion;
	}
	
	//********************************************************************************
	//********** Variáveis e métodos responsáveis por EXIBIR o dicionário de termos
	//********************************************************************************
		
	private List<DataDictionary> dataDictionary;

	public List<DataDictionary> getDataDictionary() {
		return dataDictionary;
	}

	public void setDataDictionary(List<DataDictionary> dataDictinary) {
		this.dataDictionary = dataDictinary;
	}

	//****************************************************************************
	//********** Métodos de regras de negócio
	//****************************************************************************
	@PostConstruct
    public void init() {
		generateRel( SessionInformation.getInstance().getIdOntology() );
    }
	
	public String generateRel(long id) {
		ontology = relOntologyApp.getOntologyByID(id);
		
		subOntology =  relOntologyApp.getSubOntologyByIdOntology(id);
		
		competenceQuestion = relOntologyApp.getCompetenceQuestionBySubOntology(subOntology);
		
		dataDictionary = relOntologyApp.getDataDictinary(ontology);

		return null;
	}

}
