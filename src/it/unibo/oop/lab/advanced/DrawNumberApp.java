package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views all the views that the program will manage
     */
    public DrawNumberApp(final DrawNumberView... views) {
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final var view : this.views) {
            view.setObserver(this); 
            view.start();
        }
        this.initialize();
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final var view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final var view : this.views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final var view : this.views) {
                view.limitsReached();
            }
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
        for (final var view : this.views) {
            view.displayError(err);
        }
    }

    void initialize() {
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
     * @throws FileNotFoundException if the file is not found
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(), new PrintStreamView(System.out), 
                          new PrintStreamView("output.log"), new DrawNumberViewImpl());
    }

}
