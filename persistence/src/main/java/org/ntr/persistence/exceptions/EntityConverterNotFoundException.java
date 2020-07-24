package org.ntr.persistence.exceptions;

public class EntityConverterNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Entity converter not found!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
