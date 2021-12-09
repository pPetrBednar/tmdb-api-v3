package io.github.ppetrbednar.tmdb.api.exception;

import java.util.LinkedList;
import io.github.ppetrbednar.tmdb.wrappers.response.StatusCode;

/**
 *
 * @author Petr Bednář
 */
public class ApiException extends Exception {

    private final LinkedList<StatusCode> statusCode;

    public ApiException(LinkedList<StatusCode> statusCode) {
        this.statusCode = statusCode;
    }

    public LinkedList<StatusCode> getStatusCode() {
        return statusCode;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        StringBuilder output = new StringBuilder();

        statusCode.forEach((t) -> {
            output.append(t.name());
            output.append(" - ");
            output.append(t.getHttpStatus());
            output.append(" - ");
            output.append(t.getMessage());
            output.append("\n");
        });

        return output.toString();
    }

}
