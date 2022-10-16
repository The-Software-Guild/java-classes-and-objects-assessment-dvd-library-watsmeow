package com.watsmeow.DVDLibrary.ui;


import com.watsmeow.DVDLibrary.dto.DVD;

public class LibraryView {

    private UserIO io = new UserIOConsoleImpl();

    public int printOptionsGetUserSelection() {

        io.print("Main Menu");
        io.print("1. Add a DVD to Library");
        io.print("2. Remove a DVD from the Library");
        io.print("3. Edit an existing DVD in the Library");
        io.print("4. View Library");
        io.print("5. Exit");

        return io.readSelection("Select from the following choices:", 1, 5);
    }
}
