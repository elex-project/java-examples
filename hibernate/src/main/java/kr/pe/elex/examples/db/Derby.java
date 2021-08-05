package kr.pe.elex.examples.db;

import com.elex_project.abraxas.Console;

import java.sql.*;

public class Derby {
	public static void main(String... args){
		try {
			Connection connection = DriverManager.getConnection("jdbc:derby:dbtest/derby;create=true");

			try {
				connection.createStatement()
						.execute("CREATE TABLE person(name VARCHAR(16), age INT)");
			} catch (SQLException e){
				if (e.getSQLState().equals("X0Y32")){
					System.out.println("Already exists.");
				}
			}

			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO person (name, age) VALUES (?,?)");
			statement.setString(1, "Steve");
			statement.setInt(2, 37);
			statement.executeUpdate();

			ResultSet result = connection.createStatement()
					.executeQuery("SELECT * FROM person");

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
