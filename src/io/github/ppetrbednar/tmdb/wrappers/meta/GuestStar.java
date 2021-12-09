package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 *
 * @author Petr Bednář
 */
public class GuestStar {

    private final String character;
    private final String creditId;
    private final double order;
    private final boolean adult;
    private final double gender;
    private final double id;
    private final String knownForDepartment;
    private final String name;
    private final String originalName;
    private final double popularity;
    private final String profilePath;

    public GuestStar(JsonObject json) {
        character = Convertor.convertString(json.get("character"));
        creditId = Convertor.convertString(json.get("credit_id"));
        order = Convertor.convertDouble(json.get("order"));
        adult = Convertor.convertBoolean(json.get("adult"));
        gender = Convertor.convertDouble(json.get("gender"));
        id = Convertor.convertDouble(json.get("id"));
        knownForDepartment = Convertor.convertString(json.get("known_for_department"));
        name = Convertor.convertString(json.get("name"));
        originalName = Convertor.convertString(json.get("original_name"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        profilePath = Convertor.convertString(json.get("profile_path"));
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public double getOrder() {
        return order;
    }

    public boolean isAdult() {
        return adult;
    }

    public double getGender() {
        return gender;
    }

    public double getId() {
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

}
