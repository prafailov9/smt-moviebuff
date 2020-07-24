package org.ntr.businesslogic.services.movies;

import org.ntr.persistence.entities.MovieEntity;

public interface MovieService {

    MovieEntity getMovieById(Long id);

}
