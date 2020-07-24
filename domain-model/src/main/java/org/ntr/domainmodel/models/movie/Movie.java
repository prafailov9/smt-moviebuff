package org.ntr.domainmodel.models.movie;


import org.ntr.domainmodel.models.Model;
import org.ntr.domainmodel.models.screening.Screening;
import org.ntr.domainmodel.exceptions.film.EmptyFilmNameException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Movie extends Model {

    private String movieName; // non-null;
    private String description;
    private double rating; // decimal from 0.0 to 10

    private LocalDate releaseDate;

    private MovieGenre movieGenre;
    private Duration duration;
    private List<Screening> screenings;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        if (Objects.isNull(movieName)) throw new EmptyFilmNameException();
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

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieName.equals(movie.movieName) &&
                getExternalId().equals(movie.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, getExternalId());
    }
}
