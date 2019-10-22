package br.ufes.informatica.rationalontology.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;

@Generated(value="Dali", date="2019-10-08T16:54:48.771-0300")
@StaticMetamodel(CompetencyQuestion.class)
public class CompetencyQuestion_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<CompetencyQuestion, String> identificator;
	public static volatile SingularAttribute<CompetencyQuestion, String> description;
	public static volatile SingularAttribute<CompetencyQuestion, SubOntology> Source;
}
