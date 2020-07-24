package org.ntr.persistence.entities;

import org.ntr.persistence.entities.relations.Entity;
import org.ntr.persistence.entities.enums.CascadeType;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.enums.RelationType;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MovieEntity extends Entity {

    private Long id;

    private String movieName; // non-null;
    private String description;
    private double rating;

    private Time duration;
    private Timestamp releaseDate;
    private String movieGenre;
    private String externalId;

    private List<ScreeningEntity> screenings;

    public MovieEntity() {
        setRelation(RelationType.ONE_2_MANY, true, CascadeType.ALL, FetchType.LAZY, ScreeningEntity.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    @Override
    public String toString() {
        return id + ", '"+ externalId +"', '" + movieName + "', '" + duration + "', '" + description + "', " + rating + ", '" + movieGenre + "', '" + releaseDate + "'";
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<ScreeningEntity> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<ScreeningEntity> screenings) {
        this.screenings = screenings;
    }
}
