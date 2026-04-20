package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
 * creee un seul instance de session Factory 
 * 
 * */
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		try {
			// charger configuration
			Configuration configuration = new Configuration().configure();
			// cree le registre de service
			ServiceRegistry serviceRegestry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			// constraction de sessionFactory
			sessionFactory = configuration.buildSessionFactory(serviceRegestry);
			System.out.println("Session est cree avec succes .");

		} catch (Exception e) {
			System.err.println(" Erreur lors de la ceation : " + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

}
