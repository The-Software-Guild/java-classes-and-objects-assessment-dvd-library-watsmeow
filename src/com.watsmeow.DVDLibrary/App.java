package com.watsmeow.DVDLibrary;

import com.watsmeow.DVDLibrary.controller.LibraryController;
import com.watsmeow.DVDLibrary.dao.LibraryDao;
import com.watsmeow.DVDLibrary.dao.LibraryDaoFileImpl;
import com.watsmeow.DVDLibrary.ui.LibraryView;
import com.watsmeow.DVDLibrary.ui.UserIO;
import com.watsmeow.DVDLibrary.ui.UserIOConsoleImpl;

public class App {

    // Main entry point of the program, instantiates the classes and interfaces being used and activates the run method
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        LibraryView view = new LibraryView(io);
        LibraryDao dao = new LibraryDaoFileImpl();
        LibraryController controller = new LibraryController(dao, view);
        controller.run();
    }
}
