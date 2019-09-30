package br.ufes.informatica.rationalontology.core.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
public class Ontology extends PersistentObjectSupport implements Comparable<Ontology> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@NotNull
	private String name;

	/** TODO: generated by FrameWeb. Should be documented. */

	private String description;

	/** TODO: generated by FrameWeb. Should be documented. */

	private String intendedUse;

	/** TODO: generated by FrameWeb. Should be documented. */

	private String domainDescription;

	/** TODO: generated by FrameWeb. Should be documented. */
	@NotNull
	private String nickname;


	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "Source")
	private Set<SubOntology> TargetSubOntology;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "Source")
	private Set<DataDictionary> TargetDataDictionary;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "Source")
	private Set<Access> TargetAccess;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for description. */
	public String getDescription() {
		return description;
	}

	/** Setter for description. */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Getter for intendedUse. */
	public String getIntendedUse() {
		return intendedUse;
	}

	/** Setter for intendedUse. */
	public void setIntendedUse(String intendedUse) {
		this.intendedUse = intendedUse;
	}

	/** Getter for domainDescription. */
	public String getDomainDescription() {
		return domainDescription;
	}

	/** Setter for domainDescription. */
	public void setDomainDescription(String domainDescription) {
		this.domainDescription = domainDescription;
	}

	/** Getter for nickname. */
	public String getNickname() {
		return nickname;
	}

	/** Setter for nickname. */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/** Getter for Source. */
/*	public Set<Access> getSource() {
		return Source;
	}
*/
	/** Setter for Source. */
/*	public void setSource(Set<Access> Source) {
		this.Source = Source;
	}
*/
	/** Getter for Target. */
	public Set<SubOntology> getTarget() {
		return TargetSubOntology;
	}

	/** Setter for Target. */
	public void setTargetSubOntology(Set<SubOntology> TargetSubOntology) {
		this.TargetSubOntology = TargetSubOntology;
	}

	/** Setter for Target. */
	public void setTargetDataDictionary(Set<DataDictionary> TargetDataDictionary) {
		this.TargetDataDictionary = TargetDataDictionary;
	}
	
	/** Setter for Target. */
	public void setTargetAccess(Set<Access> TargetAccess) {
		this.TargetAccess = TargetAccess;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Ontology o) {
		// FIXME: auto-generated method stub
		return super.compareTo(o);
	}
}