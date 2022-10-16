package com.watsmeow.DVDLibrary.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    public void print(String msg) {
        System.out.println(msg);
    }

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
}
