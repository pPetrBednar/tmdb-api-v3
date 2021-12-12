package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for network.
 *
 * @author Petr Bednář
 */
public class Network {

    private final String name;
    private final int id;
    private final String logoPath;
    private final String originCountry;

    public Network(JsonObject json) {
        name = Convertor.convertString(json.get("name"));
        id = Convertor.convertInt(json.get("id"));
        logoPath = Convertor.convertString(json.get("logo_path"));
        originCountry = Convertor.convertString(json.get("origin_country"));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getOriginCountry() {
        return originCountry;
    }

}
