package io.github.ppetrbednar.tmdb.wrappers.results;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import io.github.ppetrbednar.tmdb.tools.JsonConvertable;
import io.github.ppetrbednar.tmdb.wrappers.language.ISO_639;

/**
 *
 * @author Petr Bednář
 */
public class MovieResult {

    private final boolean adult;
    private final String backdropPath;
    private final LinkedList<Double> genreIds;
    private final double id;
    private final ISO_639 originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final double popularity;
    private final String posterPath;
    private final String releaseDate;
    private final String title;
    private final boolean video;
    private final double voteAverage;
    private final double voteCount;

    public MovieResult(JsonObject json) {
        adult = Convertor.convertBoolean(json.get("adult"));
        backdropPath = Convertor.convertString(json.get("backdrop_path"));
        genreIds = new LinkedList<>();

        for (Object obj : (JsonArray) json.get("genre_ids")) {
            genreIds.add(Convertor.convertDouble(obj));
        }

        id = Convertor.convertDouble(json.get("id"));
        originalLanguage = new ISO_639(Convertor.convertString(json.get("original_language")));
        originalTitle = Convertor.convertString(json.get("original_title"));
        overview = Convertor.convertString(json.get("overview"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        posterPath = Convertor.convertString(json.get("poster_path"));
        releaseDate = Convertor.convertString(json.get("release_date"));
        title = Convertor.convertString(json.get("title"));
        video = Convertor.convertBoolean(json.get("video"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
        voteCount = Convertor.convertDouble(json.get("vote_count"));

    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public LinkedList<Double> getGenreIds() {
        return genreIds;
    }

    public double getId() {
        return id;
    }

    public ISO_639 getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

}
