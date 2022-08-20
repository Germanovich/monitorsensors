package com.germanovich.monitorsensors.common.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private String massageForUser = "App Error";

    public ApplicationException(final Exception ex) {
        super(ex);
    }

    public ApplicationException(final String message, final Exception ex) {
        super(message + ":\n" + ex);
        massageForUser = message;
    }
}
