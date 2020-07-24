package org.ntr.persistence.entities;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.RelationType;
import org.ntr.persistence.entities.relations.Entity;

import java.util.List;
import java.util.Objects;

public class TheaterEntity extends Entity {

    private Long id;

    private String theaterName;
    private TheaterAddressEntity theaterAddressEntity;
    private String externalId;

    private List<SeatEntity> seats;
    private List<ScreeningEntity> screenings;

    public TheaterEntity() {
        setRelation(RelationType.ONE_2_MANY, true, CascadeType.ALL, FetchType.LAZY, ScreeningEntity.class);
        setRelation(RelationType.ONE_2_MANY, true, CascadeType.ALL, FetchType.LAZY, SeatEntity.class);
        setRelation(RelationType.ONE_2_ONE, false, CascadeType.ALL, FetchType.EAGER, TheaterAddressEntity.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public TheaterAddressEntity getTheaterAddressEntity() {
        return theaterAddressEntity;
    }

    public void setTheaterAddressEntity(TheaterAddressEntity theaterAddressEntity) {
        this.theaterAddressEntity = theaterAddressEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheaterEntity theaterEntity = (TheaterEntity) o;
        return Objects.equals(id, theaterEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ", '" + theaterName + "'";

    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }

    public List<ScreeningEntity> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<ScreeningEntity> screenings) {
        this.screenings = screenings;
    }
}
