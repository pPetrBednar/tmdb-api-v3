package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for created by.
 *
 * @author Petr Bednář
 */
public class CreatedBy {

    private final int id;
    private final String creditId;
    private final String name;
    private final int gender;
    private final String profilePath;

    public CreatedBy(JsonObject json) {
        id = Convertor.convertInt(json.get("id"));
        creditId = Convertor.convertString(json.get("credit_id"));
        name = Convertor.convertString(json.get("name"));
        gender = Convertor.convertInt(json.get("gender"));
        profilePath = Convertor.convertString(json.get("profile_path"));
    }

    public int getId() {
        return id;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

}
