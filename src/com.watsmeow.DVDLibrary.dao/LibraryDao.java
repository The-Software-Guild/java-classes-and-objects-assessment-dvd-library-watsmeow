package com.watsmeow.DVDLibrary.dao;

import com.watsmeow.DVDLibrary.dto.DVD;

import java.util.List;

public interface LibraryDao {

    DVD addDVD(String title, DVD dvd);

    DVD deleteDVD(String title);

    DVD editDVD(String title, DVD dvd);

    List<DVD> getAllDVDs();

    DVD getDVD(String title);
}
