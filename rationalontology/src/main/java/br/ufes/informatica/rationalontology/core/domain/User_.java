package br.ufes.informatica.rationalontology.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-10-08T16:54:48.793-0300")
@StaticMetamodel(User.class)
public class User_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> institution;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Access> Target;
}