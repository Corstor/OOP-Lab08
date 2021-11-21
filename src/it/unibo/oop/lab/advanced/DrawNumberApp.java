package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
        this.initialize();
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    private void displayError(final String err) {
        view.displayError(err);
    }

    private void initialize() {
        try (var contents = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("config.yml")))) {
            for (var configLine = contents.readLine(); configLine != null; configLine = contents.readLine()) {
               final var line = new StringTokenizer(configLine);
                if (line.countTokens() == 2) {
                    final String next = line.nextToken();
                    if (next.contains("max")) {
                        this.max = Integer.parseInt(line.nextToken());
                    }
                    if (next.contains("min")) {
                        this.min = Integer.parseInt(line.nextToken());
                    }
                    if (next.contains("attempts")) {
                        this.attempts = Integer.parseInt(line.nextToken());
                    }
                } else {
                    displayError("There must be a problem in the file");
                }
            }
        } catch (IOException e) {
            displayError(e.getMessage());
        }
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
