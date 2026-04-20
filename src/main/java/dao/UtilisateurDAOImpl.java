package dao;

import util.HibernateUtil;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UtilisateurDAOImpl implements UtilisateurDAO {
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
			Query<Utilisateur> query = session.createQuery("FROM users WHERE login=:login AND password=:pass",
					Utilisateur.class);
			query.setParameter("login", login);
			query.setParameter("pass", password);
			return query.uniqueResult();
		} finally {
			session.close();
		}
	}
}
