package br.ufes.informatica.rationalontology.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.*;
import javax.faces.application.FacesMessage;

import org.primefaces.event.SelectEvent;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.TypeOfAccess;
import br.ufes.informatica.rationalontology.core.application.RequestParticipationApp;
import br.ufes.informatica.rationalontology.core.application.SessionContext;
import br.ufes.informatica.rationalontology.core.domain.Access;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.exception.RegistrationFailedException;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
//@ManagedBean(name = "requestParticipationController")
//@ViewScoped
public class RequestParticipationController extends JSFController implements Serializable{
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private RequestParticipationApp requestParticipationApp;
	
	//****************************************************************************
	//********** Variáveis responsáveis por EXIBIR as requisições de participação
	//********* Utilizadas na interface requestParticipatinPage
	//****************************************************************************
	private List<Access> requests;
	
	private Access selectedRequest;
	
	//********************************************************************
	//*** Variáveis responsáveis por CADASTRAR uma nova requisição
	//*** Utilizadas na interface newRequestParticipationPage
	//********************************************************************
	private List<Ontology> ontologies;

	private String ontologyName;
	
	private Ontology selectedOntology;
	
	private String email_text;
	
    
    //***********************************************************************
    //**  Métodos de acesso as variáveis da página RequstPartipationPage  ***
    //************************************************************************
	public void setRequests(List<Access> requests) {
		this.requests = requests;
	}
	
	public List<Access> getRequests() {
		return requests;
	}

	public Access getSelectedRequest() {
		return selectedRequest;
	}

	public void setSelectedRequest(Access selectedRequest) {
		this.selectedRequest = selectedRequest;
	}
	
	//**************************************************************************
    //**  Métodos de acesso as variáveis da página NewRequstPartipationPage  ***
    //***************************************************************************
	
	public List<Ontology> getOntologies() {
		return ontologies;
	}

	public void setOntologies(List<Ontology> ontologies) {
		this.ontologies = ontologies;
	}
	
	public String getOntologyName() {
		return ontologyName;
	}

	public void setOntologyName(String ontologyName) {
		this.ontologyName = ontologyName;
	}
	
	public Ontology getSelectedOntology() {
		return selectedOntology;
	}

	public void setSelectedOntology(Ontology selectedOntology) {
		this.selectedOntology = selectedOntology;
	}
	
	public String getEmail_text() {
		return email_text;
	}

	public void setEmail_text(String email_text) {
		this.email_text = email_text;
	}
	
	//**********************
    //**  Métodos gerais ***
    //**********************
	
	@PostConstruct
    public void init() {
    	requests = requestParticipationApp.getRequests(SessionContext.getInstance().getUsuarioLogado().getId());
    	ontologies = requestParticipationApp.findOntologyByNameNotMine("");
    }

	public void cancelParticipation() {
		try {
			selectedRequest.setAccessType( TypeOfAccess.CANCELADO );
			requestParticipationApp.cancellParticipation(selectedRequest);
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successUpdate");
		}
		catch (RegistrationFailedException e) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_FATAL, "requestParticipation.error.summary", "requestParticipation.error.detail");
		}
	}
	
	public void findOntologyByName() {
		setOntologies( requestParticipationApp.findOntologyByName(ontologyName));
	}
	
	public String save() {		
		Access newAccess = new Access();
		newAccess.setAccessType(TypeOfAccess.SOLICITANTE);
		newAccess.setEmail_text(email_text);
		newAccess.setSource(SessionContext.getInstance().getUsuarioLogado());
		newAccess.setTarget(selectedOntology);
		
		try {
			requestParticipationApp.saveNewParticipation(newAccess);
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successInsert");
		} catch (RegistrationFailedException e) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_FATAL, "requestParticipation.error.summary", "requestParticipation.error.detail");
		}		
		
		return "requestParticipationPage.xhtml?faces-redirect=true";
	}

}