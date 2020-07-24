package org.ntr.persistence.entities;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.RelationType;
import org.ntr.persistence.entities.relations.Entity;

import java.util.Objects;

public class SeatEntity extends Entity {

    private Long id;
    private int seatNumber;
    private TheaterEntity theaterEntity; // one seat is unique to one hall
    private String externalId;

    public SeatEntity() {
        setRelation(RelationType.MANY_2_ONE, false, CascadeType.ALL, FetchType.EAGER, TheaterEntity.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TheaterEntity getTheaterEntity() {
        return theaterEntity;
    }

    public void setTheaterEntity(TheaterEntity theaterEntity) {
        this.theaterEntity = theaterEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatEntity seatEntity = (SeatEntity) o;
        return Objects.equals(id, seatEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ", " + seatNumber + "";
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
