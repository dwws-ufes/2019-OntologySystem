package br.ufes.informatica.rationalontology.core.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;

import br.ufes.informatica.rationalontology.RDF_RO;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.CompetencyQuestion;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.Ontology;
import br.ufes.informatica.rationalontology.core.domain.SubOntology;
import br.ufes.informatica.rationalontology.core.persistence.CompetencyQuestionDAO;
import br.ufes.informatica.rationalontology.core.persistence.DataDictionaryDAO;
import br.ufes.informatica.rationalontology.core.persistence.OntologyDAO;
import br.ufes.informatica.rationalontology.core.persistence.SubOntologyDAO;

@WebServlet(urlPatterns = { "/data/ontologies/*" })
public class ListOntologiesInRDFServelet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private Model model;
	private String myNS = "http://localhost:8080/RationalOntology/data/ontologies/";
	private String grNS = "http://purl.org/goodrelations/v1#";
	private Resource grOntology;
	private Resource grSubOntology;
	private Resource grCompetencyQuestion;
	private Resource grTerm;
	
	@EJB
	private OntologyDAO ontologyDAO;
	
	@EJB
	private SubOntologyDAO subOntologyDAO;
	
	@EJB
	private CompetencyQuestionDAO competencyQuestionDAO;
	
	@EJB
	private DataDictionaryDAO dataDictionaryDAO;

	/*
	resp.setContentType("text/xml");
	List<TourPackage> packs = tourPackageDAO.retrieveAll();
	Model model = ModelFactory.createDefaultModel();
	String myNS = "http://localhost:8080/CDITravel/data/TourPackage/";
	String grNS = "http://purl.org/goodrelations/v1#";
	model.setNsPrefix("gr", grNS);
	Resource grOffering = ResourceFactory.createResource(grNS + "Offering");
	Resource grPriceSpecification = ResourceFactory.createResource(grNS + "PriceSpecification");
	Property gravailabilityStarts = ResourceFactory.createProperty(grNS + "availabilityStarts");
	Property gravailabilityEnds = ResourceFactory.createProperty(grNS + "availabilityEnds");
	Property grhasPriceSpecification = ResourceFactory.createProperty(grNS + "hasPriceSpecification");
	Property grhasCurrencyValue = ResourceFactory.createProperty(grNS + "hasCurrencyValue");
	
	for (TourPackage pack : packs) {
		model.createResource(myNS + pack.getId())
				.addProperty(RDF.type, grOffering)
				.addProperty(RDFS.label, pack.getName())
				.addProperty(RDFS.comment, pack.getDescription())
				.addLiteral(gravailabilityStarts,
						ResourceFactory.createTypedLiteral(df.format(pack.getBegin()), XSDDatatype.XSDdateTime))
				.addLiteral(gravailabilityEnds,
						ResourceFactory.createTypedLiteral(df.format(pack.getEnd()), XSDDatatype.XSDdateTime))
				
				.addProperty(grhasPriceSpecification,
						model.createResource().addProperty(RDF.type, grPriceSpecification)
								.addLiteral(grhasCurrencyValue, pack.getPrice().floatValue()));
	}
	try (PrintWriter out = resp.getWriter()) {
		model.write(out, "RDF/XML");
	}
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		int param = getParam(req);
		
		resp.setContentType("text/xml");
		
		model = RDF_RO.getModel();
		model.setNsPrefix("gr", grNS);
		model.setNsPrefix("ro", RDF_RO.vocabularyNS);

		generateRDFOntologies(model, param);
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");		
//			model.write(System.out);
		}		
		
		gravaBanco( model );
	}
	
	public void gravaBanco(Model model) {
		try {
            DatasetAccessor accessor = DatasetAccessorFactory.createHTTP("http://localhost:3030/myTriples/data");
            //accessor.putModel(m);	 
            accessor.add(model);
        }
		catch(Exception ex) {
			System.out.println("Erro ao manipular arquivos: "+ ex.toString());
		}
	}
	
	private int getParam(HttpServletRequest req) {
		String URI = req.getRequestURI();
		String param = URI.substring(URI.lastIndexOf("/")+1) ;
		
		int result = -1;
		
		if(!param.trim().equals("")) {
			try {
				result = Integer.parseInt(param) ;
			}
			catch(Exception e) {
				System.out.println("O parâmetro não corresponte a um número inteiro. "+ e.toString());
			}
		}
		return result;
	}
	
	private void generateRDFOntologies(Model model, int param) {
		grOntology = ResourceFactory.createResource(grNS + "Ontology");
		grSubOntology = ResourceFactory.createResource(grNS + "SubOntology");
		grCompetencyQuestion = ResourceFactory.createResource(grNS + "CompetencyQuestion");
		grTerm = ResourceFactory.createResource(grNS + "term");
		
		List<Ontology> ontologies = new ArrayList<Ontology>();
		Resource res;
		
		if(param == -1) {
			ontologies = ontologyDAO.getOntologiesByUser(SessionInformation.getInstance().getUsuarioLogado2());
		}
		else {
			ontologies.add(ontologyDAO.findOntologyByID(param));
		}
		
		for (Ontology ontology : ontologies) {
			res = model.createResource(myNS + ontology.getId());
			res.addProperty(RDF.type, grOntology)
				.addProperty(RDF_RO.name, ontology.getName())
				.addProperty(RDF_RO.nickname, ontology.getNickname())
				.addProperty(RDF_RO.description, ontology.getDescription())
				.addProperty(RDF_RO.domainDescription, ontology.getDomainDescription())
				.addProperty(RDF_RO.IntendedUse, ontology.getIntendedUse() );
			
			for(SubOntology subOntology : subOntologyDAO.getSubOntologyByOntology(ontology)) {
				res.addProperty(RDF_RO.formedBy, getRDFSubOntology(model, subOntology));
			}
			
			for(DataDictionary term: dataDictionaryDAO.getDataDictionary(ontology)) {
				res.addProperty(RDF_RO.term, getRDFDataDictionary(model, term));
			}
		}
	}
	
	private Resource getRDFSubOntology(Model model, SubOntology subOntology){
		Resource res = model.createResource(myNS + "SubOntology/" +subOntology.getId()); //+ "SubOntology/" 
		
		res.addProperty(RDF.type, grSubOntology)
			.addProperty(RDF_RO.name, subOntology.getName())
			.addProperty(RDF_RO.shortDesctiption, subOntology.getShortDescription())
			.addProperty(RDF_RO.fullDesctiption, subOntology.getFullDescription());
		
		for(CompetencyQuestion cp : competencyQuestionDAO.getCompetenceQuestionBySubOntology(subOntology)) {
				res.addProperty(RDF_RO.competencyQuestion, getRDFCompetencyQuestion(model, cp));
			}	
			
		return res;
	}
	
	private Resource getRDFCompetencyQuestion(Model model, CompetencyQuestion cp){
		
		return model.createResource(myNS + "CompetencyQuestion/" +cp.getId())
				.addProperty(RDF.type, grCompetencyQuestion)
				.addProperty(RDF_RO.identifier, cp.getIdentificator())
				.addProperty(RDF_RO.description, cp.getDescription());
	}
	
	private Resource getRDFDataDictionary(Model model, DataDictionary term) {
		//Resource res = model.createResource(myNS + term.getId());
		Resource res = model.createResource(myNS + "DataDictionary/" + term.getId()); // +"DataDictionary/"
		
		return res .addProperty(RDF.type, grTerm)
					.addProperty(RDF_RO.concept, term.getConcept())
					.addProperty(RDF_RO.definition, term.getDefinition())
					.addProperty(RDF_RO.source, term.getSource());
	}
	
}

