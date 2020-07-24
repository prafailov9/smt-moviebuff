package org.ntr.persistence.exceptions;

public class MalformedSQLQueryException extends RuntimeException {

    private static final String MESSAGE = "given query contains unknown syntax!";

    @Override
    public String getMessage() {
        return MESSAGE;

    }

}

