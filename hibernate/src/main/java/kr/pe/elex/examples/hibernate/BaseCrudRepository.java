package kr.pe.elex.examples.hibernate;

import org.hibernate.Session;

public abstract class BaseCrudRepository<T> extends BaseRepository {

	public T create(T o) {
		Session session = getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();

		return o;
	}

	public T update(T person) {
		Session session = getSession();
		session.beginTransaction();
		session.update(person);
		session.getTransaction().commit();
		session.close();

		return person;
	}

	public void delete(T person) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(person);
		session.getTransaction().commit();
		session.close();
	}
}
