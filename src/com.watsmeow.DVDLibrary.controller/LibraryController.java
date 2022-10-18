package com.watsmeow.DVDLibrary.controller;

import com.watsmeow.DVDLibrary.dao.LibraryDao;
import com.watsmeow.DVDLibrary.dao.LibraryDaoException;
import com.watsmeow.DVDLibrary.dto.DVD;
import com.watsmeow.DVDLibrary.ui.LibraryView;
import java.util.List;

public class LibraryController {

    // Instantiating the LibraryView
    private LibraryView view;

    // Instantiating the LibraryDao
    private LibraryDao library;

    /* Controller constructor that takes in the dao and view as parameters
    * so that it is working with the appropriate classes
    * */
    public LibraryController(LibraryDao dao, LibraryView view) {
        this.library = dao;
        this.view = view;
    }

    // Method to run the application
    public void run() {
        boolean keepRunning = true;
        int userSelection = 0;

        // Places the running into a try and catch so that if there is an error it alerts properly
        try {
            while (keepRunning) {

                userSelection = getUserSelection();

                // Switch statement calls methods that run based on user selection
                switch (userSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        viewDVD();
                        break;
                    case 5:
                        listDVDs();
                        break;
                    case 6:
                        searchDVDs();
                        break;
                    case 7:
                        keepRunning = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            // Display the exit message if user exits program
            exitMessage();
        } catch (LibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    // @return the user's selection from the main menu options
    private int getUserSelection() {
        return view.printOptionsGetUserSelection();
    }

    // Takes in new dvd info from user, saves it to the LibraryDao
    private void createDVD() throws LibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        library.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    // Gets all the DVDs from LibraryDao and displays them
    private void listDVDs() throws LibraryDaoException{
        view.displayDisplayListBanner();
        List<DVD> dvdList = library.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    // Takes in the title of a single DVD from the user/LibraryView, gets it from LibraryDao, displays it
    private void viewDVD() throws LibraryDaoException{
        view.displayDVDBanner();
        String title = view.getDVDTitleFromUser();
        DVD dvd = library.getDVD(title);
        view.displayDVD(dvd);
    }

    // Takes in the title of a single DVD from the user/LibraryView, deletes it from LibraryDao
    private void removeDVD() throws LibraryDaoException{
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleFromUser();
        DVD removedDVD = library.removeDVD(title);
        view.displayRemovedDVD(removedDVD);
    }

    /* Takes in the title of a dvd from LibraryView, gets it from memory, if the dvd exists then allow the user to
    * edit it and overwrite the existing object in memory, then display a success message, if the dvd (title)
    * doesn't already exist in the library then alert the user
    */
    private void editDVD() throws LibraryDaoException {
        view.displayEditDVDBanner();
        String title = view.getDVDTitleFromUser();
        DVD dvd = library.getDVD(title);
        if (dvd != null) {
            DVD editedDVD = view.editDVD(dvd);
            library.editDVD(title, editedDVD);
            view.displayEditSuccessBanner();
        } else {
            view.displayDoesNotExistBanner();
        }
    }

    // @return if the user provided title exists in the library or not
    private void searchDVDs() throws LibraryDaoException {
        view.displayDVDBanner();
        String title = view.getDVDTitleFromUser();
        boolean DVDExists = library.searchLibrary(title);
        view.searchLibrary(DVDExists);
    }

    // Display exit message when user exits the program
    private void exitMessage() {
        view.displayExitBanner();
    }

    // Displays error message if user selects an option that doesn't exist in the menu
    private void unknownCommand() {
        view.displayErrorBanner();
    }
}