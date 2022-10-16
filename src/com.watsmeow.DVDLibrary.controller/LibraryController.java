package com.watsmeow.DVDLibrary.controller;

import com.watsmeow.DVDLibrary.ui.LibraryView;
import com.watsmeow.DVDLibrary.ui.UserIO;
import com.watsmeow.DVDLibrary.ui.UserIOConsoleImpl;

public class LibraryController {
    private UserIO io = new UserIOConsoleImpl();
    private LibraryView view = new LibraryView();
    public void run() {
        boolean keepRunning = true;
        int userSelection = 0;

        while (keepRunning) {

            userSelection = getUserSelection();

            switch (userSelection) {
                case 1:
                    io.print("Add a DVD");
                    break;
                case 2:
                    io.print("Remove a DVD");
                    break;
                case 3:
                    io.print("Edit a DVD");
                    break;
                case 4:
                    io.print("View all DVDs");
                    break;
                case 5:
                    keepRunning = false;
                    break;
                default:
                    io.print("Invalid selection");
            }
        }
        io.print("PEACE OUT.");
    }

    private int getUserSelection() {
        return view.printOptionsGetUserSelection();
    }
}