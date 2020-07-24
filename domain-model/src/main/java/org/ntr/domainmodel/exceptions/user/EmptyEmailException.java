package org.ntr.domainmodel.exceptions.user;

import org.ntr.domainmodel.exceptions.EntityFieldValidationException;

public class EmptyEmailException extends EntityFieldValidationException {

    private static final String MESSAGE = "Email field cannot be NULL!";

    public EmptyEmailException() {
        super(MESSAGE);
    }
}
