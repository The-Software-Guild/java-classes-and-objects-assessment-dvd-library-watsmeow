package com.watsmeow.DVDLibrary.dao;

import com.watsmeow.DVDLibrary.dto.DVD;

import java.util.List;

public interface LibraryDao {

    DVD addDVD(String title, DVD dvd) throws LibraryDaoException;

    DVD removeDVD(String title) throws LibraryDaoException;

    void editDVD(String title, DVD dvd) throws LibraryDaoException;

    DVD getDVD(String title) throws LibraryDaoException;

    List<DVD> getAllDVDs() throws LibraryDaoException;


}
