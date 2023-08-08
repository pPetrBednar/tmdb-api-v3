package io.github.ppetrbednar.tmdb.wrappers.language;

/**
 * Wrapper for ISO 639 languages.
 *
 * @author Petr Bednář
 */
public class ISO_639 {

    private final ISO_639_1 iso1;
    private final ISO_639_2 iso2;

    public ISO_639(String lang) {
        iso1 = ISO_639_1.getValueOf(lang);
        iso2 = ISO_639_2.getValueOf(lang);
    }

    public ISO_639() {
        iso1 = ISO_639_1.UNKNOWN;
        iso2 = ISO_639_2.UNKNOWN;
    }

    public String getShortcut() {

        if (iso1 != ISO_639_1.UNKNOWN) {
            return iso1.name();
        }

        if (iso2 != ISO_639_2.UNKNOWN) {
            return iso2.name();
        }

        return ISO_639_1.UNKNOWN.name();
    }

    public String getLanguage() {

        if (iso1 != ISO_639_1.UNKNOWN) {
            return iso1.getLanguage();
        }

        if (iso2 != ISO_639_2.UNKNOWN) {
            return iso2.getLanguage();
        }

        return ISO_639_1.UNKNOWN.getLanguage();
    }
}
