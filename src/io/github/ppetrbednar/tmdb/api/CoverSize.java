package io.github.ppetrbednar.tmdb.api;

/**
 * Selector for supported cover sizes.
 *
 * @author Petr Bednář
 */
public enum CoverSize {
    W500("w500"),
    ORIGINAL("original");

    private final String command;

    private CoverSize(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
