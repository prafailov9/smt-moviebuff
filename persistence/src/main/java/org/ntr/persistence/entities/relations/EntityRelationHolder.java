package org.ntr.persistence.entities.relations;

import java.util.HashMap;
import java.util.Map;

public class EntityRelationHolder {

    private Map<Class<? extends Entity>, Relation> entityRelationMap = new HashMap<>();

    public void addEntityRelation(Class<? extends Entity> clazz, Relation relation) {
        entityRelationMap.put(clazz, relation);
    }

    public Map<Class<? extends Entity>, Relation> getEntityRelationMap() { return entityRelationMap; }

}
