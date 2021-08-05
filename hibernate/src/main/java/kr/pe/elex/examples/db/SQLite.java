package kr.pe.elex.examples.db;

import com.elex_project.abraxas.Console;

import java.sql.*;

public class SQLite {
	public static void main(String... args){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:dbtest/hibernate.sqlite");

			connection.createStatement()
					.execute("CREATE TABLE IF NOT EXISTS person(id INT, name TEXT, age INT);");

			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO person (name, age) VALUES (?,?);");
			statement.setString(1, "Steve");
			statement.setInt(2, 37);
			statement.executeUpdate();

			ResultSet result = connection.createStatement()
					.executeQuery("SELECT * FROM person;");

			while (result.next()) {
				Console.writeLine("{} is {}-year-old.",
						result.getString("name"), result.getInt("age"));
			}
			result.close();

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
