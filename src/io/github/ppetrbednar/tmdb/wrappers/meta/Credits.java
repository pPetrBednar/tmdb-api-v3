package io.github.ppetrbednar.tmdb.wrappers.meta;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import io.github.ppetrbednar.tmdb.tools.Convertor;
import java.util.LinkedList;

/**
 * Wrapper for credits.
 *
 * @author Petr Bednář
 */
public class Credits {

    private final int id;
    private final LinkedList<Cast> cast;
    private final LinkedList<Crew> crew;

    public Credits(JsonObject json) {
        id = Convertor.convertInt(json.get("id"));

        cast = new LinkedList<>();
        for (Object obj : (JsonArray) json.get("cast")) {
            cast.add(new Cast((JsonObject) obj));
        }

        crew = new LinkedList<>();
        for (Object obj : (JsonArray) json.get("crew")) {
            crew.add(new Crew((JsonObject) obj));
        }
    }

    public int getId() {
        return id;
    }

    public LinkedList<Cast> getCast() {
        return cast;
    }

    public LinkedList<Crew> getCrew() {
        return crew;
    }

}
