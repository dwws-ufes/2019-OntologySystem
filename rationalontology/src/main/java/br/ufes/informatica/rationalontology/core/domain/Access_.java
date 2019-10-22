package br.ufes.informatica.rationalontology.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;

@Generated(value="Dali", date="2019-10-08T16:54:48.759-0300")
@StaticMetamodel(Access.class)
public class Access_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Access, Short> accessType;
	public static volatile SingularAttribute<Access, Date> canceledData;
	public static volatile SingularAttribute<Access, User> Source;
	public static volatile SingularAttribute<Access, Ontology> Target;
	public static volatile SingularAttribute<Access, String> email_text;
}
