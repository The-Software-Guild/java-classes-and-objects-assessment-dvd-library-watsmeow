package com.watsmeow.DVDLibrary.dao;

public class LibraryDaoException extends Exception{

    public LibraryDaoException(String msg) {
        super(msg);
    }

    public LibraryDaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
