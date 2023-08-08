package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for production company.
 *
 * @author Petr Bednář
 */
public class ProductionCompany {

    private final int id;
    private final String logoPath;
    private final String name;
    private final String originCountry;

    public ProductionCompany(JsonObject json) {
        id = Convertor.convertInt(json.get("id"));
        logoPath = Convertor.convertString(json.get("logo_path"));
        name = Convertor.convertString(json.get("name"));
        originCountry = Convertor.convertString(json.get("origin_country"));
    }

    public int getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

}
