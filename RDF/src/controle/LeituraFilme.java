package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import camadaDeNegocio.FilmeLeitura;
import modelo.Ator;
import modelo.Filme;

/**
 * Servlet implementation class LeituraFilme
 */
@WebServlet("/LeituraFilme")
public class LeituraFilme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeituraFilme() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Ator ator = new Ator(request.getParameter("nomeAtor"));
			FilmeLeitura listaFilmes = new FilmeLeitura();
			ArrayList<Filme> filmes = listaFilmes.listaFilmes(ator.getNome());
			
			request.setAttribute("filmes", filmes);
			getServletContext().getRequestDispatcher("/filmografia.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
