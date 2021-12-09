package io.github.ppetrbednar.tmdb.wrappers.response;

import java.util.LinkedList;

/**
 *
 * @author Petr Bednář
 */
public enum StatusCode {
    C1(200, "Success."),
    C2(501, "Invalid service: this service does not exist."),
    C3(401, "Authentication failed: You do not have permissions to access the service."),
    C4(405, "Invalid format: This service doesn't exist in that format."),
    C5(422, "Invalid parameters: Your request parameters are incorrect."),
    C6(404, "Invalid id: The pre-requisite id is invalid or not found."),
    C7(401, "Invalid API key: You must be granted a valid key."),
    C8(403, "Duplicate entry: The data you tried to submit already exists."),
    C9(503, "Service offline: This service is temporarily offline, try again later."),
    C10(401, "Suspended API key: Access to your account has been suspended, contact TMDB."),
    C11(500, "Internal error: Something went wrong, contact TMDB."),
    C12(201, "The item/record was updated successfully."),
    C13(200, "The item/record was deleted successfully."),
    C14(401, "Authentication failed."),
    C15(500, "Failed."),
    C16(401, "Device denied."),
    C17(401, "Session denied."),
    C18(400, "Validation failed."),
    C19(406, "Invalid accept header."),
    C20(422, "Invalid date range: Should be a range no longer than 14 days."),
    C21(200, "Entry not found: The item you are trying to edit cannot be found."),
    C22(400, "Invalid page: Pages start at 1 and max at 1000. They are expected to be an integer."),
    C23(400, "Invalid date: Format needs to be YYYY-MM-DD."),
    C24(504, "Your request to the backend server timed out. Try again."),
    C25(429, "Your request count (#) is over the allowed limit of (40)."),
    C26(400, "You must provide a username and password."),
    C27(400, "Too many append to response objects: The maximum number of remote calls is 20."),
    C28(400, "Invalid timezone: Please consult the documentation for a valid timezone."),
    C29(400, "You must confirm this action: Please provide a confirm=true parameter."),
    C30(401, "Invalid username and/or password: You did not provide a valid login."),
    C31(401, "Account disabled: Your account is no longer active. Contact TMDB if this is an error."),
    C32(401, "Email not verified: Your email address has not been verified."),
    C33(401, "Invalid request token: The request token is either expired or invalid."),
    C34(404, "The resource you requested could not be found."),
    C35(401, "Invalid token."),
    C36(401, "This token hasn't been granted write permission by the user."),
    C37(404, "The requested session could not be found."),
    C38(401, "You don't have permission to edit this resource."),
    C39(401, "This resource is private."),
    C40(200, "Nothing to update."),
    C41(422, "This request token hasn't been approved by the user."),
    C42(405, "This request method is not supported for this resource."),
    C43(502, "Couldn't connect to the backend server."),
    C44(500, "The ID is invalid."),
    C45(403, "This user has been suspended."),
    C46(503, "The API is undergoing maintenance. Try again later."),
    C47(400, "The input is not valid."),
    UNKNOWN(0, "Unknown");

    private final int httpStatus;
    private final String message;

    private StatusCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public static StatusCode getValueOf(int code) {
        return getValueOf(code + "");
    }

    public static StatusCode getValueOf(String code) {
        try {
            return valueOf("C" + code);
        } catch (Exception ex) {
            return UNKNOWN;
        }
    }

    public static LinkedList<StatusCode> getValuesForHttpStatus(int httpStatus) {
        LinkedList<StatusCode> output = new LinkedList<>();

        for (StatusCode val : StatusCode.values()) {
            if (val.getHttpStatus() == httpStatus) {
                output.add(val);
            }
        }

        return output;
    }
}
