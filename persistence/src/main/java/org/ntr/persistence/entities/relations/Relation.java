package org.ntr.persistence.entities.relations;

import org.ntr.persistence.entities.enums.RelationType;

public class Relation {

    private RelationType type;
    private RelationOption option;
    private boolean nullable;

    public Relation(final RelationType type, final RelationOption option, final boolean nullable) {
        this.type = type;
        this.option = option;
        this.nullable = nullable;
    }

    public RelationType getType() {
        return type;
    }
    public RelationOption getOption() {
        return option;
    }
    public boolean isNullable() {
        return nullable;
    }

}
