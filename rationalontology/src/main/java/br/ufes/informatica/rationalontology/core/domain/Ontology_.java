package br.ufes.informatica.rationalontology.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-10-08T16:54:48.782-0300")
@StaticMetamodel(Ontology.class)
public class Ontology_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Ontology, String> name;
	public static volatile SingularAttribute<Ontology, String> description;
	public static volatile SingularAttribute<Ontology, String> intendedUse;
	public static volatile SingularAttribute<Ontology, String> domainDescription;
	public static volatile SingularAttribute<Ontology, String> nickname;
	public static volatile SetAttribute<Ontology, Access> Source;
	public static volatile SetAttribute<Ontology, SubOntology> TargetSubOntology;
	public static volatile SetAttribute<Ontology, DataDictionary> TargetDataDictionary;
}

/*
package br.ufes.informatica.rationalontology.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@Generated(value="Dali", date="2019-10-08T16:54:48.782-0300")
@StaticMetamodel(Ontology.class)
public class Ontology_ {
	public static volatile SingularAttribute<Ontology, Long> id;
	public static volatile SingularAttribute<Ontology, Long> version;
	public static volatile SingularAttribute<Ontology, String> name;
	public static volatile SingularAttribute<Ontology, String> description;
	public static volatile SingularAttribute<Ontology, String> intendedUse;
	public static volatile SingularAttribute<Ontology, String> domainDescription;
	public static volatile SingularAttribute<Ontology, String> nickname;
	public static volatile SetAttribute<Ontology, Access> Source;
	public static volatile SetAttribute<Ontology, SubOntology> TargetSubOntology;
	public static volatile SetAttribute<Ontology, DataDictionary> TargetDataDictionary;
}
*/