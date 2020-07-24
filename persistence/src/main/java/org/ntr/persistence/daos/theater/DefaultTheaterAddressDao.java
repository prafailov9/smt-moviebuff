package org.ntr.persistence.daos.theater;

import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.entities.TheaterAddressEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultTheaterAddressDao extends AbstractGenericDao<TheaterAddressEntity> {

    private static final String TABLE_NAME = "theater_address";

    public DefaultTheaterAddressDao() {
        super(TABLE_NAME);
    }

    @Override
    protected String buildInsertWithReferencesQuery(TheaterAddressEntity entity) {
        return null;
    }

    @Override
    protected Long getEntityId(TheaterAddressEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(TheaterAddressEntity entity, Long id) {
        entity.setId(id);
    }

    @Override
    protected boolean containsReferences() {
        return false;
    }

    @Override
    protected TheaterAddressEntity getDatabaseResults(ResultSet rs) throws SQLException {
        TheaterAddressEntity theaterAddressEntity = new TheaterAddressEntity();
        theaterAddressEntity.setId(rs.getLong("id"));
        theaterAddressEntity.setExternalId(rs.getString("external_id"));
        theaterAddressEntity.setCity(rs.getString("city"));
        theaterAddressEntity.setLandmark(rs.getString("landmark"));
        theaterAddressEntity.setStreetNo(rs.getInt("street_number"));
        theaterAddressEntity.setPinCode(rs.getString("pin_code"));
        theaterAddressEntity.setState(rs.getString("state"));

        return theaterAddressEntity;
    }

    @Override
    protected String setupUpdateQuery(TheaterAddressEntity entity) throws SQLException {
        return null;
    }

    @Override
    protected void saveEntityCollections(TheaterAddressEntity entity) {
        return;
    }
}
