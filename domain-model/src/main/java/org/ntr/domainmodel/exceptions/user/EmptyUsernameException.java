package org.ntr.domainmodel.exceptions.user;

import org.ntr.domainmodel.exceptions.EntityFieldValidationException;

public class EmptyUsernameException extends EntityFieldValidationException {

    private static final String MESSAGE = "Username field cannot be NULL!";

    public EmptyUsernameException() {
        super(MESSAGE);
    }

}


