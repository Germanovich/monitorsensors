package com.hermanovich.accountingsystem.util;

public enum MessageForUser {

    NOT_FOUND_ALL_USER_DATA("Not found all user data"),

    NOT_FOUND_ALL_CORRECT_DATA("Not found all correct data"),

    WRONG_ID("Wrong id"),
    ERROR_DURING_SAVE("Error during save"),
    TOKEN_IS_INVALID("Token is invalid"),
    USER_IS_NOT_FOUND("User is not found"),
    USER_IS_NOT_AUTHORIZED("User is not authorized"),
    AUTHENTICATION_EXCEPTION("Authentication exception"),
    INVALID_TOKEN("Invalid token"),
    CANNOT_DELETE("Cannot delete"),
    REQUEST_METHOD_NOT_SUPPORTED("Request method not supported"),
    MEDIA_TYPE_NOT_SUPPORTED("Media type not supported"),
    SERVER_ERROR("Server error"),
    MEDIA_TYPE_NOT_ACCEPTABLE("Media type not acceptable"),
    BAD_REQUEST("Bad request"),
    NO_HANDLER_FOUND("No handler found"),
    SERVICE_UNAVAILABLE("Service unavailable"),
    WORD_IS_MANDATORY("Word is mandatory");

    private final String message;

    MessageForUser(String message) {
        this.message = message;
    }

    public String get() {
        return (this.message);
    }
}
