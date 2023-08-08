package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for genre.
 *
 * @author Petr Bednář
 */
public class Genre {

    private final int id;
    private final String name;

    public Genre(JsonObject json) {
        id = Convertor.convertInt(json.get("id"));
        name = Convertor.convertString(json.get("name"));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
