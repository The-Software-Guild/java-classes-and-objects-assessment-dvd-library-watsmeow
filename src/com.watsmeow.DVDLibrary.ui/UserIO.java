package com.watsmeow.DVDLibrary.ui;

public interface UserIO {
    void print(String msg);

    int readSelection(String msg, int min, int max);

    String readString(String msg);
}
