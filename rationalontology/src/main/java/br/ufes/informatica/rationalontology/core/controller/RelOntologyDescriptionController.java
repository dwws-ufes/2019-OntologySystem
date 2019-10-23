package br.ufes.informatica.rationalontology.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.RelOntologyApp;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.CompetencyQuestion;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;

@Named @SessionScoped
public class RelOntologyDescriptionController extends JSFController  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RelOntologyApp relOntologyApp;	
	
	//********************************************************************************
	//********** Variáveis e métodos para EXIBIR as ontologias
	//********************************************************************************
	private List<Ontology> ontologies;
	
	private Ontology selectedOntology;
	
	public List<Ontology> getOntologies() {
		return ontologies;
	}

	public void setOntologies(List<Ontology> ontologies) {
		this.ontologies = ontologies;
	}

	public Ontology getSelectedOntology() {
		return selectedOntology;
	}

	public void setSelectedOntology(Ontology selectedOntology) {
		this.selectedOntology = selectedOntology;
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
		
	private List<CompetencyQuestion> competenceQuestion;	

	public List<CompetencyQuestion> getCompetenceQuestion() {
		return competenceQuestion;
	}

	public void setCompeenceQuestion(List<CompetencyQuestion> competenceQuestion) {
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
	
    public void putOntologies() {
		ontologies = relOntologyApp.getOntologiesByUser(SessionInformation.getInstance().getUsuarioLogado());
    }
    
	public void onLoadForm() {
		putOntologies();
	}
	
	public String generateRel() {
		if(selectedOntology == null) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "requestParticipation.notSelectedAccess");
			return "core/reports/requestParticipation/relOntologyDescriptionSelectPage.xhtml?faces-redirect=true";
		}
		
		subOntology =  relOntologyApp.getSubOntologyByOntology(selectedOntology);
		
		competenceQuestion = relOntologyApp.getCompetenceQuestionBySubOntologies(subOntology);
		
		dataDictionary = relOntologyApp.getDataDictinary(selectedOntology);
		
		return "relOntologyDescriptionPage.xhtml?faces-redirect=true ";
	}
	
	public void checkAuthorization() {
		relOntologyApp.checkAuthorization();
	}
}
