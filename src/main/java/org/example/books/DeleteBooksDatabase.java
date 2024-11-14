package org.example.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteBooksDatabase {

    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void deleteBooksTable() {
        String sql = """
            DROP TABLE IF EXISTS books;
            DROP TABLE IF EXISTS visitors;
            """;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Tables 'books' and 'visitors' deleted successfully");
        } catch (SQLException e) {
            System.err.println("Failed to delete tables.");
            e.printStackTrace();
        }
    }
}
