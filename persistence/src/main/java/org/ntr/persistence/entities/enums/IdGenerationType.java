package org.ntr.persistence.entities.enums;

public enum IdGenerationType {
    TABLE,
    SEQUENCE,
    IDENTITY,
    AUTO;

    private IdGenerationType() {
    }
}
