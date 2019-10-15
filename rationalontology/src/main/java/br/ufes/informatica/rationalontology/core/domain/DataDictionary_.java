package br.ufes.informatica.rationalontology.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;

@Generated(value="Dali", date="2019-10-08T16:54:48.776-0300")
@StaticMetamodel(DataDictionary.class)
public class DataDictionary_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<DataDictionary, String> concept;
	public static volatile SingularAttribute<DataDictionary, String> definition;
	public static volatile SingularAttribute<DataDictionary, String> source;
	public static volatile SingularAttribute<DataDictionary, Ontology> Source;
}
