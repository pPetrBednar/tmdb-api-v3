package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 *
 * @author Petr Bednář
 */
public class Genre {

    private final double id;
    private final String name;

    public Genre(JsonObject json) {
        id = Convertor.convertDouble(json.get("id"));
        name = Convertor.convertString(json.get("name"));
    }

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
