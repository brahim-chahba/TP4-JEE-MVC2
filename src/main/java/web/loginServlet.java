package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Utilisateur;
import services.UtilisateurMetier;
import services.UtilisateurMetierImpl;

/**
 * Servlet implementation class loginServlet
 */

public class loginServlet extends HttpServlet {

	private static final UtilisateurMetier metier = UtilisateurMetierImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		Utilisateur user = metier.login(login, pass);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", user);
			response.sendRedirect("produit?action=liste");
		} else {
			request.setAttribute("error", "password ou login est incorecct");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
