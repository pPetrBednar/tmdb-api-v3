package io.github.ppetrbednar.tmdb.api;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import io.github.ppetrbednar.tmdb.api.exception.ApiException;
import io.github.ppetrbednar.tmdb.wrappers.image.ICoverSize;
import io.github.ppetrbednar.tmdb.wrappers.meta.Credits;
import io.github.ppetrbednar.tmdb.wrappers.movies.MovieMeta;
import io.github.ppetrbednar.tmdb.wrappers.response.StatusCode;
import io.github.ppetrbednar.tmdb.wrappers.results.MovieResults;
import io.github.ppetrbednar.tmdb.wrappers.results.SeriesResults;
import io.github.ppetrbednar.tmdb.wrappers.series.EpisodeMeta;
import io.github.ppetrbednar.tmdb.wrappers.series.SeasonMeta;
import io.github.ppetrbednar.tmdb.wrappers.series.SeriesMeta;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TMDB API controller.
 *
 * @author Petr Bednář
 */
public class Tmdb {

    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;

    private final String API_URL;

    private final String SERIES;
    private final String SERIES_SEARCH;

    private final String MOVIE;
    private final String MOVIE_SEARCH;

    private static final String IMAGE = "https://image.tmdb.org/t/p/";
    private static final String APPEND_KEYWORD = "&append_to_response=";

    private final String API_KEY;
    private final Localization LANG;
    private final String SUFFIX;

    /**
     * Creates an instance of TMDB API Controller.
     *
     * @param apiKey   API (v3 auth) key.
     * @param language Selected localization language.
     */
    public Tmdb(String apiKey, Localization language) {
        this.API_KEY = apiKey;
        this.LANG = language;
        this.SUFFIX = "?api_key=" + apiKey + "&language=" + language.localization;

        API_URL = "https://api.themoviedb.org";
        SERIES = API_URL + "/3/tv/";
        SERIES_SEARCH = API_URL + "/3/search/tv";
        MOVIE = API_URL + "/3/movie/";
        MOVIE_SEARCH = API_URL + "/3/search/movie";
    }

    /**
     * Creates an instance of TMDB API Controller.
     *
     * @param apiUrl   URL address of TMDB v3 API.
     * @param language Selected localization language.
     */
    public Tmdb(URI apiUrl, Localization language) {
        this.API_KEY = null;
        this.LANG = language;
        this.SUFFIX = "?language=" + language.localization;

        API_URL = apiUrl.toString();
        SERIES = API_URL + "/3/tv/";
        SERIES_SEARCH = API_URL + "/3/search/tv";
        MOVIE = API_URL + "/3/movie/";
        MOVIE_SEARCH = API_URL + "/3/search/movie";
    }

    /**
     * Searches for series credits by inputted id.
     *
     * @param seriesId Series ID.
     * @return Credits or null.
     */
    public Credits getSeriesCredits(int seriesId) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + "/credits" + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new Credits(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Searches for movie credits by inputted id.
     *
     * @param movieId Movie ID.
     * @return Credits or null.
     */
    public Credits getMovieCredits(int movieId) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE + movieId + "/credits" + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new Credits(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Searches for series by inputted parameters.
     *
     * @param title Series title.
     * @return Series results or null.
     */
    public SeriesResults searchForSeries(String title) throws ApiException {
        try {
            String data = ApiCall.call(SERIES_SEARCH + SUFFIX + "&query=" + URLEncoder.encode(title, StandardCharsets.UTF_8));
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new SeriesResults(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Searches for movie by inputted parameters.
     *
     * @param title Movie title.
     * @return Movie results or null.
     */
    public MovieResults searchForMovie(String title) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE_SEARCH + SUFFIX + "&query=" + URLEncoder.encode(title, StandardCharsets.UTF_8));
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new MovieResults(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @return Series metadata or null.
     */
    public SeriesMeta getSeriesMeta(int seriesId) throws ApiException {
        try {
            String tempData = ApiCall.call(SERIES + seriesId + SUFFIX);
            JsonObject tempJson = (JsonObject) Jsoner.deserialize(tempData);
            SeriesMeta temp = new SeriesMeta(tempJson);

            var seasons = temp.getSeasons().stream()
                    .map(SeasonMeta::getSeasonNumber)
                    .map(seasonNumber -> "season/" + seasonNumber)
                    .toList();

            List<List<String>> batches = splitSeasonsToBatches(seasons, 20);
            List<SeriesMeta> seriesMetaList = new LinkedList<>();
            for (var batch : batches) {
                var seasonsRequest = String.join(",", batch);
                String data = ApiCall.call(SERIES + temp.getId() + SUFFIX + APPEND_KEYWORD + seasonsRequest);
                JsonObject json = (JsonObject) Jsoner.deserialize(data);
                SeriesMeta meta = new SeriesMeta(json);
                seriesMetaList.add(meta);
            }
            return mergeSeriesMeta(seriesMetaList);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API. Parameter streamlines the initial query.
     *
     * @param seriesMeta SeriesMeta
     * @return Series metadata or null.
     */
    public SeriesMeta getSeriesMeta(SeriesMeta seriesMeta) throws ApiException {
        try {

            var seasons = seriesMeta.getSeasons().stream()
                    .map(SeasonMeta::getSeasonNumber)
                    .map(seasonNumber -> "season/" + seasonNumber)
                    .toList();

            List<List<String>> batches = splitSeasonsToBatches(seasons, 20);
            List<SeriesMeta> seriesMetaList = new LinkedList<>();
            for (var batch : batches) {
                var seasonsRequest = String.join(",", batch);
                String data = ApiCall.call(SERIES + seriesMeta.getId() + SUFFIX + APPEND_KEYWORD + seasonsRequest);
                JsonObject json = (JsonObject) Jsoner.deserialize(data);
                SeriesMeta meta = new SeriesMeta(json);
                seriesMetaList.add(meta);
            }
            return mergeSeriesMeta(seriesMetaList);
        } catch (JsonException ex) {
            return null;
        }
    }

    private List<List<String>> splitSeasonsToBatches(List<String> seasons, int batchSize) {
        List<List<String>> seasonBatches = new LinkedList<>();
        List<String> currentBatch = new LinkedList<>();

        int counter = 0;
        for (var season : seasons) {
            currentBatch.add(season);
            if (++counter >= batchSize) {
                seasonBatches.add(currentBatch);
                currentBatch = new LinkedList<>();
                counter = 0;
            }
        }
        if (!currentBatch.isEmpty()) {
            seasonBatches.add(currentBatch);
        }
        return seasonBatches;
    }

    private SeriesMeta mergeSeriesMeta(List<SeriesMeta> seriesMetaList) throws ApiException {
        if (seriesMetaList.isEmpty()) {
            throw new ApiException(new LinkedList<>());
        }

        if (seriesMetaList.size() == 1) {
            return seriesMetaList.get(0);
        }

        SeriesMeta result = seriesMetaList.remove(0);
        for (var seriesMeta : seriesMetaList) {
            result.getSeasons().addAll(seriesMeta.getSeasons());
        }

        return result;
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @return Series metadata or null.
     */
    public SeriesMeta getSeriesMetaWithCredits(int seriesId) throws ApiException {
        try {
            String tempData = ApiCall.call(SERIES + seriesId + SUFFIX);
            JsonObject tempJson = (JsonObject) Jsoner.deserialize(tempData);
            SeriesMeta temp = new SeriesMeta(tempJson);

            var seasons = temp.getSeasons().stream()
                    .map(SeasonMeta::getSeasonNumber)
                    .map(seasonNumber -> "season/" + seasonNumber)
                    .toList();

            List<List<String>> batches = splitSeasonsToBatches(seasons, 19);
            List<SeriesMeta> seriesMetaList = new LinkedList<>();
            for (var batch : batches) {
                var seasonsRequest = String.join(",", batch);
                String data = ApiCall.call(SERIES + temp.getId() + SUFFIX + APPEND_KEYWORD + "credits," + seasonsRequest);
                JsonObject json = (JsonObject) Jsoner.deserialize(data);
                SeriesMeta meta = new SeriesMeta(json);
                seriesMetaList.add(meta);
            }
            return mergeSeriesMeta(seriesMetaList);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API. Parameter streamlines the initial query.
     *
     * @param seriesMeta SeriesMeta
     * @return Series metadata or null.
     */
    public SeriesMeta getSeriesMetaWithCredits(SeriesMeta seriesMeta) throws ApiException {
        try {

            var seasons = seriesMeta.getSeasons().stream()
                    .map(SeasonMeta::getSeasonNumber)
                    .map(seasonNumber -> "season/" + seasonNumber)
                    .toList();

            List<List<String>> batches = splitSeasonsToBatches(seasons, 19);
            List<SeriesMeta> seriesMetaList = new LinkedList<>();
            for (var batch : batches) {
                var seasonsRequest = String.join(",", batch);
                String data = ApiCall.call(SERIES + seriesMeta.getId() + SUFFIX + APPEND_KEYWORD + "credits," + seasonsRequest);
                JsonObject json = (JsonObject) Jsoner.deserialize(data);
                SeriesMeta meta = new SeriesMeta(json);
                seriesMetaList.add(meta);
            }
            return mergeSeriesMeta(seriesMetaList);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @return Series metadata json string.
     */
    public String getSeriesMetaJson(int seriesId) throws ApiException {
        try {
            String tempData = ApiCall.call(SERIES + seriesId + SUFFIX);
            JsonObject tempJson = (JsonObject) Jsoner.deserialize(tempData);
            SeriesMeta temp = new SeriesMeta(tempJson);

            var seasons = temp.getSeasons().stream()
                    .map(SeasonMeta::getSeasonNumber)
                    .map(seasonNumber -> "season/" + seasonNumber)
                    .collect(Collectors.joining(","));

            return ApiCall.call(SERIES + seriesId + SUFFIX + APPEND_KEYWORD + seasons);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId Series ID.
     * @return Series metadata json string.
     */
    public String getSeriesMetaJsonWithCredits(int seriesId) throws ApiException {
        try {
            String tempData = ApiCall.call(SERIES + seriesId + SUFFIX);
            JsonObject tempJson = (JsonObject) Jsoner.deserialize(tempData);
            SeriesMeta temp = new SeriesMeta(tempJson);

            var seasons = temp.getSeasons().stream()
                    .map(SeasonMeta::getSeasonNumber)
                    .map(seasonNumber -> "season/" + seasonNumber)
                    .collect(Collectors.joining(","));

            return ApiCall.call(SERIES + seriesId + SUFFIX + APPEND_KEYWORD + "credits," + seasons);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId     Series ID.
     * @param seasonNumber Season number.
     * @return Season metadata or null.
     */
    public SeasonMeta getSeasonMeta(int seriesId, int seasonNumber) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new SeasonMeta(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series metadata from TMDB API.
     *
     * @param seriesId     Series ID.
     * @param seasonNumber Season number.
     * @return Season metadata json string.
     */
    public String getSeasonMetaJson(int seriesId, int seasonNumber) throws ApiException {
        return ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + SUFFIX);
    }

    /**
     * Obtains series episode metadata from TMDB API.
     *
     * @param seriesId      Series ID.
     * @param seasonNumber  Season number.
     * @param episodeNumber Episode number.
     * @return Episode metadata or null.
     */
    public EpisodeMeta getEpisodeMeta(int seriesId, int seasonNumber, int episodeNumber) throws ApiException {
        try {
            String data = ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + "/episode/" + episodeNumber + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new EpisodeMeta(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains series episode metadata from TMDB API.
     *
     * @param seriesId      Series ID.
     * @param seasonNumber  Season number.
     * @param episodeNumber Episode number.
     * @return Episode metadata json string.
     */
    public String getEpisodeMetaJson(int seriesId, int seasonNumber, int episodeNumber) throws ApiException {
        return ApiCall.call(SERIES + seriesId + "/season/" + seasonNumber + "/episode/" + episodeNumber + SUFFIX);
    }

    /**
     * Obtains movie metadata from TMDB API.
     *
     * @param movieId Movie ID.
     * @return Movie metadata or null.
     */
    public MovieMeta getMovieMeta(int movieId) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE + movieId + SUFFIX);
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new MovieMeta(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains movie metadata from TMDB API.
     *
     * @param movieId Movie ID.
     * @return Movie metadata or null.
     */
    public MovieMeta getMovieMetaWithCredits(int movieId) throws ApiException {
        try {
            String data = ApiCall.call(MOVIE + movieId + SUFFIX + APPEND_KEYWORD + "credits");
            JsonObject json = (JsonObject) Jsoner.deserialize(data);
            return new MovieMeta(json);
        } catch (JsonException ex) {
            return null;
        }
    }

    /**
     * Obtains movie metadata from TMDB API.
     *
     * @param movieId Movie ID.
     * @return Movie metadata json string.
     */
    public String getMovieMetaJson(int movieId) throws ApiException {
        return ApiCall.call(MOVIE + movieId + SUFFIX);
    }

    /**
     * Obtains movie metadata from TMDB API.
     *
     * @param movieId Movie ID.
     * @return Movie metadata json string.
     */
    public String getMovieMetaJsonWithCredits(int movieId) throws ApiException {
        return ApiCall.call(MOVIE + movieId + SUFFIX + APPEND_KEYWORD + "credits");
    }

    /**
     * Downloads image from TMDB servers to temporary file. Temporary file is usable until VM exit.
     *
     * @param image     Image location on TMDB servers (/image.jpg).
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
