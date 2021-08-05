package kr.pe.elex.examples.hibernate;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class PersonRepository extends BaseCrudRepository<Person> {

	public PersonRepository() {
		super();
	}

	public List<Person> retrieveAll2() {
		Session session = getSession();
		CriteriaQuery<Person> query = session.getCriteriaBuilder()
				.createQuery(Person.class);
		TypedQuery<Person> q = session.createQuery(query.select(query.from(Person.class)));
		session.close();
		return q.getResultList();
	}

	public List<Person> retrieveAll() {
		Session session = getSession();
		List<Person> list = session
				.createQuery("FROM Person p", Person.class).list();
		session.close();
		return list;
	}

	public Optional<Person> retrieveById(long id) {
		Session session = getSession();
		Person person = (Person) session
				.createQuery("FROM Person p where p.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.uniqueResult();
		session.close();
		return Optional.ofNullable(person);
	}
}
