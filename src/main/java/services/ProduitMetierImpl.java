package services;

import dao.Produit;
import dao.ProduitDAO;
import dao.ProduitDAOImpl;
import java.util.List;

/***
 * Implémentation Singleton du service produit. Délègue toutes les opérations à
 * ProduitDAOImpl (Hibernate)
 */
public class ProduitMetierImpl implements ProduitMetier {

	private static ProduitMetierImpl instance;
	private ProduitDAO dao;

	private ProduitMetierImpl() {
		dao = new ProduitDAOImpl();
	}

	public static ProduitMetierImpl getInstance() {
		if (instance == null) {
			instance = new ProduitMetierImpl();
		}
		return instance;
	}

	@Override
	public void addProduit(Produit p) {
		dao.addProduit(p);
	}

	@Override
	public void deleteProduit(Long id) {
		dao.deleteProduit(id);
	};

	@Override
	public void updateProduit(Produit p) {
		dao.updateProduit(p);
	}

	@Override
	public Produit getProduitById(Long id) {
		return dao.getProduitById(id);
	}

	@Override
	public List<Produit> getAllProduit() {
		return dao.getAllProduit();
	}
}
