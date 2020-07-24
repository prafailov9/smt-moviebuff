package org.ntr.persistence.daos.screening;

import org.ntr.persistence.entities.MovieEntity;
import org.ntr.persistence.entities.ScreeningEntity;

import java.util.List;

public interface ScreeningDao {
    List<ScreeningEntity> loadAllByMovie(MovieEntity movieEntity);
}
