package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 *
 * @author Petr Bednář
 */
public class CreatedBy {

    private final double id;
    private final String creditId;
    private final String name;
    private final double gender;
    private final String profilePath;

    public CreatedBy(JsonObject json) {
        id = Convertor.convertDouble(json.get("id"));
        creditId = Convertor.convertString(json.get("credit_id"));
        name = Convertor.convertString(json.get("name"));
        gender = Convertor.convertDouble(json.get("gender"));
        profilePath = Convertor.convertString(json.get("profile_path"));
    }

    public double getId() {
        return id;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getName() {
        return name;
    }

    public double getGender() {
        return gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

}
