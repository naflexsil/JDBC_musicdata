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
        String deleteRecords = """
            DELETE FROM study.books WHERE isbn IN ('9788467030224', '9785171032267');
            DELETE FROM study.visitors WHERE phone = '895-456-7372';
            """;

        String dropTables = """
            DROP TABLE IF EXISTS study.books;
            DROP TABLE IF EXISTS study.visitors;
            """;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(deleteRecords);

            statement.executeUpdate(dropTables);

            System.out.println("Both 'books' and 'visitors' tables, along with related data, deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to delete tables.");
            e.printStackTrace();
        }
    }
}
