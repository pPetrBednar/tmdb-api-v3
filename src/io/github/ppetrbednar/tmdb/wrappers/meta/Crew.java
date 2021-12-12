package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for crew.
 *
 * @author Petr Bednář
 */
public class Crew {

    private final boolean adult;
    private final int gender;
    private final int id;
    private final String knownForDepartment;
    private final String name;
    private final String originalName;
    private final double popularity;
    private final String profilePath;
    private final String creditId;
    private final String department;
    private final String job;

    public Crew(JsonObject json) {

        adult = Convertor.convertBoolean(json.get("adult"));
        gender = Convertor.convertInt(json.get("gender"));
        id = Convertor.convertInt(json.get("id"));
        knownForDepartment = Convertor.convertString(json.get("known_for_department"));
        name = Convertor.convertString(json.get("name"));
        originalName = Convertor.convertString(json.get("original_name"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        profilePath = Convertor.convertString(json.get("profile_path"));
        creditId = Convertor.convertString(json.get("credit_id"));
        department = Convertor.convertString(json.get("department"));
        job = Convertor.convertString(json.get("job"));
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

    public String getCreditId() {
        return creditId;
    }

    public String getDepartment() {
        return department;
    }

    public String getJob() {
        return job;
    }

    /*
    adult = Convertor.convertBoolean(json.get("adult"));
        gender = Convertor.convertInt(json.get("gender"));
        id = Convertor.convertInt(json.get("id"));
        knownForDepartment = Convertor.convertString(json.get("known_for_department"));
        name = Convertor.convertString(json.get("name"));
        originalName = Convertor.convertString(json.get("original_name"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        profilePath = Convertor.convertString(json.get("profile_path"));
        creditId = Convertor.convertString(json.get("credit_id"));
        department = Convertor.convertString(json.get("department"));
        job = Convertor.convertString(json.get("job"));
     */
    public JsonObject toJsonObject() {
        JsonObject output = new JsonObject();

        output.put("adult", adult);
        output.put("gender", gender);
        output.put("id", id);
        output.put("known_for_department", knownForDepartment);
        output.put("name", name);
        output.put("original_name", originalName);
        output.put("popularity", popularity);
        output.put("profile_path", profilePath);
        output.put("credit_id", creditId);
        output.put("department", department);
        output.put("job", job);

        return output;
    }

}
