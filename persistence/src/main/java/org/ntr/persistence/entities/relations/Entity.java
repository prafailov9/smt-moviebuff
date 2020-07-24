package org.ntr.persistence.entities.relations;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.IdGenerationType;
import org.ntr.persistence.entities.enums.RelationType;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class Entity implements Persistable {

    private Long id;

    private EntityRelationHolder entityRelationHolder = new EntityRelationHolder();
    private IdGenerationType idGenerationType = IdGenerationType.IDENTITY;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public IdGenerationType getIdGenerationType() { return idGenerationType; }

    public Map<Class<? extends Entity>, Relation> getEntityRelationMap() {
        return entityRelationHolder.getEntityRelationMap();
    }

    protected void setIdGenerationType(final IdGenerationType idGenerationType) {
        this.idGenerationType = idGenerationType;
    }

    protected void setRelation(RelationType relationType, boolean nullable, CascadeType cascadeType, FetchType fetchType, Class<? extends Entity> entityClass) {
        final Relation relation = createRelation(relationType, nullable, cascadeType, fetchType);
        entityRelationHolder.addEntityRelation(entityClass, relation);
    }

    private Relation createRelation(RelationType relationType, boolean nullable, CascadeType cascadeType, FetchType fetchType) {
        nullSettingsCheck(Optional.of(relationType), Optional.of(cascadeType), Optional.of(fetchType));
        final RelationOption option = new RelationOption(cascadeType, fetchType);
        final Relation relation = new Relation(relationType, option, nullable);
        return relation;
    }

    private void nullSettingsCheck(Optional<Object>... params) {
        if(Objects.isNull(params)) throw new RuntimeException();
        for(int i = 0; i < params.length; i++) {
            Objects.requireNonNull(params[i].orElseThrow(() -> new RuntimeException()));
        }
    }

}
