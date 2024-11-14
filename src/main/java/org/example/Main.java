package org.example;

import org.example.music.CreateMusicDatabase;
import org.example.music.Task1;
import org.example.music.Task2;
import org.example.music.Task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreateMusicDatabase.initializeDatabase();

        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            System.out.println("number task 1 - 8: ");
            int taskNumber = scanner.nextInt();
            switch (taskNumber) {
                case 1:
                    Task1.fetchAllMusic();
                    break;
                case 2:
                    Task2.fetchMusicWithoutMAndT();
                    break;
                case 3:
                    Task3.addFavoriteSong();
                    break;
                default:
                    System.out.println("error in number task");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}