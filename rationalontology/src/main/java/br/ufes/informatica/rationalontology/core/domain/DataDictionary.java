package br.ufes.informatica.rationalontology.core.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
public class DataDictionary extends PersistentObjectSupport implements Comparable<DataDictionary> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@NotNull
	private String concept;

	/** TODO: generated by FrameWeb. Should be documented. */
	@NotNull
	private String definition;

	/** TODO: generated by FrameWeb. Should be documented. */
	@NotNull
	private String source;

	/** TODO: generated by FrameWeb. Should be documented. */
	@ManyToOne
	private Ontology Source;

	/** Getter for concept. */
	public String getConcept() {
		return concept;
	}

	/** Setter for concept. */
	public void setConcept(String concept) {
		this.concept = concept;
	}

	/** Getter for definition. */
	public String getDefinition() {
		return definition;
	}

	/** Setter for definition. */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/** Getter for source. */
	public String getSource() {
		return source;
	}

	/** Setter for source. */
	public void setSource(String source) {
		this.source = source;
	}

	/** Setter for Source. */
	public void setSource(Ontology Source) {
		this.Source = Source;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(DataDictionary o) {
		// FIXME: auto-generated method stub
		return super.compareTo(o);
	}
}