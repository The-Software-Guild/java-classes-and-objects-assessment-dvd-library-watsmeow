package com.watsmeow.DVDLibrary.dao;

import com.watsmeow.DVDLibrary.dto.DVD;

import java.util.List;

public interface LibraryDao {
    /**
     * Adds a dvd to the library and associates it with the given title. If the title already exists it returns
     * the dvd object, otherwise it returns null.
     * @param title is the dvd info is associated with
     * @param dvd is the object being added
     * @return returns the DVD object already associated the title if one exists, if not returns null
    */
    DVD addDVD(String title, DVD dvd) throws LibraryDaoException;

    /**
     * Removes a dvd from the library using the given title.
     * @param title is the dvd info is associated with
     * @return returns the DVD object already associated the title if one exists, if not returns null
     */
    DVD removeDVD(String title) throws LibraryDaoException;

    /**
     * User inputs the title of the dvd they want to edit and then can proceed to edit its information
     * if the dvd exists. If it does not exist the user is notified that no such dvd exists in the library.
     * @param title is the dvd info is associated with
     * @param dvd is the object being edited
     */
    void editDVD(String title, DVD dvd) throws LibraryDaoException;

    /**
     * Returns a single dvd from the library using title
     * @return returns the DVD object already associated the title if one exists
     */
    DVD getDVD(String title) throws LibraryDaoException;

    /**
     * Returns a list of all dvds in the library
     * @return List containing all dvds in library
     */
    List<DVD> getAllDVDs() throws LibraryDaoException;

    /**
     * Returns if a dvd exists in the library or not using the title
     * @return boolean to indicate existence
     */
    boolean searchLibrary(String title) throws LibraryDaoException;
}
