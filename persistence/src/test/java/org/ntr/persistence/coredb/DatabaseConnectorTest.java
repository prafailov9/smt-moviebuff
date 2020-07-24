package org.ntr.persistence.coredb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ntr.persistence.exceptions.ConnectorNotInitializedException;

class DatabaseConnectorTest {

    private static final String DEV_DB_PROPS = "/db.properties";
    private static final String TEST_DB_PROPS = "/db-test.properties";

    @Test
    public void initializeDataSourceTest() {
        DatabaseConnector.initialize(DEV_DB_PROPS);

        Assertions.assertNotNull(DatabaseConnector.getInstance());
        Assertions.assertNotNull(DatabaseConnector.getInstance().getConnection());
    }

    @Test
    public void tryToInitWithNonExistingDataSourceTest() {
        Assertions.assertThrows(NullPointerException.class, () -> DatabaseConnector.initialize("/LOLW"));
    }

    @Test// asserts that an exception should be thrown if the instance is not created
    public void tryToGetConnectorInstanceWithoutCreatingDataSource() {
        Assertions.assertThrows(ConnectorNotInitializedException.class, () -> DatabaseConnector.getInstance());
    }

}