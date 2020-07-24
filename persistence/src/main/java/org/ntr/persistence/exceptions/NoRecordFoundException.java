package org.ntr.persistence.exceptions;

public class NoRecordFoundException extends RuntimeException {

    private final static String MESSAGE = "No records found in the database!";

    public NoRecordFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
