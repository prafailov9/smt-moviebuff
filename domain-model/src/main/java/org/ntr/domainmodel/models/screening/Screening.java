package org.ntr.domainmodel.models.screening;


import org.ntr.domainmodel.models.movie.Movie;
import org.ntr.domainmodel.models.theater.Theater;
import org.ntr.domainmodel.exceptions.EntityFieldValidationException;
import org.ntr.domainmodel.models.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Screening extends Model {

    private String screeningName; // should not be null


    // end and start time depend on each other for their init and creation of duration
    private LocalDateTime startTime; // should not be null
    private LocalDateTime endTime; // should not be null

    private Movie movie; // cannot be null!!!!!!!

    private Theater theater;


    public String getScreeningName() {
        return screeningName;
    }

    public void setScreeningName(String screeningName) {
        if (Objects.isNull(screeningName)) throw new EntityFieldValidationException("screening name cannot be null");
        this.screeningName = screeningName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        checkStartEndTime(startTime, endTime);
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        checkStartEndTime(startTime, endTime);
        this.endTime = endTime;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return screeningName.equals(screening.screeningName) &&
                getExternalId().equals(screening.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(screeningName, getExternalId());
    }

    @Override
    public String toString() {
        return String.format("Screening{screeningName='%s'}", screeningName);
    }

    private void checkStartEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        // method to check validity of start and end time objects.
        if (Objects.isNull(startTime) || Objects.isNull(endTime))
            throw new EntityFieldValidationException("Start and End time cannot be null!");
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime))
            throw new EntityFieldValidationException("Start time cannot be equal or after End time!");

    }

}
