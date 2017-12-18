package camadaDeDados;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import modelo.Ator;
import modelo.Filme;

public class FilmeRDF {
	static final String inputFileName  = "C:/Users/Patrick/Dropbox/Faculdade/7º período/T.E.B.D/RDF/Trabalho_2_TEBD/Atores.rdf";
	
public ArrayList<Filme> listaFilmes (String nomeAtor){		
		
		Model model = ModelFactory.createDefaultModel();
		ArrayList<Filme> filmes = new ArrayList<Filme>();

	    InputStream in = FileManager.get().open( inputFileName );
	    if (in == null) {
	        throw new IllegalArgumentException( "File: " + inputFileName + " not found");
	    }
	    
	    FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
	    model = FileManager.get().loadModel(inputFileName);
	    
	    String sparql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	  			  + "PREFIX	vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> "
	  		+ "SELECT ?nomeCompleto ?filmes ?lista"
	  		+ " WHERE "
	  		+ "{ ?y vcard:FN ?nomeCompleto . "
	  		+ "	?x vcard:LABEL ?filmes ."
	  		+ "	?y vcard:hasRole ?x ."
	  		+ "FILTER (?nomeCompleto = \"" + nomeAtor + "\")"
	  		+ "}";
	    
	    Query query = QueryFactory.create(sparql);
	    
	    QueryExecution qexec = QueryExecutionFactory.create(query, model);
	    
	    try {
			ResultSet results = qexec.execSelect();
			while ( ((org.apache.jena.query.ResultSet) results).hasNext() ) {
				QuerySolution soln = (results).nextSolution();
				Literal titulo = soln.getLiteral("filmes");
				
				Filme filme = new Filme(titulo.toString());
				filmes.add(filme);
				System.out.println(filme.getTitulo());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    qexec.close();
	    
		return filmes;
	}
}
