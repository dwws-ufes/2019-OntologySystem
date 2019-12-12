package br.ufes.informatica.rationalontology;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class RDF_RO {

	public static String vocabularyNS = "http://localhost:8080/rationalontology/rdf/v1#";
	
	private static Model model;
	
	public static Property name;
	
	public static Property nickname;
	
	public static Property description;
	
	public static Property formedBy;
	
	public static Property shortDesctiption;
	
	public static Property fullDesctiption;
	
	public static Property competencyQuestion;
	
	public static Property identifier;
	
	public static Property term;
	
	public static Property concept;
	
	public static Property definition;
	
	public static Property source;
	
	public static Property domainDescription;
	
	public static Property IntendedUse;
	
	public static Property owner;
	
	public static final Model getModel() {
		model = ModelFactory.createDefaultModel();
		
		name = model.createProperty(vocabularyNS + "name");
		
		nickname = model.createProperty(vocabularyNS + "nickname");
		
		description = model.createProperty(vocabularyNS + "description");
		
		formedBy = model.createProperty(vocabularyNS + "formedBy");
		
		shortDesctiption = model.createProperty(vocabularyNS + "shortDesctiption");
		
		fullDesctiption = model.createProperty(vocabularyNS + "fullDesctiption");
		
		competencyQuestion = model.createProperty(vocabularyNS + "competencyQuestion");
		
		identifier = model.createProperty(vocabularyNS + "identifier");
		
		term = model.createProperty(vocabularyNS + "term");
		
		concept = model.createProperty(vocabularyNS + "concept");
		
		definition = model.createProperty(vocabularyNS + "definition");
		
		source = model.createProperty(vocabularyNS + "source");
		
		domainDescription = model.createProperty(vocabularyNS + "domainDescription");
		
		IntendedUse = model.createProperty(vocabularyNS + "IntendedUse");
		
		owner = model.createProperty(vocabularyNS + "owner");
		
		return  model;
	}
}
