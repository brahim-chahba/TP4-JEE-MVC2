package dao;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdUtilisateur;
	private String login;
	private String password;
	private String role;

	public Utilisateur() {
	}

	public Utilisateur(String login, String pass, String role) {
		// TODO Auto-generated constructor stub
		this.login = login;
		this.password = pass;
		this.role = role;
	}

	public Long getIdUtilisateur() {
		return IdUtilisateur;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		IdUtilisateur = idUtilisateur;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	};

}
