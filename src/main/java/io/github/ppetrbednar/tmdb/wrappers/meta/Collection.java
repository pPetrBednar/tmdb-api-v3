package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for collection.
 *
 * @author Petr Bednář
 */
public class Collection {

    private final int id;
    private final String name;
    private final String posterPath;
    private final String backdropPath;

    public Collection(JsonObject json) {
        id = Convertor.convertInt(json.get("id"));
        name = Convertor.convertString(json.get("name"));
        posterPath = Convertor.convertString(json.get("poster_path"));
        backdropPath = Convertor.convertString(json.get("backdrop_path"));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

}
