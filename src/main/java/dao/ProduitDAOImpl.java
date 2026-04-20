package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import util.HibernateUtil;
import org.hibernate.Transaction;

/**
 * Implémentation de ProduitDAO avec Hibernate.
 *
 * Chaque méthode : 1. Ouvre une Session Hibernate 2. Démarre une Transaction 3.
 * Exécute l'opération 4. Commit (ou rollback si erreur) 5. Ferme la Session
 */
public class ProduitDAOImpl implements ProduitDAO {
	@Override
	public void addProduit(Produit p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction Tx = null;
		try {
			Tx = session.beginTransaction();
			session.save(p);
			Tx.commit();
		} catch (Exception e) {
			if (Tx != null)
				Tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Produit getProduitById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return session.get(Produit.class, id);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getAllProduit() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return session.createQuery("FROM produits").list();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteProduit(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Produit p = session.get(Produit.class, id);
			if (p != null)
				session.delete(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
	}

	@Override
	public void updateProduit(Produit p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
