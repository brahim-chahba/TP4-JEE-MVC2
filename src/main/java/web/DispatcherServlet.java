package web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Produit;
import services.ProduitMetierImpl;

/**
 * r DispatcherServlet — Coeur de l'architecture MVC2.
 *
 * Une SEULE servlet remplace les 5 servlets de MVC1. Elle lit le paramètre
 * "action" dans la requête et route vers la bonne logique métier.
 *
 * URLs : /produit?action=list /produit?action=add (POST)
 * /produit?action=delete&id=1 /produit?action=edit&id=1
 * /produit?action=update(POST)
 */
@SuppressWarnings("serial")
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

	private static final ProduitMetierImpl metier = ProduitMetierImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		switch (action) {
		case "list":
			handelList(request, response);
			break;
		case "delete":
			handelDelete(request, response);
			break;
		case "edit":
			handelEdit(request, response);
			break;
		default:
			handelList(request, response);
		}
	};

	private void handelDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("idProduit");
		Long id = Long.parseLong(idParam);
		metier.deleteProduit(id);
		response.sendRedirect("produit?action=list");
	}

	private void handelEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idProduit"));
		Produit p = metier.getProduitById(id);
		request.setAttribute("produitEdit", p);
		request.setAttribute("listeProduits", metier.getAllProduit());
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		switch (action) {
		case "add":
			handelAdd(request, response);
			break;
		case "update":
			handelUpdate(request, response);
			break;
		default:
			handelList(request, response);
		}
	}

	private void handelAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		Double prix = Double.parseDouble(request.getParameter("prix"));
		Produit p = new Produit(nom, description, prix);
		metier.addProduit(p);
		response.sendRedirect("produit?action=list");
	}

	private void handelUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("idProduit"));
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		Double prix = Double.parseDouble(request.getParameter("prix"));
		Produit p = new Produit();

		p.setIdProduit(id);
		p.setNom(nom);
		p.setDescription(description);
		p.setPrix(prix);
		metier.updateProduit(p);
		response.sendRedirect("produit?action=list");
	}

	public void handelList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idProduit = req.getParameter("idProduit");
		if (idProduit != null) {
			try {
				Long id = Long.parseLong(idProduit);
				Produit p = metier.getProduitById(id);
				if (p != null) {
					req.setAttribute("listeProduits", java.util.Arrays.asList(p));
				} else {
					req.setAttribute("listeProduits", metier.getAllProduit());
					req.setAttribute("message", "aucaune produits avec cette id ");
				}
			} catch (NumberFormatException e) {
				req.setAttribute("listeProduits", metier.getAllProduit());
			}
		} else {
			req.setAttribute("listeProduits", metier.getAllProduit());
		}
		req.getRequestDispatcher("index.jsp").forward(req, res);
	}

}
