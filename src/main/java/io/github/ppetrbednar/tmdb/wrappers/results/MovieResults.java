package io.github.ppetrbednar.tmdb.wrappers.results;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for movie results.
 *
 * @author Petr Bednář
 */
public class MovieResults {

    private final int page;
    private final LinkedList<MovieResult> results;
    private final int totalPages;
    private final int totalResults;

    public MovieResults(JsonObject json) {
        page = Convertor.convertInt(json.get("page"));
        results = new LinkedList<>();

        for (Object obj : (JsonArray) json.get("results")) {
            results.add(new MovieResult((JsonObject) obj));
        }

        totalPages = Convertor.convertInt(json.get("total_pages"));
        totalResults = Convertor.convertInt(json.get("total_results"));
    }

    public int getPage() {
        return page;
    }

    public LinkedList<MovieResult> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

}
