package org.ntr.persistence.entities;

import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.RelationType;
import org.ntr.persistence.entities.relations.Entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class ScreeningEntity extends Entity {
    private Long id;

    // end and start time depend on each other for their init and creation of duration
    private Timestamp startTime; // should not be null
    private Timestamp endTime; // should not be null

    private MovieEntity movieEntity;
    private TheaterEntity theaterEntity;
    private String externalId;

    private List<ReservationEntity> reservationEntities;

    public ScreeningEntity() {
        setRelation(RelationType.MANY_2_ONE, false, CascadeType.ALL, FetchType.EAGER, MovieEntity.class);
        setRelation(RelationType.MANY_2_ONE, false, CascadeType.ALL, FetchType.EAGER, TheaterEntity.class);
        setRelation(RelationType.ONE_2_MANY, true, CascadeType.ALL, FetchType.LAZY, ReservationEntity.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
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
        ScreeningEntity that = (ScreeningEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s, '%s', '%s', '%s'", id, externalId, startTime, endTime);
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<ReservationEntity> getReservationEntities() {
        return reservationEntities;
    }

    public void setReservationEntities(List<ReservationEntity> reservationEntities) {
        this.reservationEntities = reservationEntities;
    }
}
