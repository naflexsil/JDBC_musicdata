package org.example.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Task3 {
    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void addFavoriteSong() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String insertSQL = "INSERT INTO study.music (id, name) VALUES (?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setInt(1, 21);
                preparedStatement.setString(2, "Machucar");

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("music 'Machucar' add");
                } else {
                    System.out.println("error for add music");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}