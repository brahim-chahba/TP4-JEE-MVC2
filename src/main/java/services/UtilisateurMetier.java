package services;

import dao.Utilisateur;

public interface UtilisateurMetier {
	/**
	 * @return
	 */
	public Utilisateur login(String login, String password);
}
