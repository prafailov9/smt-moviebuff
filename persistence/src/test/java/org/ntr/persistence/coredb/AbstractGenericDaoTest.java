package org.ntr.persistence.coredb;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractGenericDaoTest<T> {
    protected final static Logger LOGGER = Logger.getLogger(AbstractGenericDaoTest.class.getCanonicalName());
    private static final String PROPERTIES_PATH = "/db-test.properties";
    private static final String DB_SCRIPT_FILE = "/hsql-moviebuff-db-script.sql";
    private static final String INSERT_TEST_DATA_FILE = "/hsql-insert-data.sql";
    private static DatabaseConnector DB_CONNECTOR;

    @BeforeAll
    public static void beforeClass() {
        LOGGER.log(Level.INFO, "In abstract Dao test class. Setting up test connection...");
        try {
            DatabaseConnector.initialize(PROPERTIES_PATH);
            DB_CONNECTOR = DatabaseConnector.getInstance();
            LOGGER.log(Level.INFO, "Building test database...");
            DatabaseBuilder.setDbScriptFilePath(DB_SCRIPT_FILE);
            DatabaseBuilder.buildDatabase(DB_CONNECTOR.getConnection());
            LOGGER.log(Level.INFO, "Inserting test data...");
            DatabaseBuilder.insertData(DB_CONNECTOR.getConnection(), INSERT_TEST_DATA_FILE);
            LOGGER.log(Level.INFO, "Test database configured!");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Exception in abstract dao test class: {0}", ex.getMessage());
        }
    }

    @AfterAll
    public static void afterClass() {
        LOGGER.log(Level.INFO, "In abstract test class.");
        try {
            DatabaseBuilder.dropDatabase(DB_CONNECTOR.getConnection());
            DB_CONNECTOR.getConnection().close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Exception in abstract test class: {0}", ex.getMessage());
        }
    }

    public Long getRandomId() {
        List<Long> ids = getAllIds();
        Long randomId = ids.get(new Random().nextInt(ids.size()));
        return randomId;
    }

    protected List<Long> getAllIds() {
        List<T> records = getRecords();
        List<Long> ids = records.stream().map(t -> getDtoId(t)).collect(Collectors.toList());
        return ids;
    }

    protected abstract List<T> getRecords();

    protected abstract Long getDtoId(T dto);
}
