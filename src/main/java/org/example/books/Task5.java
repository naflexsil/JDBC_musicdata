package org.example.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task5 {

    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void fetchSortedBooksByYear() {
        String query = "SELECT id, name, author, publishing_year FROM study.books ORDER BY publishing_year;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("Books sorted by publishing year:");
            while (resultSet.next()) {
                System.out.printf("ID: %d, Name: %s, Year: %d%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("publishing_year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
