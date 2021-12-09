package io.github.ppetrbednar.tmdb.api;

/**
 *
 * @author Petr Bednář
 */
public enum Localization {
    EN_US("en-US");

    private final String language;

    private Localization(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

}
