package dao;

import java.util.List;

public interface ProduitDAO {
	public void addProduit(Produit p);

	public void deleteProduit(Long id);

	public void updateProduit(Produit p);

	public Produit getProduitById(Long id);

	public List<Produit> getAllProduit();

}
