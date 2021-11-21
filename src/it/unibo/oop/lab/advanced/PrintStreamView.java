package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * This class implements a view that can write on any PrintStream (so both file or stdout).
 */
public class PrintStreamView implements DrawNumberView {

    private final PrintStream stream;

    /**
     * @param stream the {@link PrintStream} where to write
     */
    public PrintStreamView(final PrintStream stream) {
        this.stream = stream;
    }

    /**
     * Builds a {@link PrintStreamView} that writes on file, given a path.
     * 
     * @param path a file path
     * @throws FileNotFoundException 
     */
    public PrintStreamView(final String path) throws FileNotFoundException {
        this.stream = new PrintStream(new FileOutputStream(new File(path)));
    }

    /**
     * This method won't do anything if called by an object of this class.
     */
    @Override
    public void setObserver(final DrawNumberViewObserver observer) {

    }

    /**
     * This method won't do anything if called by an object of this class.
     */
    @Override
    public void start() {

    }

    @Override
    public void numberIncorrect() {
        stream.println("Enter a correct number");
    }

    @Override
    public void result(final DrawResult res) {
        stream.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        stream.println("You lost: a new game starts!");
    }

    @Override
    public void displayError(final String message) {
        stream.println("An error occurred: " + message);
    }

}
