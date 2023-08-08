package io.github.ppetrbednar.tmdb.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import io.github.ppetrbednar.tmdb.api.exception.ApiException;
import io.github.ppetrbednar.tmdb.wrappers.response.StatusCode;
import java.net.URLEncoder;

/**
 * Controller for obtaining data from TMDB API.
 *
 * @author Petr Bednář
 */
public class ApiCall {

    public static String call(String call) throws ApiException {
        HttpURLConnection conn = null;
        try {

            URL url = new URL(call);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new ApiException(StatusCode.getValuesForHttpStatus(conn.getResponseCode()));
            }

            return new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

        } catch (IOException | RuntimeException e) {
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ex) {
                    // Could not disconnect
                }
            }
        }
    }
}
