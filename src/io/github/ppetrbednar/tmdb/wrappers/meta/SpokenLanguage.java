package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import io.github.ppetrbednar.tmdb.wrappers.language.ISO_639;

/**
 *
 * @author Petr Bednář
 */
public class SpokenLanguage {

    private final String englishName;
    private final ISO_639 iso_639;
    private final String name;

    public SpokenLanguage(JsonObject json) {
        englishName = Convertor.convertString(json.get("english_name"));
        iso_639 = new ISO_639(Convertor.convertString(json.get("iso_639_1")));
        name = Convertor.convertString(json.get("name"));
    }

    public String getEnglishName() {
        return englishName;
    }

    public ISO_639 getIso_639() {
        return iso_639;
    }

    public String getName() {
        return name;
    }

}
