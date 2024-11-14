package org.example.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Task7 {
    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void addPersonalInfo() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Insert visitor data, ensuring no conflict on the phone number
            String insertVisitor = "INSERT INTO study.visitors (name, surname, phone, subscribed) " +
                    "VALUES ('Alina', 'Starikova', '895-456-7372', true) " +
                    "ON CONFLICT (phone) DO NOTHING;";
            statement.executeUpdate(insertVisitor);

            // Insert book 1, ensuring no conflict on ISBN
            String insertBook1 = "INSERT INTO study.books (name, author, publishing_year, isbn, publisher) " +
                    "VALUES ('Don Quixote', 'Miguel de Cervantes', 1605, '9788467030224', 'AST') " +
                    "ON CONFLICT (isbn) DO NOTHING;";
            statement.executeUpdate(insertBook1);

            // Insert book 2, ensuring no conflict on ISBN
            String insertBook2 = "INSERT INTO study.books (name, author, publishing_year, isbn, publisher) " +
                    "VALUES ('The Cherry Orchard', 'Anton Chekhov', 1904, '9785171032267', 'Eksmo') " +
                    "ON CONFLICT (isbn) DO NOTHING;";
            statement.executeUpdate(insertBook2);

            System.out.println("Your information and favorite books have been successfully added.");

            // Fetch and print the inserted information
            String fetchInfo = "SELECT v.name AS visitor_name, v.surname, v.phone, v.subscribed, " +
                    "b.name AS book_name, b.author, b.publishing_year " +
                    "FROM study.visitors v " +
                    "JOIN study.books b ON v.id IS NOT NULL " +
                    "WHERE v.name = 'Alina' AND v.surname = 'Starikova';";
            var resultSet = statement.executeQuery(fetchInfo);

            System.out.println("Your information and favorite books:");
            while (resultSet.next()) {
                System.out.printf("Visitor: %s %s, Phone: %s, Subscribed: %s\n",
                        resultSet.getString("visitor_name"),
                        resultSet.getString("surname"),
                        resultSet.getString("phone"),
                        resultSet.getBoolean("subscribed"));
                System.out.printf("Book: %s, Author: %s, Year of Publication: %d\n",
                        resultSet.getString("book_name"),
                        resultSet.getString("author"),
                        resultSet.getInt("publishing_year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
