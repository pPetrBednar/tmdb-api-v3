package io.github.ppetrbednar.tmdb.wrappers.movies;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import io.github.ppetrbednar.tmdb.wrappers.language.ISO_639;
import io.github.ppetrbednar.tmdb.wrappers.meta.Collection;
import io.github.ppetrbednar.tmdb.wrappers.meta.Credits;
import io.github.ppetrbednar.tmdb.wrappers.meta.Genre;
import io.github.ppetrbednar.tmdb.wrappers.meta.ProductionCompany;
import io.github.ppetrbednar.tmdb.wrappers.meta.ProductionCountry;
import io.github.ppetrbednar.tmdb.wrappers.meta.SpokenLanguage;

/**
 * Wrapper for movie metadata.
 *
 * @author Petr Bednář
 */
public class MovieMeta {

    private final boolean adult;
    private final String backdropPath;
    private final Collection belongsToCollection;
    private final int budget;
    private final LinkedList<Genre> genres;
    private final String homepage;
    private final int id;
    private final String imdbId;
    private final ISO_639 originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final double popularity;
    private final String posterPath;
    private final LinkedList<ProductionCompany> productionCompanies;
    private final LinkedList<ProductionCountry> productionCountries;
    private final String releaseDate;
    private final int revenue;
    private final int runtime;
    private final LinkedList<SpokenLanguage> spokenLanguages;
    private final String status;
    private final String tagline;
    private final String title;
    private final boolean video;
    private final double voteAverage;
    private final int voteCount;

    private final Credits credits;

    public MovieMeta(JsonObject json) {
        adult = Convertor.convertBoolean(json.get("adult"));
        backdropPath = Convertor.convertString(json.get("backdrop_path"));

        belongsToCollection = json.get("belongs_to_collection") == null ? null : new Collection((JsonObject) json.get("belongs_to_collection"));

        budget = Convertor.convertInt(json.get("budget"));
        genres = new LinkedList<>();
        if (json.get("genres") != null) {
            for (Object obj : (JsonArray) json.get("genres")) {
                genres.add(new Genre((JsonObject) obj));
            }
        }

        homepage = Convertor.convertString(json.get("homepage"));
        id = Convertor.convertInt(json.get("id"));
        imdbId = Convertor.convertString(json.get("imdb_id"));
        originalLanguage = new ISO_639(Convertor.convertString(json.get("original_language")));
        originalTitle = Convertor.convertString(json.get("original_title"));
        overview = Convertor.convertString(json.get("overview"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        posterPath = Convertor.convertString(json.get("poster_path"));

        productionCompanies = new LinkedList<>();
        if (json.get("production_companies") != null) {
            for (Object obj : (JsonArray) json.get("production_companies")) {
                productionCompanies.add(new ProductionCompany((JsonObject) obj));
            }
        }

        productionCountries = new LinkedList<>();
        if (json.get("production_countries") != null) {
            for (Object obj : (JsonArray) json.get("production_countries")) {
                productionCountries.add(new ProductionCountry((JsonObject) obj));
            }
        }

        releaseDate = Convertor.convertString(json.get("release_date"));

        revenue = Convertor.convertInt(json.get("revenue"));
        runtime = Convertor.convertInt(json.get("runtime"));

        spokenLanguages = new LinkedList<>();
        if (json.get("spoken_languages") != null) {
            for (Object obj : (JsonArray) json.get("spoken_languages")) {
                spokenLanguages.add(new SpokenLanguage((JsonObject) obj));
            }
        }

        status = Convertor.convertString(json.get("status"));
        tagline = Convertor.convertString(json.get("tagline"));
        title = Convertor.convertString(json.get("title"));
        video = Convertor.convertBoolean(json.get("video"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
        voteCount = Convertor.convertInt(json.get("vote_count"));

        credits = json.get("credits") == null ? null : new Credits((JsonObject) json.get("credits"));
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Collection getBelongsToCollection() {
        return belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public LinkedList<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
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

    public LinkedList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public LinkedList<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public LinkedList<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
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

    public int getVoteCount() {
        return voteCount;
    }

    public Credits getCredits() {
        return credits;
    }

}
