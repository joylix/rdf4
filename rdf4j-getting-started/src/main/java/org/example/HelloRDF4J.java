package org.example;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

public class HelloRDF4J {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Repository rep = new SailRepository(new MemoryStore());
		
		Namespace ex = Values.namespace("ex", "http://example.org/");
		IRI john = Values.iri(ex, "john");
		try (RepositoryConnection conn = rep.getConnection()) {
			conn.add(john, RDF.TYPE, FOAF.PERSON);
			conn.add(john, RDFS.LABEL, Values.literal("John"));
			RepositoryResult<Statement> statements = conn.getStatements(null, null, null);
			
			Model model = QueryResults.asModel(statements);
			model.setNamespace(RDF.NS);
			model.setNamespace(RDFS.NS);
			model.setNamespace(FOAF.NS);
			model.setNamespace(ex);
			
			Rio.write(model, System.out, RDFFormat.TURTLE);
		}

	}

}
