package br.ufes.informatica.rationalontology.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.SubOntologyApp;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;

/** TODO: generated by FrameWeb. Should be documented. */
@Named @SessionScoped
public class SubOntologyController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private SubOntologyApp subOntologyApp;

	/** TODO: generated by FrameWeb. Should be documented. */
	private SubOntology selectedSubOntology;
	
	private List<SubOntology> subOntologies;
	
	private boolean newSubOntology = false;
	
	private Ontology ontology;
	
	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}

	public SubOntology getSelectedSubOntology() {
		return selectedSubOntology;
	}

	public void setSelectedSubOntology(SubOntology selectedSubOntology) {
		this.selectedSubOntology = selectedSubOntology;
	}
	
	public List<SubOntology> getSubOntologies() {
		putSubOntologies();
		return subOntologies;
	}

	public void setSubOntologies(List<SubOntology> subOntologies) {
		this.subOntologies = subOntologies;
	}

	//*******************************************
	//*** Métodos de negócio
	//*******************************************
	public void onLoadForm() {
		putSubOntologies();
	}
	
	private void putSubOntologies() {
		ontology = SessionInformation.getInstance().getOntology();
		subOntologies = subOntologyApp.getSubOntologiesByOntology( ontology );
	}
	
		
	/** TODO: generated by FrameWeb. Should be documented. */
	public void saveSubOntology() {
		if (newSubOntology) {
			selectedSubOntology.setSource(ontology);
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successInsert");
		}
		else {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successUpdate");
		}
		
		try {
			subOntologyApp.saveSubOntology(selectedSubOntology);
			putSubOntologies();
			newSubOntology = false;
		} catch (Exception e) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_FATAL, "subOntologyDevelopment.error.summary", "ontologyDevelpment.error.detail");
		}
		
	}
	
	public String newSubOntology() {
		selectedSubOntology = new SubOntology();
		newSubOntology = true;
		return "/core/ontology/subOntologyDevelopmentPage.xhtml?faces-redirect=true";
	}
	
	public String editSubOntology() {
		newSubOntology = false;
		if(selectedSubOntology == null) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "subOntologyProject.noSelected");
			return null;
		}
		return "/core/ontology/subOntologyDevelopmentPage.xhtml?faces-redirect=true";
	}
	
	public String showCompetenceQuestion() {
		if(selectedSubOntology == null) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "subOntologyProject.noSelected");
			return null;
		}

		SessionInformation.getInstance().setSubOntology(selectedSubOntology);
		return "/core/ontology/competencyQuestionSelectPage.xhtml?faces-redirect=true";
	}
	
	public void checkAuthorization() {
		subOntologyApp.checkAuthorization();
	}

}