package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


/**
 * 
 */
public class Controller {

    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "output.txt";
    private File file;

    /* 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    public Controller() {
        file = new File(PATH);
    }

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * 1) A method for setting a File as current file
     */
    /**
     * @param file 
     *             file to be set as current file
     */
    public void setFile(final File file) {
        if (file.getParentFile().exists()) {
            this.file = file;
        } else {
            throw new IllegalArgumentException("Cannot write in a non-existing folder.");
        }
    }

    /*
     * 2) A method for getting the current File
     */
    /**
     * @return the current file
     */
    public File getFile() {
        return this.file;
    }

    /* 
     * 3) A method for getting the path (in form of String) of the current File
     */
    /**
     * @return the path of the current file
     */
    public String getPath() {
        return this.file.toPath().toString();
    }

    /*
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     */
    /**
     * @param inputString 
     *                    String to write on the current file
     * 
     * @throws IOException
     */
    public void writeLine(final String inputString) throws IOException {
        try (PrintStream ps = new PrintStream(file)) {
            ps.print(inputString);
        }
    }

}
