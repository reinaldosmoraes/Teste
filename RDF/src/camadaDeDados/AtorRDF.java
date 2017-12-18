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

public class AtorRDF {
	static final String inputFileName  = "C:/Users/Patrick/Dropbox/Faculdade/7º período/T.E.B.D/RDF/Trabalho_2_TEBD/Atores.rdf";
	
	public ArrayList<Ator> listaAtores (){		
		
		Model model = ModelFactory.createDefaultModel();
		ArrayList<Ator> atores = new ArrayList<Ator>();

	    InputStream in = FileManager.get().open( inputFileName );
	    if (in == null) {
	        throw new IllegalArgumentException( "File: " + inputFileName + " not found");
	    }
	    
	    //model.read(in, "");
	    
	    //model.write(System.out);
	    
	    FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
	    model = FileManager.get().loadModel(inputFileName);
	    
	    String sparql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			  + "PREFIX	vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> "
	    		+ "SELECT ?nomeCompleto ?premios ?conjuge ?filho ?nascimento ?nacionalidade ?enderecoAtual ?filmes ?lista"
	    		+ " WHERE "
	    		+ "{ ?x vcard:FN ?nomeCompleto . "
	    		+ "	?x vcard:CATEGORIES ?premios ."
	    		+ "	?x vcard:Family ?conjuge ."
	    		+ "	?x vcard:NAME ?filho ."
	    		+ "	?x vcard:BDAY ?nascimento ."
	    		+ "	?x vcard:Country ?nacionalidade ."
	    		+ "	?x vcard:Locality ?enderecoAtual ."
	    		+ "}";
	    
	    Query query = QueryFactory.create(sparql);
	    
	    QueryExecution qexec = QueryExecutionFactory.create(query, model);
	    
	    try {
			ResultSet results = qexec.execSelect();
			while ( ((org.apache.jena.query.ResultSet) results).hasNext() ) {
				QuerySolution soln = (results).nextSolution();
				Literal nome = soln.getLiteral("nomeCompleto");
				Literal premios = soln.getLiteral("premios");
				Literal conjuge = soln.getLiteral("conjuge");
				Literal filho = soln.getLiteral("filho");
				Literal nascimento = soln.getLiteral("nascimento");
				Literal nacionalidade = soln.getLiteral("nacionalidade");
				Literal enderecoAtual = soln.getLiteral("enderecoAtual");
				
				Ator ator = new Ator(nome.toString(), premios.toString(), conjuge.toString(), 
						filho.toString(), nascimento.toString(), nacionalidade.toString(), enderecoAtual.toString());
				atores.add(ator);
				
				System.out.println(ator.getNome());
				
				//Literal name8 = soln.getLiteral("filmes");
				System.out.println("Nome: " + nome);
				System.out.println("Prêmios: " + premios);
				System.out.println("Cônjuge: " + conjuge);
				System.out.println("Filho: " + filho);
				System.out.println("Data de Nascimento: " + nascimento);
				System.out.println("Nacionalidade " + nacionalidade);
				System.out.println("Endereço Atual: " + enderecoAtual);
				//System.out.println("Filmes: " + name8);
				//System.out.println("\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    qexec.close();
	    
		return atores;
	}
}
