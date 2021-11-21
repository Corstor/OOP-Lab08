package it.unibo.oop.lab.mvc;

import java.util.List;

/**
 * A controller that prints strings and has memory of the strings it printed.
 */
public interface Controller {

    /*
     * This interface must model a simple controller responsible of I/O access. It
     * considers only the standard output, and it is able to print on it.
     */
    /**
     * A method for setting the next string to print. Null values are not
     * acceptable, and an exception should be produced
     * 
     * @param string
     *              the next string to print
     *              
     * @throws IllegalArgumentException if the argument is null
     */
    void setStringToPrint(String string) throws IllegalArgumentException;

    /**
     * A method for getting the next string to print
     * 
     * @return the string to print
     */
    String getStringToPrint();

    /** 
     * A method for getting the history of the printed strings (in form of a List
     * of Strings)
     * 
     * @return the list of strings that have been printed before
     */
    List<String> getHistory();

    /**
     * A method that prints the current string. If the current string is unset,
     * an IllegalStateException should be thrown
     * 
     * @throws IllegalStateException if the current string is null
     */
    void print() throws IllegalStateException;

}
