package io.github.ppetrbednar.tmdb.wrappers.series;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import io.github.ppetrbednar.tmdb.wrappers.language.ISO_639;
import io.github.ppetrbednar.tmdb.wrappers.meta.CreatedBy;
import io.github.ppetrbednar.tmdb.wrappers.meta.Genre;
import io.github.ppetrbednar.tmdb.wrappers.meta.Network;
import io.github.ppetrbednar.tmdb.wrappers.meta.ProductionCompany;
import io.github.ppetrbednar.tmdb.wrappers.meta.ProductionCountry;
import io.github.ppetrbednar.tmdb.wrappers.meta.SpokenLanguage;

/**
 * Wrapper for series metadata.
 *
 * @author Petr Bednář
 */
public class SeriesMeta {

    private final String backdropPath;
    private final LinkedList<CreatedBy> createdby;
    private final LinkedList<Double> episodeRunTime;
    private final String firstAirDate;
    private final LinkedList<Genre> genres;
    private final String homepage;
    private final double id;
    private final boolean inProduction;
    private final LinkedList<ISO_639> languages;
    private final String lastAirDate;
    private final EpisodeMeta lastEpisodeToAir;
    private final String name;
    private final EpisodeMeta nextEpisodeToAir;
    private final LinkedList<Network> networks;
    private final double numberOfEpisodes;
    private final double numberOfSeasons;
    private final LinkedList<String> originCountry;
    private final ISO_639 originalLanguage;
    private final String originalName;
    private final String overview;
    private final double popularity;
    private final String posterPath;
    private final LinkedList<ProductionCompany> productionCompanies;
    private final LinkedList<ProductionCountry> productionCountries;
    private final LinkedList<SeasonMeta> seasons;
    private final LinkedList<SpokenLanguage> spokenLanguages;
    private final String status;
    private final String tagline;
    private final String type;
    private final double voteAverage;
    private final double voteCount;

    public SeriesMeta(JsonObject json) {
        backdropPath = Convertor.convertString(json.get("backdrop_path"));

        this.createdby = new LinkedList<>();
        if (json.get("created_by") != null) {
            for (Object obj : (JsonArray) json.get("created_by")) {
                this.createdby.add(new CreatedBy((JsonObject) obj));
            }
        }

        this.episodeRunTime = new LinkedList<>();
        if (json.get("episode_run_time") != null) {
            for (Object obj : (JsonArray) json.get("episode_run_time")) {
                this.episodeRunTime.add(Convertor.convertDouble(obj));
            }
        }

        firstAirDate = Convertor.convertString(json.get("first_air_date"));

        this.genres = new LinkedList<>();
        if (json.get("genres") != null) {
            for (Object obj : (JsonArray) json.get("genres")) {
                this.genres.add(new Genre((JsonObject) obj));
            }
        }

        homepage = Convertor.convertString(json.get("homepage"));
        id = Convertor.convertDouble(json.get("id"));
        inProduction = Convertor.convertBoolean(json.get("in_production"));

        this.languages = new LinkedList<>();
        if (json.get("languages") != null) {
            for (Object obj : (JsonArray) json.get("languages")) {
                this.languages.add(new ISO_639(Convertor.convertString(obj)));
            }
        }

        lastAirDate = Convertor.convertString(json.get("last_air_date"));
        lastEpisodeToAir = json.get("last_episode_to_air") == null ? null : new EpisodeMeta((JsonObject) json.get("last_episode_to_air"));
        name = Convertor.convertString(json.get("name"));
        nextEpisodeToAir = json.get("next_episode_to_air") == null ? null : new EpisodeMeta((JsonObject) json.get("next_episode_to_air"));

        this.networks = new LinkedList<>();
        if (json.get("networks") != null) {
            for (Object obj : (JsonArray) json.get("networks")) {
                this.networks.add(new Network((JsonObject) obj));
            }
        }

        numberOfEpisodes = Convertor.convertDouble(json.get("number_of_episodes"));
        numberOfSeasons = Convertor.convertDouble(json.get("number_of_seasons"));

        this.originCountry = new LinkedList<>();
        if (json.get("origin_country") != null) {
            for (Object obj : (JsonArray) json.get("origin_country")) {
                this.originCountry.add(Convertor.convertString(obj));
            }
        }

        originalLanguage = new ISO_639(Convertor.convertString("original_language"));
        originalName = Convertor.convertString(json.get("original_name"));
        overview = Convertor.convertString(json.get("overview"));
        popularity = Convertor.convertDouble(json.get("popularity"));
        posterPath = Convertor.convertString(json.get("poster_path"));

        this.productionCompanies = new LinkedList<>();
        if (json.get("production_companies") != null) {
            for (Object obj : (JsonArray) json.get("production_companies")) {
                this.productionCompanies.add(new ProductionCompany((JsonObject) obj));
            }
        }

        this.productionCountries = new LinkedList<>();
        if (json.get("production_countries") != null) {
            for (Object obj : (JsonArray) json.get("production_countries")) {
                this.productionCountries.add(new ProductionCountry((JsonObject) obj));
            }
        }

        this.seasons = new LinkedList<>();
        if (json.get("seasons") != null) {
            for (Object obj : (JsonArray) json.get("seasons")) {
                this.seasons.add(new SeasonMeta((JsonObject) obj));
            }
        }

        this.spokenLanguages = new LinkedList<>();
        if (json.get("spoken_languages") != null) {
            for (Object obj : (JsonArray) json.get("spoken_languages")) {
                this.spokenLanguages.add(new SpokenLanguage((JsonObject) obj));
            }
        }

        status = Convertor.convertString(json.get("status"));
        tagline = Convertor.convertString(json.get("tagline"));
        type = Convertor.convertString(json.get("type"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
        voteCount = Convertor.convertDouble(json.get("vote_count"));
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public LinkedList<CreatedBy> getCreatedby() {
        return createdby;
    }

    public LinkedList<Double> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public LinkedList<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public double getId() {
        return id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public LinkedList<ISO_639> getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public EpisodeMeta getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public String getName() {
        return name;
    }

    public EpisodeMeta getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public LinkedList<Network> getNetworks() {
        return networks;
    }

    public double getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public double getNumberOfSeasons() {
        return numberOfSeasons;
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

    public LinkedList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public LinkedList<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public LinkedList<SeasonMeta> getSeasons() {
        return seasons;
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

    public String getType() {
        return type;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

}
