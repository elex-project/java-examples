package kr.pe.elex.examples.hibernate;

import java.util.List;

public class ORM {

	public static void main(String... args) {
		PersonRepository repository = new PersonRepository();

		Person p;
		p = repository.create(new Person("Charlie", 17));
		System.out.println(p);
		p = repository.create(new Person("Steve", 37));
		System.out.println(p);
		p = repository.create(new Person("Agatha", 19));
		System.out.println(p);

		List<Person> list = repository.retrieveAll();
		for (Person person : list){
			System.out.println(person);
		}

		repository.retrieveById(3)
				.ifPresent(System.out::println);
	}
}
