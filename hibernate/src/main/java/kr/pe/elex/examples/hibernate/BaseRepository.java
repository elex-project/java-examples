package kr.pe.elex.examples.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

abstract class BaseRepository {
	private static final Configuration configuration;
	private static SessionFactory factory;

	static {
		try {
			configuration = new Configuration().configure();
			factory = configuration.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	protected Session getSession(){
		Session session = factory.openSession();
		if (!session.isConnected()){
			factory = configuration.buildSessionFactory();
			session = getSession();
		}
		return session;
	}
}
