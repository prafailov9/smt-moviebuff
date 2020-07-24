package org.ntr.persistence.exceptions;

public class ConnectorNotInitializedException extends RuntimeException {

    private static final String MESSAGE = "Database Connector not initialized! initialize() must be called before retrieval...";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
