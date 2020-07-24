package org.ntr.domainmodel.models.movie;

public enum MovieGenre {

    DRAMA("Drama"),
    COMEDY("Comedy"),
    ACTION("Action"),
    HORROR("Horror");

    private final String movieGenre;

     MovieGenre(final String movieGenre) {
        this.movieGenre = movieGenre;
    }


    public String getMovieGenre() {
         return movieGenre;
    }

}
