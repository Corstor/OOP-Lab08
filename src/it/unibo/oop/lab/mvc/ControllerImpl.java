package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> history;
    private String string; 

    public ControllerImpl() {
        this.string = null;
        this.history = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStringToPrint(final String string) {
        if (string != null) {
            this.string = string;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStringToPrint() {
        return this.string;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return new LinkedList<>(this.history);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        if (this.string != null) {
            System.out.println(this.string);
            this.history.add(this.string);
        } else {
            throw new IllegalStateException();
        }
    }

}
