package org.ntr.persistence.coredb;

import org.apache.commons.dbcp.BasicDataSource;
import org.ntr.persistence.exceptions.ConnectorNotInitializedException;
import org.ntr.persistence.exceptions.CouldNotLoadPropertiesException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A DatabaseConnector instance is created when calling the initialize() method.
 * During creation, it establishes a Connection to an underlying Data Source.
 * After creation of the instance, the Connection can be retrieved.
 * To correctly initialize a DC instance a properties file must exist in resources folder with valid data for the underlying data source.
 * This class only establishes a connection to an UDS.
 */
public final class DatabaseConnector {
    private final static Logger LOGGER = Logger.getLogger(DatabaseConnector.class.getCanonicalName());

    private DataSource dataSource;
    private Properties properties;

    private DatabaseConnector(String propertiesFile) {
        this.properties = loadProperties(propertiesFile);
        this.dataSource = loadDataSource();
    }

    private static class InstanceHolder {

        static DatabaseConnector INSTANCE;

        static final void setInstance(final String properties) {
            INSTANCE = new DatabaseConnector(properties);
        }
    }

    public static void initialize(final String properties) {
        InstanceHolder.setInstance(properties);
    }

    public static DatabaseConnector getInstance() {
        if (Objects.isNull(InstanceHolder.INSTANCE)) throw new ConnectorNotInitializedException();
        return InstanceHolder.INSTANCE;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.severe("Could not retrieve connection!");
        }
        return null;
    }

    private Properties loadProperties(String propertiesFile) {
        try {
            Reader reader = new InputStreamReader(getClass().getResourceAsStream(propertiesFile));
            properties = new Properties();
            properties.load(reader);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            throw new CouldNotLoadPropertiesException();
        } finally {
            return properties;
        }
    }

    private DataSource loadDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(properties.getProperty("datasource.driver"));
        ds.setUrl(properties.getProperty("datasource.url"));
        ds.setUsername(properties.getProperty("datasource.username"));
        ds.setPassword(properties.getProperty("datasource.password"));
        return ds;
    }

}
