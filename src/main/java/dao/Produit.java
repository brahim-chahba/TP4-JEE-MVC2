package dao;

import javax.persistence.*;

@Entity
@Table(name = "produits")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduit; // Identifiant unique du produit
	private String nom; // Nom du produit
	private String description; // Description détaillée du produit
	private Double prix;

	public Produit() {
	}

	// Constructeur utile pour créer rapidement un produit sans ID (ex: lors de
	// l'ajout)
	public Produit(String nom, String description, Double prix) {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrix() {
		return prix;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}
}
