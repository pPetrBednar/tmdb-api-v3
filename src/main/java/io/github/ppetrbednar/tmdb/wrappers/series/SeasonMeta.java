package io.github.ppetrbednar.tmdb.wrappers.series;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.LinkedList;

import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for season metadata.
 *
 * @author Petr Bednář
 */
public class SeasonMeta {

    private final String _id;
    private final String airDate;
    private final LinkedList<EpisodeMeta> episodes;
    private final int episodeCount;
    private final String name;
    private final String overview;
    private final int id;
    private final String posterPath;
    private final int seasonNumber;
    private final double voteAverage;

    public SeasonMeta(JsonObject json) {

        _id = Convertor.convertString(json.get("_id"));
        airDate = Convertor.convertString(json.get("air_date"));

        episodes = new LinkedList<>();
        if (json.get("episodes") != null) {
            for (Object obj : (JsonArray) json.get("episodes")) {
                episodes.add(new EpisodeMeta((JsonObject) obj));
            }
        }

        episodeCount = Convertor.convertInt(json.get("episode_count")) == 0 ? episodes.size() : Convertor.convertInt(json.get("episode_count"));
        name = Convertor.convertString(json.get("name"));
        overview = Convertor.convertString(json.get("overview"));
        id = Convertor.convertInt(json.get("id"));
        posterPath = Convertor.convertString(json.get("poster_path"));
        seasonNumber = Convertor.convertInt(json.get("season_number"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
    }

    public String get_Id() {
        return _id;
    }

    public String getAirDate() {
        return airDate;
    }

    public LinkedList<EpisodeMeta> getEpisodes() {
        return episodes;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public double getVoteAverage() {
        return voteAverage;
    }
}
