package io.github.ppetrbednar.tmdb.api;

import io.github.ppetrbednar.tmdb.wrappers.image.PosterSize;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import io.github.ppetrbednar.tmdb.api.exception.ApiException;
import io.github.ppetrbednar.tmdb.wrappers.image.ICoverSize;
import io.github.ppetrbednar.tmdb.wrappers.meta.Credits;
import io.github.ppetrbednar.tmdb.wrappers.movies.MovieMeta;
import io.github.ppetrbednar.tmdb.wrappers.results.MovieResults;
import io.github.ppetrbednar.tmdb.wrappers.results.SeriesResults;
import io.github.ppetrbednar.tmdb.wrappers.series.EpisodeMeta;
import io.github.ppetrbednar.tmdb.wrappers.series.SeasonMeta;
import io.github.ppetrbednar.tmdb.wrappers.series.SeriesMeta;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TMDB API controller.
 *
 * @author Petr Bednář
 */
public class Tmdb {

    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;

    private static final String SERIES = "https://api.themoviedb.org/3/tv/";
    private static final String SERIES_SEARCH = "https://api.themoviedb.org/3/search/tv";

    private static final String MOVIE = "https://api.themoviedb.org/3/movie/";
    private static final String MOVIE_SEARCH = "https://api.themoviedb.org/3/search/movie";

    private static final String IMAGE = "https://image.tmdb.org/t/p/";

    private final String API_KEY;
    private final Localization LANG;
    private final String SUFFIX;

    /**
     * Creates an instance of TMDB API Controller.
     *
     * @param apiKey API (v3 auth) key.
     * @param language Selected localization language.
     */
    public Tmdb(String apiKey, Localization language) {
        this.API_KEY = apiKey;
        this.LANG = language;
        this.SUFFIX = "?api_key=" + apiKey + "&language=" + language.getLanguage();
    }

    /**
     * Searches for series credits by inputted id.
     *
     * @param seriesId Series ID.
     * @return Credits or null.
     * @throws ApiException
     */
    public Credits getSeriesCredits(int seriesId) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + "/credits" + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            Credits meta = new Credits(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Searches for movie credits by inputted id.
     *
     * @param movieId Movie ID.
     * @return Credits or null.
     * @throws ApiException
     */
    public Credits getMovieCredits(int movieId) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE + movieId + "/credits" + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            Credits meta = new Credits(json);
            return meta;
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Searches for series by inputted parameters.
     *
     * @param title Series title.
     * @return Series results or null.
     * @throws ApiException
     */
    public SeriesResults searchForSeries(String title) throws ApiException {
        try {
            String data = ApiCall.call(SERIES_SEARCH + SUFFIX + "&query=" + URLEncoder.encode(title, StandardCharsets.UTF_8.toString()));
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            SeriesResults meta = new SeriesResults(json);
            return meta;
        } catch (JsonException | UnsupportedEncodingException ex) {
            return null;
        }
    }

    /**
     * Searches for movie by inputted parameters.
     *
     * @param title Movie title.
     * @return Movie results or null.
     * @throws ApiException
     */
    public MovieResults searchForMovie(String title) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE_SEARCH + SUFFIX + "&query=" + URLEncoder.encode(title, StandardCharsets.UTF_8.toString()));
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            MovieResults meta = new MovieResults(json);
            return meta;
        } catch (JsonException | UnsupportedEncodingException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @return Series metadata or null.
     * @throws ApiException
     */
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

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @return Series metadata json string.
     * @throws ApiException
     */
    public String getSeriesMetaJson(int seriesId) throws ApiException {
        return ApiCall.call(SERIES + seriesId + SUFFIX);
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @param seasonNumber Season number.
     * @return Season metadata or null.
     * @throws ApiException
     */
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

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @param seasonNumber Season number.
     * @return Season metadata json string.
     * @throws ApiException
     */
    public String getSeasonMetaJson(int seriesId, int seasonNumber) throws ApiException {
        return ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + SUFFIX);
    }

    /**
     * Obtains series episode metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @param seasonNumber Season number.
     * @param episodeNumber Episode number.
     * @return Episode metadata or null.
     * @throws ApiException
     */
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

    /**
     * Obtains series episode metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @param seasonNumber Season number.
     * @param episodeNumber Episode number.
     * @return Episode metadata json string.
     * @throws ApiException
     */
    public String getEpisodeMetaJson(int seriesId, int seasonNumber, int episodeNumber) throws ApiException {
        return ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + "/episode/" + episodeNumber + SUFFIX);
    }

    /**
     * Obtains movie metadata from TMDB API.
     *
     * @param movieId Movie ID.
     * @return Movie metadata or null.
     * @throws ApiException
     */
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

    /**
     * Obtains movie metadata from TMDB API.
     *
     * @param movieId Movie ID.
     * @return Movie metadata json string.
     * @throws ApiException
     */
    public String getMovieMetaJson(int movieId) throws ApiException {
        return ApiCall.call(MOVIE + movieId + SUFFIX);
    }

    /**
     * Downloads image from TMDB servers to temporary file. Temporary file is usable until VM exit.
     *
     * @param image Image location on TMDB servers (/image.jpg).
     * @param coverSize Selected cover size preset.
     * @return Temporary file in default TEMP directory.
     */
    public File getCover(String image, ICoverSize coverSize) {
        try {
            File file = File.createTempFile("temp", ".jpg");
            file.deleteOnExit();
            FileUtils.copyURLToFile(new URL(IMAGE + coverSize.getCommand() + image), file, CONNECTION_TIMEOUT, READ_TIMEOUT);
            return file;
        } catch (IOException ex) {
            return null;
        }
    }
}
