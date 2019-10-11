package br.ufes.informatica.rationalontology.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import org.primefaces.event.SelectEvent;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.RequestParticipationApp;
import br.ufes.informatica.rationalontology.core.application.SessionContext;
import br.ufes.informatica.rationalontology.core.domain.Access;
import br.ufes.informatica.rationalontology.core.domain.Ontology;

@Model
public class NewRequestParticipationController extends JSFController implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private RequestParticipationApp requestParticipationApp;
	
	private List ontologies;

	private String ontologyName;
	
	private Ontology selectedOntology;
	
	private Access newAccess;
	
	public Access getNewAccess() {
		return newAccess;
	}

	public void setNewAccess(Access newAccess) {
		this.newAccess = newAccess;
	}

	public Ontology getSelectedOntology() {
		return selectedOntology;
	}

	public void setSelectedOntology(Ontology selectedOntology) {
		this.selectedOntology = selectedOntology;
	}

	public String getOntologyName() {
		return ontologyName;
	}

	public void setOntologyName(String ontologyName) {
		this.ontologyName = ontologyName;
	}
	
	public List getOntologies() {
		return ontologies;
	}

	public void setOntologies(List ontologies) {
		this.ontologies = ontologies;
	}
	
	public void findOntologyByName() {
		setOntologies( requestParticipationApp.findOntologyByName(ontologyName));
	}
	
	public void onRowSelect(SelectEvent event) {
		selectedOntology = (Ontology) event.getObject();
	}
	
	public void save() {
		System.out.print("foi....................");
		if(newAccess == null) {
			System.out.print("NULLLLLLLLLL0");
		}else {
		System.out.println("id: "+ newAccess.getId());
		System.out.println("uuid: " + newAccess.getUuid());
		System.out.println("version: " + newAccess.getVersion());
		System.out.println("usuario: " + newAccess.getSource().getName());
		System.out.println("ontologia: " + newAccess.getTarget().getName());
		System.out.println("texto: "+ newAccess.getAccessTypeText());
		}
	}
	
	public void teste() {
		System.out.print("foi....................");
	}
	
}