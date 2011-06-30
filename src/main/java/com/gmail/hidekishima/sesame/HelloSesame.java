package com.gmail.hidekishima.sesame;

import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.Sail;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.memory.MemoryStore;

/**
 * This class briefly introduces what the Sesame can do.
 *  
 * @author Hideki Shima
 *
 */
public class HelloSesame {
	
	private final static String NAMESPACE = "http://seit1.lti.cs.cmu.edu/ontology#";
	
	public void demo() throws RepositoryException, OpenRDFException {
		Repository repo = getOnMemoryRepository(); // initialize a repository
		populateHardCodedTriples( repo ); // add triples to the repository
		searchWithSeRQL( repo ); // search the repository using SeRQL query language
	}
	
	/**
	 * Creates an on-memory Repository object.
	 * See more details at http://www.openrdf.org/doc/sesame2/users/ch07.html#section-native-store-config
	 * 
	 */
	private Repository getOnMemoryRepository() throws RepositoryException {
		MemoryStore memory = new MemoryStore(); 
		Sail sail = new ForwardChainingRDFSInferencer( memory ); // This class allows inference
		Repository repo = new SailRepository( sail ); // SAIL stands for Storage And Inference Layer
		repo.initialize();
		return repo;
	}
	
	/**
	 * Adds triples to the repository using hard coded data.
	 * Alternatively, we can read an RDF file to populate data.
	 * 
	 * @param repo
	 * @throws RepositoryException
	 */
	private void populateHardCodedTriples( Repository repo ) throws RepositoryException {
		ValueFactory factory = repo.getValueFactory();
		
		// Let's define 3 people, 2 universities and 3 relations
		
		/**
		 * Here comes people and universities instances which 
		 * will be RDF "subject" or "object" (note that these 
		 * terms are used slightly in a different way in RDF)
		 * 
		 * Note that concepts in RDF are represented as URI.
		 */
		URI eric   = factory.createURI( NAMESPACE+"eric" );
		URI hideki = factory.createURI( NAMESPACE+"hideki" );
		URI hovy   = factory.createURI( NAMESPACE+"hovy" );
		
		URI cmu    = factory.createURI( NAMESPACE+"cmu" );
		URI usc    = factory.createURI( NAMESPACE+"usc" );
		
		// Let's define an alternative form of CMU.
		URI cmuAlias = factory.createURI( NAMESPACE+"carnegie_mellon_university" );
		
		/**
		 * Here comes relationships (called "predicate" in the RDF world).
		 * 
	     * You can use static reference to one of the vocabulary primitives
	     * provided by sesame, or your own ontology. Following primitives 
	     * are provided by sesame under the package org.openrdf.model.vocabulary. 
	     * 
	     * OWL - Constants for OWL primitives and for the OWL namespace.
	     * RDF - Constants for RDF primitives and for the RDF namespace.
	     * RDFS - Constants for RDF Schema primitives and for the RDF Schema namespace.
	     * SESAME - Defines constants for the Sesame schema namespace
	     * XML - Defines constants for the standard XML Schema datatypes.
	     * 
	     * Primitives and inference rules for them are defined in the 
	     * W3C RDF Semantics spec at http://www.w3.org/TR/rdf-mt/
	     */
		URI isa = RDFS.SUBCLASSOF; // predefined predicate
		URI worksFor = factory.createURI( NAMESPACE+"worksFor" ); // original predicate
		URI coauthoredWith = factory.createURI( NAMESPACE+"coauthoredWith" ); // original predicate
		
		// Let's add triples into the repository 
		RepositoryConnection con = repo.getConnection();
		try {
			con.setAutoCommit(false);
			
		    con.add(eric,   worksFor, cmu);
		    con.add(hideki, worksFor, cmu);
		    con.add(hovy,   worksFor, usc);
		    
		    // Note that direction matters.
		    con.add(cmuAlias, isa, cmu);
		    con.add(cmu,      isa, cmuAlias);
		    
		    con.add(eric,   coauthoredWith, hovy);
		    con.add(hovy,   coauthoredWith, eric);
		    con.add(eric,   coauthoredWith, hideki);
		    con.add(hideki, coauthoredWith, eric);
		  
		    con.commit();
		} catch (RepositoryException e) {
		    // If something went wrong during the transaction, let's roll it back
		    con.rollback();
		} finally {
			con.close();
		}
	}
	
	/**
	 * Searches in the repository with various SeRQL queries. 
	 * 
	 * See the query language spec at:
	 * http://www.openrdf.org/doc/sesame2/users/ch09.html
	 * 
	 * @param repo
	 * @throws RepositoryException
	 * @throws OpenRDFException
	 */
	private void searchWithSeRQL( Repository repo ) 
		throws RepositoryException, OpenRDFException {
		RepositoryConnection con = repo.getConnection();
		String queryString = null;
		String postfix = " USING NAMESPACE seit = <"+NAMESPACE+">";
		try {
			
			System.out.println( "#### Which university does Eric belong to?" );
			queryString = "SELECT * FROM {seit:eric} seit:worksFor {answer}"+postfix;
			printSearchResult( con, queryString );
			
			System.out.println( "#### Which university do Eric AND Hideki belong to?" );
			queryString = "SELECT * FROM {seit:eric, seit:hideki} seit:worksFor {answer}"+postfix;
			printSearchResult( con, queryString );
			
			System.out.println( "#### Who belongs to CMU?" );
			queryString = "SELECT * FROM {answer} seit:worksFor {seit:cmu}"+postfix;
			printSearchResult( con, queryString );
			
			System.out.println( "#### What relationship exists between Eric and CMU?" );
			queryString = "SELECT * FROM {seit:eric} answer {seit:cmu}"+postfix;
			printSearchResult( con, queryString );
			
			System.out.println( "#### Who coauthored with whom?" );
			queryString = "SELECT * FROM {answer1} seit:coauthoredWith {answer2}"+postfix;
			printSearchResult( con, queryString );
			
			System.out.println( "#### Is there such a person who coauthored a paper with Hideki, and another with Hovy?" );
			queryString = "SELECT * FROM {seit:hideki} seit:coauthoredWith {answer} seit:coauthoredWith {seit:hovy}"+postfix;
			printSearchResult( con, queryString );
			
			System.out.println( "#### Which CMU person did Hovy coauthor with?" );
			queryString = "SELECT * FROM {seit:hovy} seit:coauthoredWith {answer} seit:worksFor {} rdfs:subClassOf {seit:cmu}"+postfix;
			printSearchResult( con, queryString );
			
			// Can sesame infer cmu == carnegie mellon university are the same thing?
			System.out.println( "#### Which Carnegie Mellon University person did Hovy coauthor with?" );
			queryString = "SELECT * FROM {seit:hovy} seit:coauthoredWith {answer} seit:worksFor {} rdfs:subClassOf {seit:carnegie_mellon_university}"+postfix;
			printSearchResult( con, queryString );
			
		} finally {
			con.close();
		}
	}
	
	/**
	 * Shows the search result from the repository given a query.
	 * 
	 * @param con
	 * @param queryString
	 * @throws RepositoryException
	 * @throws OpenRDFException
	 */
	private void printSearchResult( RepositoryConnection con, String queryString ) 
		throws RepositoryException, OpenRDFException {
	    TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
	    TupleQueryResult result = tupleQuery.evaluate();
	    try {
			int i=0;
	    	while ( result.hasNext() ) {
		    	System.out.println( "==== Result "+(++i)+" ====");
		    	BindingSet bindingSet = result.next();
	    		List<String> names = result.getBindingNames();
		    	for ( String name : names ) {
			    	System.out.println( name+": "+bindingSet.getValue(name) );
		    	}
		    	System.out.println();
	    	}
	    } finally {
	    	result.close();
	    }
	}
	
	public static void main(String[] args) {
		try {
			new HelloSesame().demo();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
