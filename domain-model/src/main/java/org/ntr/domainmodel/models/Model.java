package org.ntr.domainmodel.models;

import java.util.UUID;

public abstract class Model {

    private String externalId;

    protected Model() {
        externalId = UUID.randomUUID().toString();
    }

    public String getExternalId() {
        return externalId;
    }

}
