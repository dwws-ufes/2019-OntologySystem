package br.ufes.informatica.rationalontology.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.persistence.CompetenceQuestionDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class CompetenceQuestionControllerBean implements CompetenceQuestionController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private CompetenceQuestionDAO competenceQuestionDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public void saveCQ() {
		// FIXME: auto-generated method stub
		return;
	}

}