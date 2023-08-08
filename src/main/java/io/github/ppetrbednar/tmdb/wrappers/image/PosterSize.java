package io.github.ppetrbednar.tmdb.wrappers.image;

/**
 * Selector for supported cover sizes.
 *
 * @author Petr Bednář
 */
public enum PosterSize implements ICoverSize {
    W92("w92"),
    W154("w154"),
    W185("w185"),
    W342("w342"),
    W500("w500"),
    W780("w780"),
    ORIGINAL("original");

    private final String command;

    private PosterSize(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

}
