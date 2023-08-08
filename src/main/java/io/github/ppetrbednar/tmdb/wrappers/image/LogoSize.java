package io.github.ppetrbednar.tmdb.wrappers.image;

/**
 * Selector for supported cover sizes.
 *
 * @author Petr Bednář
 */
public enum LogoSize implements ICoverSize {
    W45("w45"),
    W92("w92"),
    W154("w154"),
    W185("w185"),
    W300("w300"),
    W500("w500"),
    ORIGINAL("original");

    private final String command;

    private LogoSize(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

}
