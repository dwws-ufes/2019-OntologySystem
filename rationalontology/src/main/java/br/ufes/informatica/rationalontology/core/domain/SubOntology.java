package br.ufes.informatica.rationalontology.core.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
public class SubOntology extends PersistentObjectSupport implements Comparable<SubOntology> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@NotNull
	private String name;

	/** TODO: generated by FrameWeb. Should be documented. */

	private String shortDescription;

	/** TODO: generated by FrameWeb. Should be documented. */

	private String fullDescription;

	/** TODO: generated by FrameWeb. Should be documented. */
	@ManyToOne
	private Ontology Source;

	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "Source")
	private Set<CompetenceQuestion> Target;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for shortDescription. */
	public String getShortDescription() {
		return shortDescription;
	}

	/** Setter for shortDescription. */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/** Getter for fullDescription. */
	public String getFullDescription() {
		return fullDescription;
	}

	/** Setter for fullDescription. */
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	/** Getter for Source. */
	public Ontology getSource() {
		return Source;
	}

	/** Setter for Source. */
	public void setSource(Ontology Source) {
		this.Source = Source;
	}

	/** Getter for Target. */
	public Set<CompetenceQuestion> getTarget() {
		return Target;
	}

	/** Setter for Target. */
	public void setTarget(Set<CompetenceQuestion> Target) {
		this.Target = Target;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(SubOntology o) {
		// FIXME: auto-generated method stub
		return super.compareTo(o);
	}
}