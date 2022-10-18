package com.watsmeow.DVDLibrary.ui;

// Public interface that defines what methods are used for communicating with the user
public interface UserIO {

    /**
     * Takes in a message/text that the user needs in order to move through the program and
     * displays the message to the user to they can act appropriately
     * @param msg is the message/text that will be displayed to the user
     */
    void print(String msg);

    /**
     * Reads the user's selection of menu items and passes on that input so that the program can act
     * appropriately based on the user's desired action
     * @param msg is the message/text/options that will be displayed to the user
     * @params min and max define the selection options available to the user
     */
    int readSelection(String msg, int min, int max);

    /**
     * Reads the user's input when they submit strings such as dvd titles or when they are editing
     * specific fields of a dvd object
     * @param msg is the message/text that is accepted from the user
     */
    String readString(String msg);
}
