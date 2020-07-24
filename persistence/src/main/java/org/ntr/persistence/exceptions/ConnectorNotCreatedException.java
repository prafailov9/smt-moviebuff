package org.ntr.persistence.exceptions;

public class ConnectorNotCreatedException extends RuntimeException {

    private static final String MESSAGE = "Could not create Database Connector!";

    public ConnectorNotCreatedException() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
