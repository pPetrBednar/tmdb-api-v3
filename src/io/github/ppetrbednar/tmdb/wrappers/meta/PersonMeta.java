package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import java.util.LinkedList;

/**
 *
 * @author Petr Bednář
 */
public class PersonMeta {

    private final String birthday;
    private final String knownForDepartment;
    private final String deathday;
    private final int id;
    private final String name;
    private final LinkedList<String> alsoKnownAs;
    private final int gender;
    private final String biography;
    private final double popularity;
    private final String placeOfBirth;
    private final String profilePath;
    private final boolean adult;
    private final String imdbId;
    private final String homepage;

    public PersonMeta(JsonObject json) {
        birthday = Convertor.convertString(json.get("birthday"));
        knownForDepartment = Convertor.convertString(json.get("known_for_department"));
        deathday = Convertor.convertString(json.get("deathday"));
        id = Convertor.convertInt(json.get("id"));
        name = Convertor.convertString(json.get("name"));

        alsoKnownAs = new LinkedList<>();
        for (Object obj : (JsonArray) json.get("also_known_as")) {
            alsoKnownAs.add(Convertor.convertString(obj));
        }

        gender = Convertor.convertInt(json.get("gender"));
        biography = Convertor.convertString(json.get("biography"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        placeOfBirth = Convertor.convertString(json.get("place_of_birth"));
        profilePath = Convertor.convertString(json.get("profile_path"));
        adult = Convertor.convertBoolean(json.get("adult"));
        imdbId = Convertor.convertString(json.get("imdb_id"));
        homepage = Convertor.convertString(json.get("homepage"));
    }

    public String getBirthday() {
        return birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getDeathday() {
        return deathday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public int getGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getHomepage() {
        return homepage;
    }

}
