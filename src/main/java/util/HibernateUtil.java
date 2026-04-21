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
			// Charger configuration et les mappings depuis hibernate.cfg.xml
			org.hibernate.boot.registry.StandardServiceRegistry standardRegistry = 
					new org.hibernate.boot.registry.StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();

			org.hibernate.boot.Metadata metadata = 
					new org.hibernate.boot.MetadataSources(standardRegistry)
					.getMetadataBuilder()
					.build();

			sessionFactory = metadata.getSessionFactoryBuilder().build();
			System.out.println("Session est creee avec succes.");

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
