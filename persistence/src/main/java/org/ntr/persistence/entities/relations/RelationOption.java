package org.ntr.persistence.entities.relations;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;

public class RelationOption {

    private CascadeType cascadeType;
    private FetchType fetchType;

    public RelationOption(final CascadeType cascadeType, final FetchType fetchType) {
        this.cascadeType = cascadeType;
        this.fetchType = fetchType;
    }

    public CascadeType getCascadeType() {
        return cascadeType;
    }

    public FetchType getFetchType() {
        return fetchType;
    }

}
