package org.ntr.persistence.daos.seat;

import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.entities.SeatEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultSeatDao extends AbstractGenericDao<SeatEntity> implements SeatDao {

    private static final String SUBSELECT_QUERY = "(SELECT * FROM theaters WHERE theaters.id=%s)";
    private static final String INSERT_QUERY = "INSERT INTO screenings VALUES (%s, %s);";

    private static final String TABLE_NAME = "seats";

    public DefaultSeatDao() {
        super(TABLE_NAME);
    }

    @Override
    protected Long getEntityId(SeatEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(SeatEntity entity, Long id) {
        entity.setId(id);
    }


    @Override
    protected SeatEntity getDatabaseResults(ResultSet rs) throws SQLException {
        SeatEntity dto = new SeatEntity();
        dto.setId(rs.getLong(1));
        dto.setSeatNumber(rs.getInt(2));
        return null;
    }

    @Override
    protected boolean containsReferences() {
        return true;
    }

    @Override
    protected String setupUpdateQuery(SeatEntity entity) throws SQLException {
        return null;
    }

    @Override
    protected void saveEntityCollections(SeatEntity entity) {
        return;
    }

    @Override
    protected String buildInsertWithReferencesQuery(SeatEntity entity) {
        String innerQuery = String.format(SUBSELECT_QUERY, entity.getTheaterEntity().getId());
        String query = String.format(INSERT_QUERY, entity.toString(), innerQuery);
        return query;
    }
}
