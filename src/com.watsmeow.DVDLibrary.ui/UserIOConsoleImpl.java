package com.watsmeow.DVDLibrary.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    // Prints out messages and options to the user
    public void print(String msg) {
        System.out.println(msg);
    }

    // Reads a user's menu selection
    public int readSelection(String msg, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        int userInput = min - 1;
        while (userInput < min || userInput > max) {
            userInput = scanner.nextInt();
            if (userInput < min || userInput > max) {
                System.out.println("Invalid input");
            }
        }
        return userInput;
    }

    // Read's a user's string input for titles and inputting/editing DVD info
    @Override
    public String readString(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        String userInput = scanner.nextLine();
        return userInput;
    }
}
