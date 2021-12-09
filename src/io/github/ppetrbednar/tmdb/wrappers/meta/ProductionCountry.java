package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 *
 * @author Petr Bednář
 */
public class ProductionCountry {

    private final String iso_3166_1;
    private final String name;

    public ProductionCountry(JsonObject json) {
        iso_3166_1 = Convertor.convertString(json.get("iso_3166_1"));
        name = Convertor.convertString(json.get("name"));
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getName() {
        return name;
    }

}
