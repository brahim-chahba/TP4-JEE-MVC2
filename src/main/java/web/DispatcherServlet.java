package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ProduitMetierImpl;

/**
 * r DispatcherServlet — Coeur de l'architecture MVC2.
 *
 * Une SEULE servlet remplace les 5 servlets de MVC1. Elle lit le paramètre
 * "action" dans la requête et route vers la bonne logique métier.
 *
 * URLs : /produit?action=list /produit?action=add (POST)
 * /produit?action=delete&id=1 /produit?action=edit&id=1 /produit?action=update
 * (POST)
 */
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final ProduitMetierImpl metier = ProduitMetierImpl.getInstance();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
