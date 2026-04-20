package services;

import dao.Utilisateur;
import dao.UtilisateurDAO;
import dao.UtilisateurDAOImpl;

public class UtilisateurMetierImpl implements UtilisateurMetier {

	private static UtilisateurMetierImpl instance;
	private UtilisateurDAO dao;

	private UtilisateurMetierImpl() {
		dao = new UtilisateurDAOImpl();
	}

	public static UtilisateurMetierImpl getInstance() {
		if (instance == null) {
			instance = new UtilisateurMetierImpl();
		}
		return instance;
	}

	@Override
	public Utilisateur login(String login, String password) {
		return dao.findByLoginAndPassword(login, password);
	}
}
