package com.watsmeow.DVDLibrary.dao;

import com.watsmeow.DVDLibrary.dto.DVD;

import java.io.*;
import java.util.*;

public class LibraryDaoFileImpl implements LibraryDao {

    private static final String LIBRARY_FILE = "library.txt";
    private static final String DELIMITER = "::";

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) throws LibraryDaoException {
            loadLibrary();
            DVD newDVD = dvds.put(title, dvd);
            writeRoster();
            return newDVD;
    }
    public DVD removeDVD(String title) throws LibraryDaoException {
        loadLibrary();
        DVD removedDVD = dvds.remove(title);
        writeRoster();
        return removedDVD;
    }
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
    @Override
    public DVD getDVD(String title) throws LibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }
    @Override
    public List<DVD> getAllDVDs() throws LibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAARating(dvdTokens[2]);
        dvdFromFile.setDirector(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserNotes(dvdTokens[5]);
        return dvdFromFile;
    }

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

        String currentLine;

        DVD currentDVD;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD((currentLine));

            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private String marshallDVD(DVD dvd) {
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getMPAARating() + DELIMITER;
        dvdAsText += dvd.getDirector() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getUserNotes();
        return dvdAsText;
    }

    private void writeRoster() throws LibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new LibraryDaoException("Could not save data.", e);
        }

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
