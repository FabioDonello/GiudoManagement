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
            statement.executeQuery("SELECT * FROM Cost LIMIT 1");
            statement.executeQuery("SELECT * FROM Revenues LIMIT 1");
        }catch (SQLException e){
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("DROP TABLE IF EXISTS Projects");
            statement.executeUpdate("DROP TABLE IF EXISTS Cost");
            statement.executeUpdate("DROP TABLE IF EXISTS Revenues");
            statement.executeUpdate("CREATE TABLE Users (" + "Name VARCHAR(30), " + "Surname VARCHAR(30),"
                    + "Email VARCHAR(30)," + "Password VARCHAR(30))");
            statement.executeUpdate("CREATE TABLE Projects (" + "ID INTEGER, " + "Name VARCHAR(30),"
                     + "Description VARCHAR(30))");
            statement.executeUpdate("CREATE TABLE Cost (" + "ID VARCHAR(30)," + "Amount VARCHAR(30),"
                    + "Name VARCHAR(30)," + "Description VARCHAR(30))");
            statement.executeUpdate("CREATE TABLE Revenues (" + "ID VARCHAR(30)," + "Amount VARCHAR(30),"
                    + "Name VARCHAR(30)," + "Description VARCHAR(30))");
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
                                  String id, String project_name,String description)
            throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return project_Load(statement,id,project_name,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int project_Load(Statement statement,
                                   String id, String project_name,String description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Projects(ID,Name,Description) " +
                "VALUES('"+id+"', '"+project_name+"', '"+description+"')");
        return a;
    }


    public static int projectRefresh(Statement statement,
                                  String id, String project_name,String description)
            throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return project_Refresh(statement,id,project_name,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int project_Refresh(Statement statement,
                                   String id, String project_name,String description)
            throws SQLException {

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


    public static ResultSet projectsUpload(Statement statement) throws SQLException{
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


    public static int AddRevCostLoad(Statement statement,
                                  String table,String id, String amount, String name,String description)
            throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return AddRevCost_Load(statement,table,id,amount,name,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int AddRevCost_Load(Statement statement,
                                   String table,String id, String amount, String name, String description)
            throws SQLException {
        int a = statement.executeUpdate("INSERT INTO '"+table+"'(ID,Amount,Name,Description) " +
                "VALUES('"+id+"','"+amount+"','"+name+"','"+description+"')");
        return a;
    }


    public static ResultSet RevCostUpload(Statement statement, String table, String id) throws SQLException{
        try {
            System.out.println("\n- reading database...");
            return RevCost_Upload(statement,table,id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }
    public static ResultSet RevCost_Upload(Statement statement,String table, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '"+table+"' WHERE ID = '" +id+ "'  ");
    }


    public static int RevCostDelete(Statement statement, String table, String amount, String name) throws SQLException{
        try {
            System.out.println("\n- Deleting project...");
            return RevCost_Delete(statement, table,amount, name);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int RevCost_Delete(Statement statement, String table, String amount, String name) throws SQLException {

        int a = statement.executeUpdate("DELETE FROM '" +table+ "' WHERE Amount = '" +amount+ "' AND Name = '" +name+ "'  ");
        return a;
    }


    public static ResultSet TodoDoneUpLoad(Statement statement,
                                      String table,String id) throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return TodoDoneUpload(statement,table,id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }
    public static ResultSet TodoDoneUpload(Statement statement,
                                         String table,String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '"+table+"' WHERE ID = '" +id+ "'  ");

    }


    public static int TodoDoneLoad(Statement statement,
                                     String table,String id, String name, String date, String description)
            throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return Todo_Done_Load(statement,table,id,name,date,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int Todo_Done_Load(Statement statement,
                                     String table,String id, String name, String date, String description)
            throws SQLException {
        int a= 0;
        if (table.compareTo("DoneAction")==0){
            a = statement.executeUpdate("INSERT INTO '"+table+"'(ID,Name,Execution,Description) " +
                    "VALUES('"+id+"','"+name+"','"+date+"','"+description+"')");
        }
        if (table.compareTo("ToDoAction")==0){
            a = statement.executeUpdate("INSERT INTO '"+table+"'(ID,Name,Deadline,Description) " +
                    "VALUES('"+id+"','"+name+"','"+date+"','"+description+"')");
        }

        return a;
    }


    public static int TodoDoneDelete(Statement statement, String table, String name, String date) throws SQLException{
        try {
            System.out.println("\n- Deleting project...");
            return TodoDone_Delete(statement, table, name, date);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int TodoDone_Delete(Statement statement, String table, String name, String date) throws SQLException {

        int a=0;
        if (table.compareTo("ToDoAction")==0){
            a = statement.executeUpdate("DELETE FROM '" +table+ "' WHERE Name = '" +name+ "' AND Deadline = '" +date+ "'  ");
        }
        if (table.compareTo("DoneAction")==0){
            a = statement.executeUpdate("DELETE FROM '" +table+ "' WHERE Name = '" +name+ "' AND Execution = '" +date+ "'  ");
        }
        return a;
    }


    public static int TicketsLoad(Statement statement,
                                   String id, String name, String tickets, String description)
            throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return Tickets_Load(statement,id,name,tickets,description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int Tickets_Load(Statement statement,
                                     String id, String name, String tickets, String description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Tickets (ID,NS,Tickets,Description) " +
                "VALUES('"+id+"','"+name+"','"+tickets+"','"+description+"')");

        return a;
    }


    public static int TicketsDelete(Statement statement, String id, String name) throws SQLException{
        try {
            System.out.println("\n- Deleting project...");
            return Tickets_Delete(statement, id, name);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }
    public static int Tickets_Delete(Statement statement, String id, String name) throws SQLException {
        int a = statement.executeUpdate("DELETE FROM Tickets WHERE ID = '" +id+ "' AND NS = '" +name+ "' ");
        return a;
    }


    public static ResultSet TicketsUpLoad(Statement statement,
                                           String id) throws SQLException{
        try {
            System.out.println("\n- Writing database...");
            return Tickets_Upload(statement,id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }
    public static ResultSet Tickets_Upload(Statement statement, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM Tickets WHERE ID = '" +id+ "' ");
    }





}
