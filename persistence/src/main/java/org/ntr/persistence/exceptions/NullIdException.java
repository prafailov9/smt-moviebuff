package org.ntr.persistence.exceptions;

public class NullIdException extends RuntimeException {

    private final static String MESSAGE = "Identifier is null!";


    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
