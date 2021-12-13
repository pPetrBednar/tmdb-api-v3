package io.github.ppetrbednar.tmdb.wrappers.image;

/**
 * Selector for supported cover sizes.
 *
 * @author Petr Bednář
 */
public enum BackdropSize implements ICoverSize {
    W300("w300"),
    W780("w780"),
    W1280("w1280"),
    ORIGINAL("original");

    private final String command;

    private BackdropSize(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

}
