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
    private final int episodeNumber;
    private final LinkedList<Crew> crew;
    private final LinkedList<GuestStar> guestStars;
    private final int id;
    private final String name;
    private final String overview;
    private final String productionCode;
    private final int seasonNumber;
    private final String stillPath;
    private final double voteAverage;
    private final int voteCount;

    public EpisodeMeta(JsonObject json) {

        airDate = Convertor.convertString(json.get("air_date"));
        episodeNumber = Convertor.convertInt(json.get("episode_number"));

        crew = new LinkedList<>();
        if (json.get("crew") != null) {
            for (Object obj : (JsonArray) json.get("crew")) {
                crew.add(new Crew((JsonObject) obj));
            }
        }

        guestStars = new LinkedList<>();
        if (json.get("guest_stars") != null) {
            for (Object obj : (JsonArray) json.get("guest_stars")) {
                guestStars.add(new GuestStar((JsonObject) obj));
            }
        }

        id = Convertor.convertInt(json.get("id"));
        name = Convertor.convertString(json.get("name"));
        overview = Convertor.convertString(json.get("overview"));
        productionCode = Convertor.convertString(json.get("production_code"));
        seasonNumber = Convertor.convertInt(json.get("season_number"));
        stillPath = Convertor.convertString(json.get("still_path"));
        voteAverage = Convertor.convertDouble(json.get("vote_average"));
        voteCount = Convertor.convertInt(json.get("vote_count"));
    }

    public String getAirDate() {
        return airDate;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public LinkedList<Crew> getCrew() {
        return crew;
    }

    public LinkedList<GuestStar> getGuestStars() {
        return guestStars;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

}
