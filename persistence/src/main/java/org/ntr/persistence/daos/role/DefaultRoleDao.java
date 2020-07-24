package org.ntr.persistence.daos.role;

import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.entities.RoleEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class DefaultRoleDao extends AbstractGenericDao<RoleEntity> implements RoleDao {

    private static final String TABLE_NAME = "roles";
    private static final String UPDATE_ROLE_RECORD_QUERY = "update %s set id=%s, role_name='%s' where id=%s";
    private static final String SELECT_ROLE_RECORD_BY_NAME_QUERY = "select * from roles where role_name=?";

    public DefaultRoleDao() {
        super(TABLE_NAME);
    }

    @Override
    protected Long getEntityId(RoleEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(RoleEntity entity, Long id) {
        entity.setId(id);
    }

    protected RoleEntity getDatabaseResults(ResultSet rs) throws SQLException {
        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(rs.getLong(1));
        roleEntity.setExternalId(rs.getString(2));
        roleEntity.setRoleName(rs.getString(3));
        return roleEntity;
    }

    @Override
    protected String setupUpdateQuery(RoleEntity entity) throws SQLException {
        String query = String.format(UPDATE_ROLE_RECORD_QUERY,
                tableName, entity.getId(), entity.getRoleName(), entity.getId());
        LOGGER.log(Level.INFO, "Update query: {0}", query);
        return query;
    }

    @Override
    protected void saveEntityCollections(RoleEntity entity) {
        return;
    }

    @Override
    protected String buildInsertWithReferencesQuery(RoleEntity entity) {
        return null;
    }

    @Override
    protected boolean containsReferences() {
        return false;
    }

    @Override
    public RoleEntity loadOneByRoleName(String roleName) {
        RoleEntity entity = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ROLE_RECORD_BY_NAME_QUERY);
            preparedStatement.setString(1, roleName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                entity = getDatabaseResults(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load the row in " + tableName + " table!", e);
        } finally {
            closeDatabaseResources(resultSet, preparedStatement);
            return entity;
        }
    }

}
