package com.watsmeow.DVDLibrary.ui;

import com.watsmeow.DVDLibrary.dto.DVD;
import java.util.List;

public class LibraryView {

    // Instantiating the user interface
    private UserIO io;

    // Library constructor establishing that this class will be using the user interface as it's interface
    public LibraryView(UserIO io) {
        this.io = io;
    }

    // Prints out the main menu to the user
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

    // Gets user information in order to create a new DVD object
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

    // Uses print method from io to print out the prompt to add a dvd
    public void displayCreateDVDBanner() {
        io.print("ADD A DVD");
    }

    // Uses readString method from io and prints out a display confirming success
    public void displayCreateSuccessBanner() {
        io.readString("ADDED DVD SUCCESSFULLY. PRESS ENTER TO CONTINUE.");
    }

    // Formats dvd list info into a string and displays entire library to user
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

    // Banner that is used when user wants to display the whole library
    public void displayDisplayListBanner() {
        io.print("DISPLAY ALL DVDS");
    }

    // Banner used when user wants to find a specific dvd by its title
    public void displayDVDBanner() {
        io.print("FIND A DVD BY TITLE");
    }

    // Uses readString method to take in the dvd title from the user
    public String getDVDTitleFromUser() {
        return io.readString("Enter the movie title");
    }

    // Displays all information pertaining to a single dvd object if a dvd by that title exists in memory
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

    // Displays the banner for removing a dvd from the library
    public void displayRemoveDVDBanner() {
        io.print("REMOVE A DVD");
    }

    /*Uses print and readString methods from io to confirm removal of a dvd, or to tell the user no dvd by that title
    * exists in the library
    */
    public void displayRemovedDVD(DVD DVDRecord) {
        if (DVDRecord != null) {
            io.print("DVD successfully removed from library.");
        } else {
            io.print("No movies by that title in your library.");
        }
        io.readString("Press enter to continue");
    }

    // Displays the banner to edit a dvd
    public void displayEditDVDBanner() {
        io.print("EDIT DVD BY ENTERING TITLE");
    }

    // Provides the user with a menu from which to edit specific fields of a dvd object and takes in their edits
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

    // Displays banner that edits were successful
    public void displayEditSuccessBanner() {
        io.print("EDIT SUCCESSFUL");
    }

    // Displays exit banner
    public void displayExitBanner() {
        io.print("Good bye");
    }

    // Displays error banner
    public void displayErrorBanner() {
        io.print("Unknown command");
    }

    // Displays error message
    public void displayErrorMessage(String errorMsg) {
        io.print("ERROR");
        io.print(errorMsg);
    }

    // Displays if a dvd title, and therefore a dvd, exists or not in the library
    public void searchLibrary(boolean exists) {
        if (exists) {
            io.print("This dvd exists in your library.");
        } else {
            io.print("No dvd by that title exists in your library.");
        }
    }

    // Displays that the requested dvd does not exist
    public void displayDoesNotExistBanner() {
        io.print("No DVD by that title in your library.");
    }
}
