package io.github.ppetrbednar.tmdb.wrappers.image;

/**
 * Selector for supported cover sizes.
 *
 * @author Petr Bednář
 */
public enum StillSize implements ICoverSize {
    W92("w92"),
    W185("w185"),
    W300("w300"),
    ORIGINAL("original");

    private final String command;

    private StillSize(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

}
