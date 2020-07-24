package org.ntr.persistence.daos.reservation;

import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.daos.screening.DefaultScreeningDao;
import org.ntr.persistence.daos.user.DefaultUserDao;
import org.ntr.persistence.entities.ReservationEntity;
import org.ntr.persistence.entities.ScreeningEntity;
import org.ntr.persistence.entities.UserEntity;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.relations.Relation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultReservationDao extends AbstractGenericDao<ReservationEntity> implements ReservationDao {

    private static final String SUBSELECT_QUERY = "(SELECT * FROM users WHERE users.id=%s), (SELECT * FROM screenings WHERE screenings.id=%s)";
    private static final String INSERT_QUERY = "INSERT INTO reservations VALUES (%s, %s);";

    private static final String TABLE_NAME = "reservations";

    public DefaultReservationDao() {
        super(TABLE_NAME);
    }

    @Override
    protected boolean containsReferences() {
        return false;
    }

    @Override
    protected Long getEntityId(ReservationEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(ReservationEntity entity, Long id) {
        entity.setId(id);
    }

    @Override
    protected ReservationEntity getDatabaseResults(ResultSet rs) throws SQLException {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setId(rs.getLong("id"));
        reservation.setReservationName(rs.getString("reservation_name"));
        reservation.setSeatsToReserve(rs.getInt("num_reserved_seats"));
        reservation.setExternalId(rs.getString("external_id"));

        return reservation;
    }

    @Override
    protected String setupUpdateQuery(ReservationEntity entity) throws SQLException {
        return null;
    }

    @Override
    protected void saveEntityCollections(ReservationEntity entity) {
        return;
    }

    @Override
    protected String buildInsertWithReferencesQuery(ReservationEntity entity) {
        String innerQuery = String.format(SUBSELECT_QUERY, entity.getUserEntity().getId(), entity.getScreeningEntity().getId());
        String query = String.format(INSERT_QUERY, entity.toString(), innerQuery);
        return query;
    }

    @Override
    public List<ReservationEntity> loadAllByScreening(ScreeningEntity screeningEntity) {
        String query = String.format("SELECT * FROM reservations WHERE reservations.screening_id=%s", screeningEntity.getId());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<ReservationEntity> reservations = new ArrayList<>();
            while (resultSet.next()) {
                ReservationEntity reservation = getDatabaseResults(resultSet);
                reservation.setScreeningEntity(screeningEntity);
                loadRelations(reservation, resultSet);
                reservations.add(reservation);
            }
            return reservations;

        } catch (SQLException ex) {

        } finally {
            closeDatabaseResources(resultSet, preparedStatement);
            return null;
        }
    }

    @Override
    public List<ReservationEntity> loadAllByUser(UserEntity userEntity) {
        String query = String.format("SELECT * FROM reservations WHERE reservations.user_id = %s", userEntity.getId());
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            List<ReservationEntity> reservations = new ArrayList<>();
            while(resultSet.next()) {
                ReservationEntity reservation = getDatabaseResults(resultSet);
                reservation.setUserEntity(userEntity);
                loadRelations(reservation, resultSet);
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException ex) {

        } finally {
            closeDatabaseResources(resultSet, preparedStatement);
        }
        return null;
    }

    private void loadRelations(ReservationEntity reservation, final ResultSet rs) throws SQLException {
        if (Objects.isNull(reservation.getScreeningEntity())) {
            Relation relation = reservation.getEntityRelationMap().get(ScreeningEntity.class);
            if (relation.getOption().getFetchType().name().equals(FetchType.EAGER.name())) {
                reservation.setScreeningEntity(new DefaultScreeningDao().loadById(rs.getLong("screening_id")));
            }
        }

        if (Objects.isNull(reservation.getUserEntity())) {
            Relation relation = reservation.getEntityRelationMap().get(UserEntity.class);
            if (relation.getOption().getFetchType().name().equals(FetchType.EAGER.name())) {
                reservation.setUserEntity(new DefaultUserDao().loadById(rs.getLong("user_id")));
            }
        }

    }
}
