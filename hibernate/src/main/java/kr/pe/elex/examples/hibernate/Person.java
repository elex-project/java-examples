package kr.pe.elex.examples.hibernate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long id;
	@Column(name = "name")
	public String name;
	@Column(name = "age")
	public int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
