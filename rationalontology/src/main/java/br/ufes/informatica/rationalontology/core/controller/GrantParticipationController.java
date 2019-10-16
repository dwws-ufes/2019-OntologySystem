package br.ufes.informatica.rationalontology.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.*;
import javax.faces.application.FacesMessage;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.GrantParticipationApp;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.Access;
import br.ufes.informatica.rationalontology.core.exception.SaveException;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
public class GrantParticipationController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private GrantParticipationApp grantParticipation;

	/** TODO: generated by FrameWeb. Should be documented. */
	private List<Access> requests;
	
	private Access selectedRequest;

	/** TODO: generated by FrameWeb. Should be documented. */
	public String grantParticipation() {
		if(selectedRequest == null) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "grantParticipation.notSelectedOntology");
			return "core/grantParticipation/grantParticipationPage.xhtml?faces-redirect=true";
		}
		
	  	try {
	  		grantParticipation.grantParticipation(selectedRequest);
	  		addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successUpdate");
	  	}catch(SaveException e) {
	  		addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "jbutler.message.failureUpdate", e.toString());
	  	}
		return "core/grantParticipation/grantParticipationPage.xhtml?faces-redirect=true";
	}
	
	public String declineParticipation() {
		if(selectedRequest == null) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "grantParticipation.notSelectedOntology");
			return "core/grantParticipation/grantParticipationPage.xhtml?faces-redirect=true";
		}
		
		try {
			grantParticipation.declineParticipation(selectedRequest);
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successUpdate");
		}
		catch(SaveException e) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "jbutler.message.failureUpdate", e.toString());
		}
		return "core/grantParticipation/grantParticipationPage.xhtml?faces-redirect=true";
	}
	

	public Access getSelectedRequest() {
		return selectedRequest;
	}

	public void setSelectedRequest(Access selectedRequest) {
		this.selectedRequest = selectedRequest;
	}

	/** Getter for requests. */
	public List<Access> getRequests() {
		return requests;
	}

	/** Setter for requests. */
	public void setRequests(List<Access> requests) {
		this.requests = requests;
	}
	
	@PostConstruct
    public void init() {
    	requests = grantParticipation.getRequests(SessionInformation.getInstance().getUsuarioLogado().getId());
    }
}