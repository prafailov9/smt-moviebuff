package org.ntr.persistence.daos.theater;

import org.apache.commons.collections4.CollectionUtils;
import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.daos.screening.DefaultScreeningDao;
import org.ntr.persistence.daos.seat.DefaultSeatDao;
import org.ntr.persistence.entities.TheaterEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DefaultTheaterDao extends AbstractGenericDao<TheaterEntity> implements TheaterDao {

    private static final String SUBSELECT_QUERY = "(SELECT * FROM theater_address WHERE theater_address.id=%s)";
    private static final String INSERT_QUERY = "INSERT INTO theaters VALUES (%s, %s);";


    private static final String TABLE_NAME = "theaters";

    public DefaultTheaterDao() {
        super(TABLE_NAME);
    }

    @Override
    protected Long getEntityId(TheaterEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(TheaterEntity entity, Long id) {
        entity.setId(id);
    }


    @Override
    protected TheaterEntity getDatabaseResults(ResultSet rs) throws SQLException {
        TheaterEntity theaterEntity = new TheaterEntity();
        theaterEntity.setTheaterName(rs.getString("theater_name"));
        theaterEntity.setExternalId(rs.getString("external_id"));
        theaterEntity.setId(rs.getLong("id"));
        loadRelations(theaterEntity, rs);

//        theaterEntity.setTheaterAddressEntity();

        return theaterEntity;
    }

    @Override
    protected boolean containsReferences() {
        return false;
    }

    @Override
    protected String setupUpdateQuery(TheaterEntity entity) throws SQLException {
        return null;
    }

    @Override
    protected void saveEntityCollections(TheaterEntity entity) {
        if(CollectionUtils.isNotEmpty(entity.getScreenings()))
            entity.getScreenings().forEach(screening -> new DefaultScreeningDao().save(screening));

        if(CollectionUtils.isNotEmpty(entity.getSeats()))
        entity.getSeats().forEach(seatEntity ->  new DefaultSeatDao().save(seatEntity));
    }

    @Override
    protected String buildInsertWithReferencesQuery(TheaterEntity entity) {
        String innerQuery = String.format(SUBSELECT_QUERY, entity.getTheaterAddressEntity().getId());
        String query = String.format(INSERT_QUERY, entity.toString(), innerQuery);
        return query;
    }

    private void loadRelations(TheaterEntity theaterEntity, ResultSet resultSet) throws SQLException {
        if(Objects.isNull(theaterEntity.getTheaterAddressEntity())) {
            theaterEntity.setTheaterAddressEntity(new DefaultTheaterAddressDao().loadById(resultSet.getLong("theater_address_id")));
        }
    }

}
