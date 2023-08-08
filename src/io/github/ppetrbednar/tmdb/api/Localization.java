package io.github.ppetrbednar.tmdb.api;

/**
 * Selector for supported output languages.
 *
 * @author Petr Bednář
 */
public enum Localization {

    EN_US("en-US"),
    CS_CZ("cs-CZ");

    public final String localization;

    Localization(String localization) {
        this.localization = localization;
    }

    public static Localization getValueOf(String localization) {
        for (Localization l : Localization.values()) {
            if (l.localization.equals(localization)) {
                return l;
            }
        }
        return null;
    }

}
