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
        try {
            DBManager.setConnection(DBManager.JDBC_Driver, DBManager.JDBC_URL);
            statement = DBManager.getConnection().createStatement();
            statement.executeQuery("SELECT * FROM Users LIMIT 1");
            statement.executeQuery("SELECT * FROM Projects LIMIT 1");
        }catch (SQLException e){
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("DROP TABLE IF EXISTS Projects");
            statement.executeUpdate("CREATE TABLE Users (" + "Name VARCHAR(30), " + "Surname VARCHAR(30),"
                    + "Email VARCHAR(30)," + "Password VARCHAR(30))");
            statement.executeUpdate("CREATE TABLE Projects (" + "ID INTEGER, " + "Name VARCHAR(30),"
                     + "Description VARCHAR(30))");
        }
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
        return statement.executeQuery("SELECT * FROM Users");
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

        int a = statement.executeUpdate("INSERT INTO Users(Name,Surname,Email,Password) " +
                "VALUES('"+name+"' , '"+surname+"', '"+email+"','"+password+"')");

        return a;
    }

    public static int projectLoad(Statement statement,
                                  String id, String project_name,String description) throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return project_Load(statement,id,project_name,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Load(Statement statement,
                                   String id, String project_name,String description) throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Projects(ID,Name,Description) " +
                "VALUES('"+id+"', '"+project_name+"', '"+description+"')");
        return a;
    }

    public static int projectRefresh(Statement statement,
                                  String id, String project_name,String description) throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return project_Refresh(statement,id,project_name,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Refresh(Statement statement,
                                   String id, String project_name,String description) throws SQLException {

        int a = statement.executeUpdate("REPLACE INTO Projects(ID,Name,Description) " +
                "VALUES('"+id+"', '"+project_name+"', '"+description+"')");
        return a;
    }

    public static int projectDelete(Statement statement, String id) throws SQLException{
        try {
            System.out.println("\n- Deleting project...");
            return project_Delete(statement, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Delete(Statement statement, String id) throws SQLException {

        int a = statement.executeUpdate("DELETE FROM Projects WHERE ID = '" +id+ "'  ");
        return a;
    }


    public static ResultSet projectUpload(Statement statement) throws SQLException{
        try {
            System.out.println("\n- reading database...");
            return projects_Upload(statement);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet projects_Upload(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM Projects");
    }

    public static int AddRevCostUpload(Statement statement,
                                  String table, String amount, String name,String description) throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return AddRevCostUpload(statement,table,amount,name,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddRevCost_Upload(Statement statement,
                                   String table, String amount, String name, String description) throws SQLException {
        int a = statement.executeUpdate("INSERT INTO '"+table+"'(ID,Name,Description) " +
                "VALUES('"+amount+"', '"+name+"', '"+description+"')");
        return a;
    }





}
