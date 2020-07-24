package org.ntr.persistence.entities;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.RelationType;
import org.ntr.persistence.entities.relations.Entity;

import java.util.Objects;

public class ReservationEntity extends Entity {

    private Long reservationId;
    private String reservationName;
    private int seatsToReserve;
    private UserEntity userEntity;
    private ScreeningEntity screeningEntity;
    private String externalId;

    public ReservationEntity() {
        setRelation(RelationType.MANY_2_ONE, false, CascadeType.ALL, FetchType.EAGER, UserEntity.class);
        setRelation(RelationType.MANY_2_ONE, false, CascadeType.ALL, FetchType.EAGER, ScreeningEntity.class);
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public int getSeatsToReserve() {
        return seatsToReserve;
    }

    public void setSeatsToReserve(int seatsToReserve) {
        this.seatsToReserve = seatsToReserve;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ScreeningEntity getScreeningEntity() {
        return screeningEntity;
    }

    public void setScreeningEntity(ScreeningEntity screeningEntity) {
        this.screeningEntity = screeningEntity;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getId() + ", '" + reservationName + "', " + seatsToReserve + "";
    }

    @Override
    public Long getId() {
        return reservationId;
    }
}
