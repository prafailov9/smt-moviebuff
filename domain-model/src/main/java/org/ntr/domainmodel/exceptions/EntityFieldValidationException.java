package org.ntr.domainmodel.exceptions;

public class EntityFieldValidationException extends RuntimeException {

    private String message;

    public EntityFieldValidationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
