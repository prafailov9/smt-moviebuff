package org.ntr.persistence.coredb;

import org.ntr.persistence.exceptions.CannotPersistEntityException;
import org.ntr.persistence.exceptions.NoRecordFoundException;
import org.ntr.persistence.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    protected final static Logger LOGGER = Logger.getLogger(AbstractGenericDao.class.getCanonicalName());

    private final static String INSERT_QUERY = "insert into %s values (%s)";
    private final static String INSERT_WITH_FK_QUERY = "insert into %s values (%s, (select id from %s where id = %s));";
    private final static String DELETE_QUERY = "delete from %s where id=?";
    private final static String LOAD_ONE_QUERY = "select * from %s where id=?";
    private final static String LOAD_ALL_QUERY = "select * from %s";

    protected Connection connection;

    protected String tableName;

    public AbstractGenericDao(String tableName) {
        this.tableName = tableName;
        this.connection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public T save(final T entity) {
        checkIfPersisted(entity);
        if (containsReferences()) return executeSaveWithReferences(entity);
        else return executeSave(entity);
    }

    @Override
    public T loadById(final Long id) {
        T entity = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = String.format(LOAD_ONE_QUERY, tableName);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) entity = getDatabaseResults(resultSet);
            if(Objects.isNull(entity)) throw new NoRecordFoundException();

            closeDatabaseResources(resultSet, preparedStatement);
            return entity;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not load a record with id=" + id + " from " + tableName + "!", ex);
            closeDatabaseResources(resultSet, preparedStatement);
        }
        return entity;
    }

    @Override
    public List<T> loadAll() {
        List<T> entities = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = String.format(LOAD_ALL_QUERY, tableName);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = getDatabaseResults(resultSet);
                entities.add(entity);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not load all records from " + tableName + "!", ex);
        } finally {
            closeDatabaseResources(resultSet, preparedStatement);
            return entities;
        }
    }



    @Override
    public boolean delete(T entity) {
        Long entityId = getEntityId(entity);
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            if (Objects.isNull(entityId)) {
                throw new NoSuchEntityException();
            } else {
                String query = String.format(DELETE_QUERY, tableName);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, entityId);
                preparedStatement.executeUpdate();
                setEntityId(entity, null);
                success = true;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not delete record with id=" + entityId + "from " + tableName + "!", ex);
            success = false;
        } finally {
            closeDatabaseResources(null, preparedStatement);
            return success;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            String query = String.format(DELETE_QUERY, tableName);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not delete record with id=" + id + "from " + tableName + "!", ex);
            success = false;
        } finally {
            closeDatabaseResources(null, preparedStatement);
            return success;
        }
    }

    @Override
    public boolean update(final T entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            if (Objects.nonNull(getEntityId(entity))) {
                final String updateQuery = setupUpdateQuery(entity);
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.executeUpdate();
                success = true;
            } else throw new NoSuchEntityException();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not update a record in " + tableName + "!", ex);
            success = false;
        } finally {
            closeDatabaseResources(null, preparedStatement);
            return success;
        }
    }

    /**
     * Will build a customized query with each foreign key selected
     * @param entity
     * @return
     */
    protected abstract String buildInsertWithReferencesQuery(final T entity);

    protected abstract Long getEntityId(final T entity);

    protected abstract void setEntityId(final T entity, final Long id);

    protected abstract boolean containsReferences();

    protected abstract T getDatabaseResults(ResultSet rs) throws SQLException;

    protected abstract String setupUpdateQuery(T entity) throws SQLException;

    protected abstract void saveEntityCollections(final T entity);

    protected void checkIfPersisted(T entity) {
        if (Objects.nonNull(getEntityId(entity))) throw new CannotPersistEntityException();
    }

    private T executeSave(T entity) {
        String query = String.format(INSERT_QUERY, tableName, entity);
        T savedEntity = runInsertQuery(entity, query, connection);
        return savedEntity;
    }

    private T executeSaveWithReferences(final T entity) {
        String query = buildInsertWithReferencesQuery(entity);
        PreparedStatement insertStatement = null;
        ResultSet keys = null;
        try {
            LOGGER.log(Level.INFO, "Query: {0}", query);
            insertStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertStatement.executeUpdate();
            keys = insertStatement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong(1);
                setEntityId(entity, id);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            closeDatabaseResources(keys, insertStatement);
            return entity;
        }
    }

    private T runInsertQuery(T entity, String query, Connection conn) {
        PreparedStatement insertStatement = null;
        ResultSet keys = null;
        try {
            LOGGER.log(Level.INFO, "Query: {0}", query);
            insertStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertStatement.executeUpdate();
            keys = insertStatement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong(1);
                setEntityId(entity, id);
            }
            saveEntityCollections(entity);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not create a record in " + tableName + "!", ex);
            throw new CannotPersistEntityException();
        } finally {
            closeDatabaseResources(keys, insertStatement);
            return entity;
        }
    }

    /**
     * Method to close the db resources once used. If not closed, these objects might potentially create memory leaks
     * and other unwanted behavior.
     *
     * @param rs
     * @param stm
     */
    protected void closeDatabaseResources(final ResultSet rs, final Statement stm) {
        try {
            if (Objects.nonNull(rs))
                rs.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not close ResultSet in " + tableName + "!", ex);
        }
        try {
            if (Objects.nonNull(stm))
                stm.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Could not close Statement in " + tableName + "!", ex);
        }
    }

    //    /**
//     * Query for inserting a row of data in a specific table with a foreign key, that references a row in another table;
//     * => the referenced row should already exist and the foreign key should be a unique column in the referenced table;
//     * First executes an inner query which will retrieve the referenced foreign key and will add it as a parameter to
//     * a Statement object;
//     *
//     * @param entity
//     * @return
//     */
//    private String formInsertWithFKQuery(T entity) {
//        String query = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        String innerQuery = String.format(LOAD_ONE_QUERY, getReferenceTableName(entity));
//        try {
//            preparedStatement = connection.prepareStatement(innerQuery);
//            preparedStatement.setLong(1, getReferenceId(entity));
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Long fk = resultSet.getLong(1);
//                query = String.format(INSERT_WITH_FK_QUERY, tableName, entity.toString(), getReferenceTableName(entity), fk);
//            } else throw new CannotPersistEntityException();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(AbstractGenericDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            closeDatabaseResources(resultSet, preparedStatement);
//            return query;
//        }
//    }

}

