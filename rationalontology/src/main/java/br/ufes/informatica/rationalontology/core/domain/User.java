package br.ufes.informatica.rationalontology.core.domain;

import java.util.*;
import java.math.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
public class User extends PersistentObjectSupport implements Comparable<User> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 100)
	private String name;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 50)
	private String email;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 100)
	private String institution;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 50)
	private String password;

	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "Source")
	private Set<Access> Target;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for email. */
	public String getEmail() {
		return email;
	}

	/** Setter for email. */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Getter for institution. */
	public String getInstitution() {
		return institution;
	}

	/** Setter for institution. */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/** Getter for password. */
	public String getPassword() {
		return password;
	}

	/** Setter for password. */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Getter for Target. */
	public Set<Access> getTarget() {
		return Target;
	}

	/** Setter for Target. */
	public void setTarget(Set<Access> Target) {
		this.Target = Target;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(User o) {
		// FIXME: auto-generated method stub
		return super.compareTo(o);
	}
}