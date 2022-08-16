package Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class for testing basic operations with JDBC It supports both MySQL and
 * SQLite
 *
 * @author Nicola Bicocchi
 */
public class DBOperations {
    static Statement statement;

    public static Statement establish_connection() throws SQLException {
        DBManager.setConnection(DBManager.JDBC_Driver, DBManager.JDBC_URL);
        statement = DBManager.getConnection().createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        return statement;
    }

    public static ResultSet usersUpload(Statement statement) throws SQLException{
        try {
            System.out.println("\n- reading database...");
            return users_upload(statement);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet users_upload(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM book LIMIT 100");
    }

    public static int userLoad(Statement statement,
                               String name,String surname,String email,String password) throws SQLException{
        try {
            System.out.println("\n- reading database...");
            return user_load(statement, name, surname, email, password);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int user_load(Statement statement,
                                String name,String surname,String email,String password) throws SQLException {
        return statement.executeUpdate("SELECT * FROM book LIMIT 100");
    }
}
