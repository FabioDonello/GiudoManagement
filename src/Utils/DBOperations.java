package Utils;

import java.sql.*;

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
        return statement.executeQuery("SELECT * FROM Utenti");
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

        int a = statement.executeUpdate("INSERT INTO Utenti(Name,Surname,Email,Password) " +
                "VALUES('"+name+"' , '"+surname+"', '"+email+"','"+password+"')");

        DBManager.close();
        return a;
    }

    public static int projectLoad(Statement statement,
                               String project_name,String creator) throws SQLException{
        try {
            System.out.println("\n- reading database...");
            return project_Load(statement,project_name,creator);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Load(Statement statement,
                                String project_name,String creator) throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Projects(Name,Creator,) " +
                "VALUES('"+project_name+"', '"+creator+"')");

        DBManager.close();
        return a;
    }
}
