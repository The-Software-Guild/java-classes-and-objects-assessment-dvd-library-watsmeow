package com.watsmeow.DVDLibrary.dao;

import com.watsmeow.DVDLibrary.dto.DVD;
import java.io.*;
import java.util.*;

public class LibraryDaoFileImpl implements LibraryDao {

    // Assigning the library.txt file into a final variable
    private static final String LIBRARY_FILE = "library.txt";

    // Defining the delimiter used in the txt tile
    private static final String DELIMITER = "::";

    // Declaring a hashmap to store the dvd collection in memory
    private Map<String, DVD> dvds = new HashMap<>();

    // Method that adds a single DVD object to memory and to the file
    @Override
    public DVD addDVD(String title, DVD dvd) throws LibraryDaoException {
            loadLibrary();
            DVD newDVD = dvds.put(title, dvd);
            writeRoster();
            return newDVD;
    }

    // Method that removes a single DVD object from memory and the file
    public DVD removeDVD(String title) throws LibraryDaoException {
        loadLibrary();
        DVD removedDVD = dvds.remove(title);
        writeRoster();
        return removedDVD;
    }

    // Method that allows user to edit a single DVD object by retrieving it using its title
    @Override
    public void editDVD(String title, DVD dvd) throws LibraryDaoException {
        loadLibrary();
        if (title == dvd.getTitle()) {
            DVD editedDVD = dvds.put(title, dvd);
        } else {
            dvds.remove(title);
            DVD editedDVD = dvds.put(dvd.getTitle(), dvd);
        }
        writeRoster();
    }

    // Method that allows user to fetch a single dvd from memory and the file
    @Override
    public DVD getDVD(String title) throws LibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    /*Method that allows user to enter a dvd title and search the library to see if it exists
    * @return a boolean that says whether the dvd exists or not
    */
    @Override
    public boolean searchLibrary(String title) throws LibraryDaoException {
        loadLibrary();
        return dvds.containsKey(title);
    }

    // Method that allows user to fetch all the dvds in the collection from memory and the file
    @Override
    public List<DVD> getAllDVDs() throws LibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    // Gets library from library.txt file
    private DVD unmarshallDVD(String dvdAsText) {
        // Splits the individual dvd info fields along the ::s
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        // Title is at 0th index of array
        String title = dvdTokens[0];
        // Use the above info above and below to create a new dvd object using the dvd constructor
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAARating(dvdTokens[2]);
        dvdFromFile.setDirector(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserNotes(dvdTokens[5]);
        // Return the dvd object
        return dvdFromFile;
    }

    // Loads the library.txt file into the hashmap
    private void loadLibrary() throws LibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)
                    )
            );
        } catch (FileNotFoundException e) {
            throw new LibraryDaoException("Could not load library into memory.", e);
        }

        // Holds the most recent line read from the file
        String currentLine;
        // Go through the library.txt line by line, decode each line into an object using unmarshall method
        DVD currentDVD;
        // Continue until all lines have been decoded
        while (scanner.hasNextLine()) {
            // Get next line
            currentLine = scanner.nextLine();
            // Unmarshall the line into a dvd object
            currentDVD = unmarshallDVD((currentLine));
            // Put current dvd into the dvds hashmap using title as key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        //close out the scanner
        scanner.close();
    }

    // Marshall the dvd hashmap into the file for storage and later retrieval
    private String marshallDVD(DVD dvd) {
        // Converts each dvd object into a string delimited by :: and returns the string
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getMPAARating() + DELIMITER;
        dvdAsText += dvd.getDirector() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getUserNotes();
        return dvdAsText;
    }

    /*
    * Writes all dvds in the library to the file based on the loadLibrary file format.
    * @throws LibraryDaoException if an error occurs writing to the file
    * */
    private void writeRoster() throws LibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new LibraryDaoException("Could not save data.", e);
        }
        // Writes out the dvd objects to the library file
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
