package io.github.ppetrbednar.tmdb.wrappers.results;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import io.github.ppetrbednar.tmdb.wrappers.language.ISO_639;

/**
 * Wrapper for series result.
 *
 * @author Petr Bednář
 */
public class SeriesResult {

    private final String backdropPath;
    private final String firstAirDate;
    private final LinkedList<Double> genreIds;
    private final int id;
    private final String name;
    private final LinkedList<String> originCountry;
    private final ISO_639 originalLanguage;
    private final String originalName;
    private final String overview;
    private final double popularity;
    private final String posterPath;
    private final double voteAverage;
    private final int voteCount;

    public SeriesResult(JsonObject json) {
        backdropPath = Convertor.convertString(json.get("backdrop_path"));
        firstAirDate = Convertor.convertString(json.get("first_air_date"));

        genreIds = new LinkedList<>();
        for (Object obj : (JsonArray) json.get("genre_ids")) {
            genreIds.add(Convertor.convertDouble(obj));
        }

        id = Convertor.convertInt(json.get("id"));
        name = Convertor.convertString(json.get("name"));

        originCountry = new LinkedList<>();
        for (Object obj : (JsonArray) json.get("origin_country")) {
            originCountry.add(Convertor.convertString(obj));
        }

        originalLanguage = new ISO_639(Convertor.convertString(json.get("original_language")));
        originalName = Convertor.convertString(json.get("original_name"));
        overview = Convertor.convertString(json.get("overview"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        posterPath = Convertor.convertString(json.get("poster_path"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
        voteCount = Convertor.convertInt(json.get("vote_count"));

    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public LinkedList<Double> getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<String> getOriginCountry() {
        return originCountry;
    }

    public ISO_639 getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

}
