package Utils;

import java.sql.*;

/**
 * Class for managing DB connections making use of the singleton pattern.
 * Supports any JDBC Connection as long as the proper driver and connection
 * string are provided.
 *
 * @author Nicola Bicocchi
 */
public class DBManager {
    public static String JDBC_Driver = "org.sqlite.JDBC";

    public static String JDBC_URL = "jdbc:sqlite:C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\Utils\\DATABASE";

    static Connection connection;

    public static void setConnection(String Driver, String URL) {
        JDBC_Driver = Driver;
        JDBC_URL = URL;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            if (JDBC_Driver == null || JDBC_URL == null) {
                throw new IllegalStateException("Illegal request. Call setConnection() before.");
            }
            try {
                Class.forName(JDBC_Driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(JDBC_URL);
            DatabaseMetaData meta = connection.getMetaData();
            showMetadata();
        }
        return connection;
    }

    public static void showMetadata() throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Illegal request. Connection not established");
        }

        DatabaseMetaData md = connection.getMetaData();
        System.out.println("-- ResultSet Type --");
        System.out.println("Supports TYPE_FORWARD_ONLY: " + md.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
        System.out.println("Supports TYPE_SCROLL_INSENSITIVE: " + md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("Supports TYPE_SCROLL_SENSITIVE: " + md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
        System.out.println("-- ResultSet Concurrency --");
        System.out.println("Supports CONCUR_READ_ONLY: " + md.supportsResultSetType(ResultSet.CONCUR_READ_ONLY));
        System.out.println("Supports CONCUR_UPDATABLE: " + md.supportsResultSetType(ResultSet.CONCUR_UPDATABLE));
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
