package org.ntr.businesslogic.services.movies;

import org.junit.jupiter.api.BeforeEach;
import org.ntr.persistence.entities.MovieEntity;

class DefaultMovieServiceTest {

    private DefaultMovieService service;


    @BeforeEach
    public void setUp() {
        service = new DefaultMovieService();
    }

    //    @Test
    public void loadMovieByIdTest() {
        MovieEntity dto = service.getMovieById(1L);
    }
}