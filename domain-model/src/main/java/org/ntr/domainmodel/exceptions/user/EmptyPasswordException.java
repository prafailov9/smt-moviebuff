package org.ntr.domainmodel.exceptions.user;

import org.ntr.domainmodel.exceptions.EntityFieldValidationException;

public class EmptyPasswordException extends EntityFieldValidationException {

    private static final String MESSAGE = "Password field cannot be NULL!";

    public EmptyPasswordException() {
        super(MESSAGE);
    }
}
