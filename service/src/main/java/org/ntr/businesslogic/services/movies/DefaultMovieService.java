package org.ntr.businesslogic.services.movies;

import org.ntr.persistence.exceptions.NoRecordFoundException;
import org.ntr.persistence.daos.movie.DefaultMovieDao;
import org.ntr.persistence.entities.MovieEntity;

public class DefaultMovieService implements MovieService {


    private DefaultMovieDao movieDao = new DefaultMovieDao();


    @Override
    public MovieEntity getMovieById(Long id) {
        MovieEntity dto = null;
        try {
            dto = movieDao.loadById(id);

        } catch (NoRecordFoundException ex) {
        }
        return dto;
    }
}
