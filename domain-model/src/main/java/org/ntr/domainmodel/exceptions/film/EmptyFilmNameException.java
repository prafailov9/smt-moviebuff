package org.ntr.domainmodel.exceptions.film;

import org.ntr.domainmodel.exceptions.EntityFieldValidationException;

public class EmptyFilmNameException extends EntityFieldValidationException {

    private static final String MESSAGE = "Film name cannot be null!";

    public EmptyFilmNameException() {
        super(MESSAGE);
    }
}
