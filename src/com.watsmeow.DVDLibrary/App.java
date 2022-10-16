package com.watsmeow.DVDLibrary;

import com.watsmeow.DVDLibrary.controller.LibraryController;

public class App {
    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        controller.run();
    }
}
