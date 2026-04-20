package dao;

public interface UtilisateurDAO {

	public Utilisateur findByLoginAndPassword(String login, String password);

	void save(Utilisateur u);
}
