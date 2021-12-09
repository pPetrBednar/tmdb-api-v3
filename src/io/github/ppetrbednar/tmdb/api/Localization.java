package io.github.ppetrbednar.tmdb.api;

/**
 * Selector for supported output languages.
 *
 * @author Petr Bednář
 */
public enum Localization {
    EN_US("en-US"),
    CS_CZ("cs-CZ");

    private final String language;

    private Localization(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

}
