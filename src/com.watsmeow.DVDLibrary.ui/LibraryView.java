package com.watsmeow.DVDLibrary.ui;


import com.watsmeow.DVDLibrary.dto.DVD;

import java.util.List;

public class LibraryView {

    private UserIO io;

    //constructor
    public LibraryView(UserIO io) {
        this.io = io;
    }

    public int printOptionsGetUserSelection() {

        io.print("Main Menu");
        io.print("1. Add a DVD to Library");
        io.print("2. Remove a DVD from the Library");
        io.print("3. Edit an existing DVD in the Library");
        io.print("4. View a DVD");
        io.print("5. View Library");
        io.print("6. Search if a dvd exists");
        io.print("7. Exit");

        return io.readSelection("Select from the following choices:", 1, 7);
    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Enter DVD title");
        String releaseDate = io.readString("Enter movie release date");
        String MPAARating = io.readString("Enter the MPAA Rating");
        String director = io.readString("Enter the movie director");
        String studio = io.readString("Enter movie studio");
        String userNotes = io.readString("Enter any notes you'd like to add about the movie");

        DVD currentDVD = new DVD(title);

        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserNotes(userNotes);

        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("ADD A DVD");
    }

    public void displayCreateSuccessBanner() {
        io.readString("ADDED DVD SUCCESSFULLY. PRESS ENTER TO CONTINUE.");
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            String DVDInfo = String.format("%s : %s, %s, %s, %s, %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMPAARating(),
                    currentDVD.getDirector(),
                    currentDVD.getStudio(),
                    currentDVD.getUserNotes());
            io.print(DVDInfo);
        }
        io.readString("Press enter to continue.");
    }

    public void displayDisplayListBanner() {
        io.print("DISPLAY ALL DVDS");
    }

    public void displayDVDBanner() {
        io.print("FIND A DVD BY TITLE");
    }

    public String getDVDTitleFromUser() {
        return io.readString("Enter the movie title");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMPAARating());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getUserNotes());
        } else {
            io.print("No movies by that title in your library.");
        }
        io.readString("Press enter to continue");
    }

    public void displayRemoveDVDBanner() {
        io.print("REMOVE A DVD");
    }

    public void displayRemovedDVD(DVD DVDRecord) {
        if (DVDRecord != null) {
            io.print("DVD successfully removed from library.");
        } else {
            io.print("No movies by that title in your library.");
        }
        io.readString("Press enter to continue");
    }

    public void displayEditDVDBanner() {
        io.print("EDIT DVD BY ENTERING TITLE");
    }

    public DVD editDVD(DVD dvd) {
        boolean keepRunning = true;
        while (keepRunning) {
            io.print("1: " + dvd.getTitle());
            io.print("2: " + dvd.getReleaseDate());
            io.print("3: " + dvd.getMPAARating());
            io.print("4: " + dvd.getDirector());
            io.print("5: " + dvd.getStudio());
            io.print("6: " + dvd.getUserNotes());
            io.print("7: Exit");
            int userSelection = io.readSelection("Select the number of the info field you want to edit or " +
                    "exit to return to main menu:", 1, 7);
            if (userSelection == 7) {
                keepRunning = false;
                break;
            }
            String userEdit = io.readString("Enter your edits here.");

            switch (userSelection) {
                case 1:
                    dvd.setTitle(userEdit);
                    break;
                case 2:
                    dvd.setReleaseDate(userEdit);
                    break;
                case 3:
                    dvd.setMPAARating(userEdit);
                    break;
                case 4:
                    dvd.setDirector(userEdit);
                    break;
                case 5:
                    dvd.setStudio(userEdit);
                    break;
                case 6:
                    dvd.setUserNotes(userEdit);
                    break;
                case 7:
                    keepRunning = false;
                    break;
                default:
                    io.print("Invalid selection");
            }
        }
        return dvd;
    }
    public void displayEditSuccessBanner() {
        io.print("EDIT SUCCESSFUL");
    }

    public void displayExitBanner() {
        io.print("Good bye");
    }

    public void displayErrorBanner() {
        io.print("Unknown command");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("ERROR");
        io.print(errorMsg);
    }

    public void searchLibrary(boolean exists) {
        if (exists) {
            io.print("This dvd exists in your library.");
        } else {
            io.print("No dvd by that title exists in your library.");
        }
    }

    public void displayDoesNotExistBanner() {
        io.print("No DVD by that title in your library.");
    }

}
