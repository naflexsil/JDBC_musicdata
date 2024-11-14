package org.example.books;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.Gson;

public class CreateBooksDatabase {

    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void createTables() {
        String createVisitorsTable = "CREATE TABLE IF NOT EXISTS study.visitors (" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "surname TEXT NOT NULL," +
                "phone TEXT NOT NULL," +
                "subscribed BOOLEAN NOT NULL);";

        String createBooksTable = "CREATE TABLE IF NOT EXISTS study.books (" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "author TEXT NOT NULL," +
                "publishing_year INT NOT NULL," +
                "isbn TEXT NOT NULL UNIQUE," +
                "publisher TEXT NOT NULL);";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(createVisitorsTable);
            statement.execute(createBooksTable);
            System.out.println("Tables created successfully.");

            // Добавление данных из books.json
            addDataFromJson(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addDataFromJson(Connection connection) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/org/example/books/books.json")) {
            Visitor[] visitors = gson.fromJson(reader, Visitor[].class);
            Set<String> uniqueBooks = new HashSet<>();

            for (Visitor visitor : visitors) {
                String insertVisitor = String.format("INSERT INTO study.visitors (name, surname, phone, subscribed) VALUES ('%s', '%s', '%s', %b) ON CONFLICT DO NOTHING;",
                        visitor.name, visitor.surname, visitor.phone, visitor.subscribed);
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate(insertVisitor);
                }

                for (Book book : visitor.favoriteBooks) {
                    if (uniqueBooks.add(book.isbn)) {
                        String insertBook = getString(book);
                        try (Statement stmt = connection.createStatement()) {
                            stmt.executeUpdate(insertBook);
                        }
                    }
                }
            }

            System.out.println("Data added successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getString(Book book) {
        String publisher = book.publisher.replace("'", "''");
        return String.format("INSERT INTO study.books (name, author, publishing_year, isbn, publisher) VALUES ('%s', '%s', %d, '%s', '%s') ON CONFLICT (isbn) DO NOTHING;",
                book.name.replace("'", "''"),
                book.author.replace("'", "''"),
                book.publishingYear,
                book.isbn,
                publisher);
    }


    private static class Visitor {
        String name;
        String surname;
        String phone;
        boolean subscribed;
        Book[] favoriteBooks;
    }

    private static class Book {
        String name;
        String author;
        int publishingYear;
        String isbn;
        String publisher;
    }
}