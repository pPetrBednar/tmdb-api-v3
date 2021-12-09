package io.github.ppetrbednar.tmdb.api;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import io.github.ppetrbednar.tmdb.api.exception.ApiException;
import io.github.ppetrbednar.tmdb.wrappers.movies.MovieMeta;
import io.github.ppetrbednar.tmdb.wrappers.results.MovieResults;
import io.github.ppetrbednar.tmdb.wrappers.results.SeriesResults;
import io.github.ppetrbednar.tmdb.wrappers.series.EpisodeMeta;
import io.github.ppetrbednar.tmdb.wrappers.series.SeasonMeta;
import io.github.ppetrbednar.tmdb.wrappers.series.SeriesMeta;

/**
 *
 * @author Petr Bednář
 */
public class Tmdb {

    private static final String SERIES = "https://api.themoviedb.org/3/tv/";
    private static final String SEARCH_SERIES = "https://api.themoviedb.org/3/search/tv";
    private static final String MOVIE = "https://api.themoviedb.org/3/movie/";
    private static final String SEARCH_MOVIE = "https://api.themoviedb.org/3/search/movie";
    private static final String IMAGE = "https://image.tmdb.org/t/p/";

    private final String API_KEY;
    private final Localization LANG;
    private final String SUFFIX;

    public Tmdb(String apiKey, Localization language) {
        this.API_KEY = apiKey;
        this.LANG = language;
        this.SUFFIX = "?api_key=" + apiKey + "&language=" + language.getLanguage();
    }

    public SeriesResults searchForSeries(String title) throws ApiException {
        try {
            String data = ApiCall.call(SEARCH_SERIES + SUFFIX + "&query=" + title);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            SeriesResults meta = new SeriesResults(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    public MovieResults searchForMovie(String title) throws ApiException {
        try {
            String data = ApiCall.call(SEARCH_MOVIE + SUFFIX + "&query=" + title);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            MovieResults meta = new MovieResults(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    public SeriesMeta getSeriesMeta(int seriesId) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            SeriesMeta meta = new SeriesMeta(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    public SeasonMeta getSeasonMeta(int seriesId, int seasonNumber) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            SeasonMeta meta = new SeasonMeta(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    public EpisodeMeta getEpisodeMeta(int seriesId, int seasonNumber, int episodeNumber) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + "/episode/" + episodeNumber + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            EpisodeMeta meta = new EpisodeMeta(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    public MovieMeta getMovieMeta(int movieId) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE + movieId + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            MovieMeta meta = new MovieMeta(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    public File getCover(String image, CoverSize coverSize) {
        try {
            File file = File.createTempFile("temp", ".jpg");
            FileUtils.copyURLToFile(new URL(IMAGE + coverSize.getCommand() + image), file, 10000, 10000);
            return file;
        } catch (IOException ex) {
            return null;
        }
    }
}
