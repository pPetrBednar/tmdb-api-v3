package io.github.ppetrbednar.tmdb.wrappers.results;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.tools.Convertor;

/**
 * Wrapper for series results.
 *
 * @author Petr Bednář
 */
public class SeriesResults {

    private final double page;
    private final LinkedList<SeriesResult> results;
    private final double totalPages;
    private final double totalResults;

    public SeriesResults(JsonObject json) {
        page = Convertor.convertDouble(json.get("page"));
        results = new LinkedList<>();

        for (Object obj : (JsonArray) json.get("results")) {
            results.add(new SeriesResult((JsonObject) obj));
        }

        totalPages = Convertor.convertDouble(json.get("total_pages"));
        totalResults = Convertor.convertDouble(json.get("total_results"));
    }

    public double getPage() {
        return page;
    }

    public LinkedList<SeriesResult> getResults() {
        return results;
    }

    public double getTotalPages() {
        return totalPages;
    }

    public double getTotalResults() {
        return totalResults;
    }

}
