package org.ntr.persistence.exceptions;

public class CouldNotLoadPropertiesException extends RuntimeException {

    private static final String MESSAGE = "Could not load properties! File might not exist, wrong path given, or data is invalid!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
