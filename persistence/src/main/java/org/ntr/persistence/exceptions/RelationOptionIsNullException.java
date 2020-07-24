package org.ntr.persistence.exceptions;

public class RelationOptionIsNullException extends RuntimeException {

    private static final String MESSAGE = "Relation options cannot be null!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
