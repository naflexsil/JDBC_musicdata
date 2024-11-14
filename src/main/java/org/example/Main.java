package org.example;

import org.example.music.CreateMusicDatabase;
import org.example.music.DeleteMusicDatabase;
import org.example.music.Task1;
import org.example.music.Task2;
import org.example.music.Task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            boolean running = true;

            while (running) {
                System.out.println("task (1 - 3), 'create music' or 'delete music' or 'exit' or 'back': ");
                String taskNumber = scanner.nextLine();

                switch (taskNumber) {
                    case "1":
                        Task1.fetchAllMusic();
                        break;
                    case "2":
                        Task2.fetchMusicWithoutMAndT();
                        break;
                    case "3":
                        Task3.addFavoriteSong();
                        break;
                    case "create music":
                        CreateMusicDatabase.initializeDatabase();
                        break;
                    case "delete music":
                        DeleteMusicDatabase.deleteMusicTable();
                        break;
                    case "back":
                        System.out.println("choose: ");
                        break;
                    case "exit":
                        running = false;
                        System.out.println("bye-bye");
                        break;
                    default:
                        System.out.println("Error: Invalid option.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
