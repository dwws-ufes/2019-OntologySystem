package br.ufes.informatica.rationalontology.core.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.*;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.OntologyManagementApp;
import br.ufes.informatica.rationalontology.core.application.SessionContext;
import br.ufes.informatica.rationalontology.core.domain.Ontology;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
public class OntologyController extends JSFController { // implements OntologyJPA
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private OntologyManagementApp ontologyManagementApp;
	

	
	/** TODO: generated by FrameWeb. Should be documented. */
	private List ontologies;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private Ontology ontology;
	
	private long meuId = 1;


	
	public long getMeuId() {
		return meuId;
	}

	public void setMeuId(long meuId) {
		this.meuId = meuId;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	public List findUserOntologies() {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public void generatePDF() {
		
	}
	
	public String generateRel() {
		//getFacesContext().getExternalContext().getFlash().put("id", 2);
		SessionContext.getInstance().setIdOntology(2);
	    		
		return "/core/relOntologyPage.xhtml?faces-redirect=true";
	}
	
	
	/** Getter for ontologies. */
	public List getOntologies() {
		return ontologies;
	}

	/** Setter for ontologies. */
	public void setOntologies(List ontologies) {
		this.ontologies = ontologies;
	}
	
	/** Getter for ontology. */
	public Ontology getOntology() {
		return ontology;
	}

	/** Setter for ontology. */
	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}
	
}