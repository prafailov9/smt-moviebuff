package org.ntr.persistence.coredb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DatabaseBuilderTest {

    private static final String DEV_DB_PROPS = "/db.properties";
    private static final String DEV_DB_SCRIPT = "/mysql-moviebuff-db-script.sql";
    private static final String DEV_INSERT_DATA_SCRIPT = "/insert-data.sql";

    private static final String TEST_DB_PROPS = "/db-test.properties";
    private static final String TEST_DB_SCRIPT = "/hsql-moviebuff-db-script.sql";
    private static final String TEST_INSERT_DATA_SCRIPT = "/hsql-insert-data.sql";

    private static final String DROP_TABLES_SCRIPT = "DROP TABLE %s.reservations;\n" +
            "DROP TABLE %s.screenings;\n" +
            "DROP TABLE %s.seats;\n" +
            "DROP TABLE %s.theaters;\n" +
            "DROP TABLE %s.movies;\n" +
            "DROP TABLE %s.users;\n" +
            "DROP TABLE %s.roles;";

    @BeforeEach
    public void setUp() {
        DatabaseConnector.initialize(DEV_DB_PROPS);
    }

    @AfterEach
    public void tearDown() {
        try {
            DatabaseConnector.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buildThenDropDbTest() {
        try {
//            DatabaseBuilder.runScript(DatabaseConnector.getInstance().getConnection(), DEV_DB_SCRIPT, "Creating DB...");
//            DatabaseBuilder.insertData(DatabaseConnector.getInstance().getConnection(), DEV_INSERT_DATA_SCRIPT);
            DatabaseBuilder.dropDatabase(DatabaseConnector.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

}