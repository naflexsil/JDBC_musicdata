package org.example.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateMusicDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String createSchemaSQL = "CREATE SCHEMA IF NOT EXISTS study;";
            statement.execute(createSchemaSQL);

            String createTableSQL = "CREATE TABLE IF NOT EXISTS study.music (" +
                    "id INT PRIMARY KEY," +
                    "name TEXT NOT NULL);";
            statement.execute(createTableSQL);

            String insertDataSQL = "INSERT INTO study.music (id, name) " +
                    "SELECT * FROM (VALUES " +
                    "(1, 'Bohemian Rhapsody')," +
                    "(2, 'Stairway to Heaven')," +
                    "(3, 'Imagine')," +
                    "(4, 'Sweet Child O Mine')," +
                    "(5, 'Hey Jude')," +
                    "(6, 'Hotel California')," +
                    "(7, 'Billie Jean')," +
                    "(8, 'Wonderwall')," +
                    "(9, 'Smells Like Teen Spirit')," +
                    "(10, 'Let It Be')," +
                    "(11, 'I Want It All')," +
                    "(12, 'November Rain')," +
                    "(13, 'Losing My Religion')," +
                    "(14, 'One')," +
                    "(15, 'With or Without You')," +
                    "(16, 'Sweet Caroline')," +
                    "(17, 'Yesterday')," +
                    "(18, 'Dont Stop Believin')," +
                    "(19, 'Crazy Train')," +
                    "(20, 'Always')) AS new_data " +
                    "WHERE NOT EXISTS (SELECT 1 FROM study.music);";

            statement.execute(insertDataSQL);
            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createMusicTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS study.music (" +
                    "id INT PRIMARY KEY," +
                    "name TEXT NOT NULL);";
            statement.execute(createTableSQL);
            System.out.println("Music table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
