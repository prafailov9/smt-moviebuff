package org.ntr.persistence.daos.screening;

import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.daos.movie.DefaultMovieDao;
import org.ntr.persistence.daos.reservation.DefaultReservationDao;
import org.ntr.persistence.daos.theater.DefaultTheaterDao;
import org.ntr.persistence.entities.MovieEntity;
import org.ntr.persistence.entities.ScreeningEntity;
import org.ntr.persistence.entities.TheaterEntity;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.relations.Entity;
import org.ntr.persistence.entities.relations.Relation;
import org.ntr.persistence.exceptions.RelatedEntityIsNullException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultScreeningDao extends AbstractGenericDao<ScreeningEntity> implements ScreeningDao {

    private static final String SUBSELECT_QUERY = "(SELECT * FROM movies WHERE movies.id=%s), (SELECT * FROM theaters WHERE theaters.id=%s)";
    private static final String INSERT_QUERY = "INSERT INTO screenings VALUES (%s, %s);";
    private static final String TABLE_NAME = "screenings";
    private static final String SELECT_ALL_BY_MOVIE_QUERY = "SELECT * FROM screenings WHERE screenings.movie_id=?";

    public DefaultScreeningDao() {
        super(TABLE_NAME);
    }


    @Override
    protected boolean containsReferences() {
        return true;
    }

    @Override
    protected Long getEntityId(ScreeningEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(ScreeningEntity entity, Long id) {
        entity.setId(id);
    }

    @Override
    protected ScreeningEntity getDatabaseResults(ResultSet rs) throws SQLException {
        ScreeningEntity screening = new ScreeningEntity();
        screening.setId(rs.getLong("id"));
        screening.setStartTime(rs.getTimestamp("start_time"));
        screening.setEndTime(rs.getTimestamp("end_time"));
        screening.setExternalId(rs.getString("external_id"));

        loadRelations(screening, rs);
        return screening;
    }

    @Override
    protected String setupUpdateQuery(ScreeningEntity entity) throws SQLException {
        return null;
    }

    /**
     * Screening does not hold any entity collections
     *
     * @param entity
     */
    @Override
    protected void saveEntityCollections(ScreeningEntity entity) {
        DefaultReservationDao reservationDao = new DefaultReservationDao();
        entity.getReservationEntities().forEach(reservationEntity -> reservationDao.save(reservationEntity));
    }

    @Override
    protected String buildInsertWithReferencesQuery(ScreeningEntity entity) {
//        String innerQuery = String.format(SUBSELECT_QUERY, entity.getMovieEntity().getId(), entity.getTheaterEntity().getId());
        checkReferencesValidity(entity.getMovieEntity());
        checkReferencesValidity(entity.getTheaterEntity());
        String  foreignKeys = String.format("%s, %s", entity.getMovieEntity().getId(), entity.getTheaterEntity().getId());
        String query = String.format(INSERT_QUERY, entity.toString(), foreignKeys);
        return query;
    }

    @Override
    public List<ScreeningEntity> loadAllByMovie(MovieEntity movieEntity) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_BY_MOVIE_QUERY);
            preparedStatement.setLong(1, movieEntity.getId());
            resultSet = preparedStatement.executeQuery();
            List<ScreeningEntity> screenings = new ArrayList<>();
            while (resultSet.next()) {
                ScreeningEntity screeningEntity = getDatabaseResults(resultSet);
                screeningEntity.setMovieEntity(movieEntity);
                loadRelations(screeningEntity, resultSet);
                screenings.add(screeningEntity);
            }
            return screenings;
        } catch (SQLException ex) {

        } finally {
            closeDatabaseResources(resultSet, preparedStatement);
        }
        return null;
    }

    private <T extends Entity> void checkReferencesValidity(T entity) {
        if(Objects.isNull(entity) || Objects.isNull(entity.getId()))
            throw new RelatedEntityIsNullException();
    }

    private void loadRelations(ScreeningEntity screening, final ResultSet resultSet) throws SQLException {
        if (Objects.nonNull(screening.getMovieEntity())) {
            Relation movieRel = screening.getEntityRelationMap().get(MovieEntity.class);
            if (movieRel.getOption().getFetchType().name().equals(FetchType.EAGER.name()))
                screening.setMovieEntity(new DefaultMovieDao().loadById(resultSet.getLong("movie_id")));
        }

        if (Objects.isNull(screening.getMovieEntity())) {
            Relation theaterRel = screening.getEntityRelationMap().get(TheaterEntity.class);
            if (theaterRel.getOption().getFetchType().name().equals(FetchType.EAGER.name()))
                screening.setTheaterEntity(new DefaultTheaterDao().loadById(resultSet.getLong("theater_id")));
        }

        if (Objects.isNull(screening.getReservationEntities()) && !screening.getReservationEntities().isEmpty()) {
            Relation reservationRel = screening.getEntityRelationMap().get(MovieEntity.class);
            if (reservationRel.getOption().getFetchType().name().equals(FetchType.EAGER.name())) {
                screening.setReservationEntities(new DefaultReservationDao().loadAllByScreening(screening));
            }
        }

    }

}
