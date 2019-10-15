package br.ufes.informatica.rationalontology.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;

@Generated(value="Dali", date="2019-10-08T16:54:48.788-0300")
@StaticMetamodel(SubOntology.class)
public class SubOntology_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<SubOntology, String> name;
	public static volatile SingularAttribute<SubOntology, String> shortDescription;
	public static volatile SingularAttribute<SubOntology, String> fullDescription;
	public static volatile SingularAttribute<SubOntology, Ontology> Source;
	public static volatile SetAttribute<SubOntology, CompetenceQuestion> Target;
}
