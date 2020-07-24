package org.ntr.persistence.entities;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.RelationType;
import org.ntr.persistence.entities.relations.Entity;

import java.util.List;
import java.util.Objects;

public class UserEntity extends Entity {

    private Long id;

    private String email;
    private String username;
    private String password;
    private String externalId;

    private RoleEntity roleEntity; // one user can have only one role in the app
    private List<ReservationEntity> reservations;

    public UserEntity() {
        setRelation(RelationType.ONE_2_MANY, true, CascadeType.ALL, FetchType.LAZY, ReservationEntity.class);
        setRelation(RelationType.MANY_2_ONE, true, CascadeType.ALL, FetchType.LAZY, RoleEntity.class);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(id, userEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ", '" + username + "', '" + password + "', '" + email + "'";
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
