package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for cast.
 *
 * @author Petr Bednář
 */
public class Cast {

    private final boolean adult;
    private final int gender;
    private final int id;
    private final String knownForDepartment;
    private final String name;
    private final String originalName;
    private final double popularity;
    private final String profilePath;
    private final String character;
    private final String creditId;
    private final int order;

    public Cast(JsonObject json) {

        adult = Convertor.convertBoolean(json.get("adult"));
        gender = Convertor.convertInt(json.get("gender"));
        id = Convertor.convertInt(json.get("id"));
        knownForDepartment = Convertor.convertString(json.get("known_for_department"));
        name = Convertor.convertString(json.get("name"));
        originalName = Convertor.convertString(json.get("original_name"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        profilePath = Convertor.convertString(json.get("profile_path"));
        character = Convertor.convertString(json.get("character"));
        creditId = Convertor.convertString(json.get("credit_id"));
        order = Convertor.convertInt(json.get("order"));
    }

    public boolean isAdult() {
        return adult;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public int getOrder() {
        return order;
    }

}
