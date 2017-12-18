package camadaDeNegocio;

import java.util.ArrayList;

import camadaDeDados.AtorRDF;
import modelo.Ator;

public class AtorLeitura {
	private AtorRDF consulta = new AtorRDF();
	
	public AtorLeitura() {
	}
	
	public ArrayList<Ator> listaAtor(){
		return consulta.listaAtores();
	}
}
