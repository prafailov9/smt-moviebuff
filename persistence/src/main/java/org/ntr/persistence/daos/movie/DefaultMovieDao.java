package org.ntr.persistence.daos.movie;

import org.apache.commons.collections4.CollectionUtils;
import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.daos.screening.DefaultScreeningDao;
import org.ntr.persistence.entities.MovieEntity;
import org.ntr.persistence.entities.ScreeningEntity;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.relations.Relation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DefaultMovieDao extends AbstractGenericDao<MovieEntity> implements MovieDao {

    private static final String TABLE_NAME = "movies";

    public DefaultMovieDao() {
        super(TABLE_NAME);
    }

    @Override
    protected Long getEntityId(MovieEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(MovieEntity entity, Long id) {
        entity.setId(id);
    }

    @Override
    protected boolean containsReferences() {
        return false;
    }

    /**
     * Retrieving the current row of the result set. gets the data by column index since its more efficient.
     * @param rs
     * @return
     * @throws SQLException
     */
    @Override
    protected MovieEntity getDatabaseResults(ResultSet rs) throws SQLException {
        final MovieEntity movie = new MovieEntity();
        movie.setId(rs.getLong(1));
        movie.setExternalId(rs.getString(2));
        movie.setMovieName(rs.getString(3));
        movie.setDuration(rs.getTime(4));
        movie.setDescription(rs.getString(5));
        movie.setRating(rs.getDouble(6));
        movie.setMovieGenre(rs.getString(7));
        movie.setReleaseDate(rs.getTimestamp(8));

        Relation relation = movie.getEntityRelationMap().get(ScreeningEntity.class);
        if(relation.getOption().getFetchType().name().equals(FetchType.EAGER.name())) {
            List<ScreeningEntity> screeningEntities = new DefaultScreeningDao().loadAllByMovie(movie);
            movie.setScreenings(screeningEntities);
        }

        return movie;
    }

    @Override
    protected String setupUpdateQuery(MovieEntity entity) throws SQLException {
        final String updateQuery = String.format("update %s set id=%s, external_id='%s', name='%s', duration='%s', description='%s', rating=%s, genre='%s', release_date='%s' where id=%s",
                tableName, entity.getId(), entity.getExternalId(),  entity.getMovieName(), entity.getDuration(), entity.getDescription(), entity.getRating(), entity.getMovieGenre(),
                entity.getReleaseDate(), entity.getId());
        return updateQuery;
    }

    @Override
    protected void saveEntityCollections(MovieEntity entity) {
        if(CollectionUtils.isNotEmpty(entity.getScreenings()))
            entity.getScreenings().forEach(screening -> new DefaultScreeningDao().save(screening));
    }


    @Override
    protected String buildInsertWithReferencesQuery(MovieEntity entity) {
        return null;
    }


}
