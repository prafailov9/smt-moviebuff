package org.ntr.persistence.exceptions;

public class NoSuchEntityException extends RuntimeException {

    private final static String MESSAGE = "Entity does not exist!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
