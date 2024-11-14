package org.example.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMusicDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void deleteMusicTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String deleteTableSQL = "DROP TABLE IF EXISTS study.music;";
            statement.execute(deleteTableSQL);
            System.out.println("Music table deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}