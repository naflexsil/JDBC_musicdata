package org.example.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task6 {

    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void fetchBooksYoungerThan2000() {
        String query = "SELECT id, name, author, publishing_year FROM study.books WHERE publishing_year > 2000;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("Books published after 2000:");
            while (resultSet.next()) {
                System.out.printf("ID: %d, Name: %s, Author: %s, Year: %d%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getInt("publishing_year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
