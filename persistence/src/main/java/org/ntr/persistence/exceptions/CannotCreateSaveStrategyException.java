package org.ntr.persistence.exceptions;

public class CannotCreateSaveStrategyException extends RuntimeException {

    private static final String MESSAGE = "cannot create save strategy!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
