package io.github.ppetrbednar.tmdb.api;

import io.github.ppetrbednar.tmdb.api.exception.ApiException;
import io.github.ppetrbednar.tmdb.wrappers.response.StatusCode;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for obtaining data from Pallidium API.
 *
 * @author Petr Bednář
 */
public class ApiCall {

    private static final Logger LOG = LogManager.getLogger(ApiCall.class);

    public enum Method {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        public final String value;

        Method(String value) {
            this.value = value;
        }
    }

    public static String get(String url) throws ApiException {
        return get(url, null);
    }

    public static String get(String url, String token) throws ApiException {
        LOG.info("Sending GET request to: " + url);
        HttpGet request = new HttpGet(url);
        if (token != null) {
            request.addHeader("Authorization", "Bearer " + token);
        }
        return execute(request);
    }

    private static String execute(ClassicHttpRequest request) throws ApiException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(request)) {

                if (response.getCode() != 200) {
                    LOG.warn("Request response: " + response.getCode());
                    throw new ApiException(new LinkedList<>(StatusCode.getValuesForHttpStatus(response.getCode())));
                }

                LOG.info("Request response: 200");
                HttpEntity result = response.getEntity();
                return EntityUtils.toString(result);
            }
        } catch (IOException | ParseException e) {
            LOG.error("Request response: 500");
            throw new ApiException(new LinkedList<>(List.of(StatusCode.C11)));
        }
    }
}
