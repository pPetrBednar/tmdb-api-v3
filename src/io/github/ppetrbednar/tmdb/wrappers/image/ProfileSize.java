package io.github.ppetrbednar.tmdb.wrappers.image;

/**
 * Selector for supported cover sizes.
 *
 * @author Petr Bednář
 */
public enum ProfileSize implements ICoverSize {
    W45("w45"),
    W185("w185"),
    W632("w632"),
    ORIGINAL("original");

    private final String command;

    private ProfileSize(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

}
