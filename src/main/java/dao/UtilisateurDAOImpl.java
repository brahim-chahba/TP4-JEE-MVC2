package dao;

import util.HibernateUtil;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UtilisateurDAOImpl implements UtilisateurDAO {

	public UtilisateurDAOImpl() {
		insertIfNotExists(new Utilisateur("admin", "1", "ADMIN"));
		insertIfNotExists(new Utilisateur("user", "1", "USER"));
	}

	private void insertIfNotExists(Utilisateur u) {
		if (findByLoginAndPassword(u.getLogin(), u.getPassword()) == null)
			save(u);
	}

	@Override
	public void save(Utilisateur u) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
	};

	@Override
	public Utilisateur findByLoginAndPassword(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Utilisateur> query = session.createQuery("FROM Utilisateur WHERE login=:login AND password=:pass",
					Utilisateur.class);
			query.setParameter("login", login);
			query.setParameter("pass", password);
			return query.uniqueResult();
		} finally {
			session.close();
		}
	}
}
