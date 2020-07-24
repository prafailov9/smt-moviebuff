package org.ntr.persistence.daos.user;

import org.ntr.persistence.coredb.AbstractGenericDao;
import org.ntr.persistence.daos.reservation.DefaultReservationDao;
import org.ntr.persistence.daos.role.DefaultRoleDao;
import org.ntr.persistence.daos.screening.DefaultScreeningDao;
import org.ntr.persistence.entities.RoleEntity;
import org.ntr.persistence.entities.UserEntity;
import org.ntr.persistence.entities.enums.FetchType;
import org.ntr.persistence.entities.relations.Relation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultUserDao extends AbstractGenericDao<UserEntity> implements UserDao {

    private static final String TABLE_NAME = "users";

    public DefaultUserDao() {
        super(TABLE_NAME);
    }


    @Override
    protected Long getEntityId(UserEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(UserEntity entity, Long id) {
        entity.setId(id);
    }

    @Override
    protected UserEntity getDatabaseResults(ResultSet rs) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(rs.getLong(1));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setExternalId(rs.getString("external_id"));
        return user;
    }

    @Override
    protected boolean containsReferences() {
        return true;
    }

    @Override
    protected String setupUpdateQuery(UserEntity entity) throws SQLException {
        return null;
    }

    @Override
    protected void saveEntityCollections(UserEntity entity) {
        entity.getReservations().forEach(reservationEntity -> new DefaultReservationDao().save(reservationEntity));
    }

    @Override
    protected String buildInsertWithReferencesQuery(UserEntity entity) {
        return null;
    }

    @Override
    public List<UserEntity> loadAllByRole(RoleEntity role) {
        String query = String.format("SELECT * FROM users WHERE users.role_id=%s", role.getId());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<UserEntity> users = new ArrayList<>();
            while (resultSet.next()) {
                UserEntity user = getDatabaseResults(resultSet);
                user.setRoleEntity(role);
                loadRelations(user, resultSet);
                users.add(user);
            }
            return users;

        } catch (SQLException ex) {

        } finally {
            closeDatabaseResources(resultSet, preparedStatement);
            return null;
        }
    }

    private void loadRelations(UserEntity user, ResultSet resultSet) throws SQLException {
        if (Objects.isNull(user.getRoleEntity())) {
            Relation relation = user.getEntityRelationMap().get(RoleEntity.class);
            if (relation.getOption().getFetchType().name().equals(FetchType.EAGER.name())) {
                user.setRoleEntity(new DefaultRoleDao().loadById(resultSet.getLong("role_id")));
            }
        }
        if (Objects.isNull(user.getReservations()) && !user.getReservations().isEmpty()) {
            Relation relation = user.getEntityRelationMap().get(UserEntity.class);
            if (relation.getOption().getFetchType().name().equals(FetchType.EAGER.name())) {
                user.setReservations(new DefaultReservationDao().loadAllByUser(user));
            }
        }

    }

}
