package com.watsmeow.DVDLibrary.dao;

// This class is used to appropriately throw errors and provide quality error information
public class LibraryDaoException extends Exception{

    public LibraryDaoException(String msg) {
        super(msg);
    }

    public LibraryDaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
