package org.example;

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
                default:
                    System.out.println("error in number task");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}