package camadaDeNegocio;

import java.util.ArrayList;
import modelo.Filme;
import org.apache.jena.rdf.model.Literal;

import camadaDeDados.FilmeRDF;

public class FilmeLeitura {
	private FilmeRDF filme = new FilmeRDF();
	
	public FilmeLeitura(){
		
	}
	
	public ArrayList<Filme> listaFilmes (String nome){
		return filme.listaFilmes(nome);
	}
}
