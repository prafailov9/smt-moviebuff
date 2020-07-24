package org.ntr.persistence.exceptions;

public class CannotPersistEntityException extends RuntimeException {

    private final static String MESSAGE = "Entity cannot be persisted!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
