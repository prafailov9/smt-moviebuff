package org.ntr.persistence.coredb;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A DatabaseBuilder is a utility class, that can create and drop tables in an existing DB schema.
 */
final class DatabaseBuilder {

    private final static Logger LOGGER = Logger.getLogger(DatabaseBuilder.class.getCanonicalName());
    //    private final static String RECREATE_DB_SCRIPT_FILE = "/recreate-db-script.sql";
    private final static String DB_SCRIPT_FILE = "/mysql-moviebuff-db-script.sql";
    private final static String TEST_DATA_FILE = "/hsql-insert-data.sql";

    private final static String DROP_ALL_TABLES_SCRIPT = "/drop-tables-script.sql";
    private static String DB_SCRIPT_FILE_PATH;

    private DatabaseBuilder() {

    }

    public static void setDbScriptFilePath(final String dbScriptFilePath) {
        DB_SCRIPT_FILE_PATH = dbScriptFilePath;
    }

//    public static void recreateDatabase(Connection conn) {
//        String message = "Error occured while recreating database!";
//        runScript(conn, RECREATE_DB_SCRIPT_FILE, message);
//    }

    public static void buildDatabase(Connection conn) throws SQLException {
        String errorMessage = "Error occurred while building database!";
        runScript(conn, DB_SCRIPT_FILE_PATH, errorMessage);
    }

    public static void insertData(Connection conn, String dataFile) throws SQLException {
        String errorMessage = "Error occurred while inserting test data!";
        runScript(conn, dataFile, errorMessage);
    }

    public static void dropDatabase(Connection conn) throws SQLException {
        String errorMessage = "Error occurred while dropping tables!";
        runScript(conn, DROP_ALL_TABLES_SCRIPT, errorMessage);
    }

    public static final void runScript(Connection conn, String scriptFile, String errorMessage) {
        try {
            Reader reader = new InputStreamReader(DatabaseBuilder.class.getResourceAsStream(scriptFile));
            ScriptRunner sr = new ScriptRunner(conn);
            sr.runScript(reader);
        } catch (RuntimeSqlException ex) {
            LOGGER.log(Level.INFO, errorMessage, ex);
        }

    }

}
