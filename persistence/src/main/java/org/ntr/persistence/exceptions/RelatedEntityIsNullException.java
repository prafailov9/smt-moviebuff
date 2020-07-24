package org.ntr.persistence.exceptions;

public class RelatedEntityIsNullException extends RuntimeException {

    private static final String MESSAGE = "Entity cannot be null in relation-retrieval context!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
