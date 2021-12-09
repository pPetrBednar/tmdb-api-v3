package io.github.ppetrbednar.tmdb.wrappers.series;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import io.github.ppetrbednar.tmdb.wrappers.meta.Crew;
import io.github.ppetrbednar.tmdb.wrappers.meta.GuestStar;

/**
 * Wrapper for episode metadata.
 *
 * @author Petr Bednář
 */
public class EpisodeMeta {

    private final String airDate;
    private final LinkedList<Crew> crew;
    private final double episodeNumber;
    private final LinkedList<GuestStar> guestStars;
    private final String name;
    private final String overview;
    private final double id;
    private final String productionCode;
    private final double seasonNumber;
    private final String stillPath;
    private final double voteAverage;
    private final double voteCount;

    public EpisodeMeta(JsonObject json) {

        airDate = Convertor.convertString(json.get("air_date"));

        crew = new LinkedList<>();
        if (json.get("crew") != null) {
            for (Object obj : (JsonArray) json.get("crew")) {
                crew.add(new Crew((JsonObject) obj));
            }
        }

        episodeNumber = Convertor.convertDouble(json.get("episode_number"));

        guestStars = new LinkedList<>();
        if (json.get("guest_stars") != null) {
            for (Object obj : (JsonArray) json.get("guest_stars")) {
                guestStars.add(new GuestStar((JsonObject) obj));
            }
        }

        name = Convertor.convertString(json.get("name"));
        overview = Convertor.convertString(json.get("overview"));
        id = Convertor.convertDouble(json.get("id"));
        productionCode = Convertor.convertString(json.get("production_code"));
        seasonNumber = Convertor.convertDouble(json.get("season_number"));
        stillPath = Convertor.convertString(json.get("still_path"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
        voteCount = Convertor.convertDouble(json.get("vote_count"));
    }

    public String getAirDate() {
        return airDate;
    }

    public LinkedList<Crew> getCrew() {
        return crew;
    }

    public double getEpisodeNumber() {
        return episodeNumber;
    }

    public LinkedList<GuestStar> getGuestStars() {
        return guestStars;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public double getId() {
        return id;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public double getSeasonNumber() {
        return seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

}
