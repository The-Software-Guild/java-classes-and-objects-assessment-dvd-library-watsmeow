package com.watsmeow.DVDLibrary.controller;

import com.watsmeow.DVDLibrary.dao.LibraryDao;
import com.watsmeow.DVDLibrary.dao.LibraryDaoException;
import com.watsmeow.DVDLibrary.dao.LibraryDaoFileImpl;
import com.watsmeow.DVDLibrary.dto.DVD;
import com.watsmeow.DVDLibrary.ui.LibraryView;
import com.watsmeow.DVDLibrary.ui.UserIO;
import com.watsmeow.DVDLibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class LibraryController {
    private LibraryView view;
    private LibraryDao library;

    //constructor
    public LibraryController(LibraryDao dao, LibraryView view) {
        this.library = dao;
        this.view = view;
    }
    public void run() {
        boolean keepRunning = true;
        int userSelection = 0;

        try {
            while (keepRunning) {

                userSelection = getUserSelection();

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
            exitMessage();
        } catch (LibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getUserSelection() {
        return view.printOptionsGetUserSelection();
    }

    private void createDVD() throws LibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        library.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws LibraryDaoException{
        view.displayDisplayListBanner();
        List<DVD> dvdList = library.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws LibraryDaoException{
        view.displayDVDBanner();
        String title = view.getDVDTitleFromUser();
        DVD dvd = library.getDVD(title);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws LibraryDaoException{
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleFromUser();
        DVD removedDVD = library.removeDVD(title);
        view.displayRemovedDVD(removedDVD);
    }

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

    private void searchDVDs() throws LibraryDaoException {
        view.displayDVDBanner();
        String title = view.getDVDTitleFromUser();
        boolean DVDExists = library.searchLibrary(title);
        view.searchLibrary(DVDExists);
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    private void unknownCommand() {
        view.displayErrorBanner();
    }
}