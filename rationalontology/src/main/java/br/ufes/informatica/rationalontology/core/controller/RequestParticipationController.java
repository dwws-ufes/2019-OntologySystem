package br.ufes.informatica.rationalontology.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.RequestParticipationApp;
import br.ufes.informatica.rationalontology.core.application.SessionContext;
import br.ufes.informatica.rationalontology.core.domain.Access;
import br.ufes.informatica.rationalontology.core.domain.User;

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


	/** TODO: generated by FrameWeb. Should be documented. */
	//private List ontologies;

	/** TODO: generated by FrameWeb. Should be documented. */
	//private String requestDescription;

	/** TODO: generated by FrameWeb. Should be documented. */
	//private String ontologyNameForSearch;

	/** TODO: generated by FrameWeb. Should be documented. */
	private List<Access> requests;
	
	private Access selectedRequest;
	
	//private HtmlDataTable dataTable;
	
    @PostConstruct
    public void init() {
    	requests = requestParticipationApp.getRequests(SessionContext.getInstance().getUsuarioLogado().getId());
    }
	
	/*
	public RequestParticipationController() {
		super();
		getRequests();
	}
	*/
	
	////public HtmlDataTable getDataTable() {
	//	return dataTable;
	//}

	//public void setDataTable(HtmlDataTable dataTable) {
	//	this.dataTable = dataTable;
	//}
	
	public void setRequests(List<Access> requests) {
		this.requests = requests;
	}
	
	// TODO: generated by FrameWeb. Should be documented.
	public List<Access> getRequests() {
		return requests;
	}

	public Access getSelectedRequest() {
		return selectedRequest;
	}

	public void setSelectedRequest(Access selectedRequest) {
		this.selectedRequest = selectedRequest;
	}
	
	
/*
	// TODO: generated by FrameWeb. Should be documented.
	public List findOntologyByName() {
		// FIXME: auto-generated method stub
		return null;
	}

	// TODO: generated by FrameWeb. Should be documented.
	public String requestParticipation() {
		// FIXME: auto-generated method stub
		return null;
	}

	// TODO: generated by FrameWeb. Should be documented. 
	private void sendEMail() {
		// FIXME: auto-generated method stub
		return;
	}

	// Getter for ontologies. 
	public List getOntologies() {
		return ontologies;
	}

	// Setter for ontologies.
	public void setOntologies(List ontologies) {
		this.ontologies = ontologies;
	}

	// Getter for requestDescription.
	public String getRequestDescription() {
		return requestDescription;
	}

	// Setter for requestDescription.
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	
	// Getter for ontologyName.
	public String getOntologyName() {
		return ontologyName;
	}

	// Setter for ontologyName.
	public void setOntologyName(String ontologyName) {
		this.ontologyName = ontologyName;
	}
*/
}