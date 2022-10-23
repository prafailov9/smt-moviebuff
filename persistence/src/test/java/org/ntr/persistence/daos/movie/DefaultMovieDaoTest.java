package org.ntr.persistence.daos.movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ntr.domainmodel.models.movie.MovieGenre;
import org.ntr.persistence.coredb.AbstractGenericDaoTest;
import org.ntr.persistence.daos.theater.DefaultTheaterDao;
import org.ntr.persistence.entities.ScreeningEntity;
import org.ntr.persistence.entities.TheaterEntity;
import org.ntr.persistence.exceptions.NoRecordFoundException;
import org.ntr.persistence.entities.MovieEntity;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

class DefaultMovieDaoTest extends AbstractGenericDaoTest<MovieEntity> {

    private DefaultMovieDao movieDao;

    private MovieEntity movieEntity;

    private ScreeningEntity screeningEntity;

    @BeforeEach
    public void setUp() {
        movieDao = new DefaultMovieDao();
        movieEntity = new MovieEntity();

    }

    @Test
    public void saveMovieDtoTest() {
        movieEntity.setExternalId(UUID.randomUUID().toString());
        movieEntity.setDescription("desc");
        movieEntity.setRating(0.1);
        movieEntity.setMovieGenre(MovieGenre.ACTION.getMovieGenre());
        movieEntity.setDuration(Time.valueOf(LocalTime.now()));
        movieEntity.setReleaseDate(Timestamp.valueOf(LocalDateTime.now()));

        ScreeningEntity screeningEntity = createScreening();
        movieEntity.setScreenings(List.of(screeningEntity));

        MovieEntity savedDto = movieDao.save(movieEntity);

        Assertions.assertNotNull(savedDto);
        Assertions.assertEquals(movieEntity, savedDto);

    }

    @Test
    public void loadMovieByIdTest() {
        MovieEntity dto = movieDao.loadById(getRandomId());
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertTrue(dto.getId() >= 1);
    }

    @Test
    public void loadAllMoviesTest() {
        List<MovieEntity> movieEntities = movieDao.loadAll();
        Assertions.assertNotNull(movieEntities);
    }

    @Test
    public void deleteByIdNullIdTest() {
        Long id = null;
        Assertions.assertThrows(NoRecordFoundException.class, () -> movieDao.deleteById(id));
    }

    @Test
    public void deleteMovieDtoTest() {
        Long id = getRandomId();
        MovieEntity dto = movieDao.loadById(id);
        boolean success = movieDao.delete(dto);
        Assertions.assertTrue(success);
        Assertions.assertNull(dto.getId()); // row as well as DTO should be deleted;
    }

    @Test
    public void deleteNonExistingRecordTest() {
        Long id = -1L;
        Assertions.assertThrows(NoRecordFoundException.class, () -> movieDao.loadById(id)); // has to throw na exception if no record was found
    }

    //    @Test
    public void updateMovieDtoTest() {
        MovieEntity dto = getRandomMovieRecord();
        Long id = dto.getId();
        dto.setDescription("newDesc");
        dto.setMovieGenre(MovieGenre.ACTION.getMovieGenre());
        dto.setRating(1.2D);
        boolean success = movieDao.update(dto);

        Assertions.assertTrue(success);

        MovieEntity updatedDto = movieDao.loadById(id);
        Assertions.assertNotNull(updatedDto);
        Assertions.assertEquals(dto.getDescription(), updatedDto.getDescription());
        Assertions.assertEquals(dto.getMovieGenre(), updatedDto.getMovieGenre());
        Assertions.assertEquals(dto.getRating(), updatedDto.getRating(), 0.01);
    }

    @Override
    protected List<MovieEntity> getRecords() {
        return movieDao.loadAll();
    }

    @Override
    protected Long getDtoId(MovieEntity dto) {
        return dto.getId();
    }

    private ScreeningEntity createScreening() {

        ScreeningEntity screeningEntity = new ScreeningEntity();
        screeningEntity.setExternalId(UUID.randomUUID().toString());
        screeningEntity.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        screeningEntity.setEndTime(Timestamp.valueOf(LocalDateTime.now().plusHours(2)));

        screeningEntity.setMovieEntity(movieEntity);
        TheaterEntity theaterEntity = new DefaultTheaterDao().loadById(getRandomId());

        screeningEntity.setTheaterEntity(theaterEntity);

        return screeningEntity;
    }

    private MovieEntity getRandomMovieRecord() {
        return getRecords().get(new Random().nextInt(getRecords().size()));
    }






}