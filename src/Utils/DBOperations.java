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
            statement.executeQuery("SELECT * FROM Staff LIMIT 1");
            statement.executeQuery("SELECT * FROM Inventory LIMIT 1");
            statement.executeQuery("SELECT * FROM Guest LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("DROP TABLE IF EXISTS Projects");
            statement.executeUpdate("DROP TABLE IF EXISTS Staff");
            statement.executeUpdate("DROP TABLE IF EXISTS Inventory");
            statement.executeUpdate("DROP TABLE IF EXISTS Guest");
            statement.executeUpdate("CREATE TABLE Users (" + "Name VARCHAR(30), " + "Surname VARCHAR(30),"
                    + "Email VARCHAR(30)," + "Password VARCHAR(30))");
            statement.executeUpdate("CREATE TABLE Projects (" + "ID INTEGER, " + "Name VARCHAR(30),"
                    + "Description VARCHAR(30))");
            statement.executeUpdate("CREATE TABLE Staff (" + "Name VARCHAR(30), " + "Task VARCHAR(30),"
                    + "Description VARCHAR)");
            statement.executeUpdate("CREATE TABLE Inventory (" + "Object VARCHAR(30), " + "Quantity INTEGER,"
                    + "Description VARCHAR)");
            statement.executeUpdate("CREATE TABLE Guest (" + "Name VARCHAR(30), " + "Surname VARCHAR(30),"
                    + "Email VARCHAR" + "Phone_Number VARCHAR(10)" + "Confirm INTEGER = 0");
        }
        return statement;
    }


    public static ResultSet usersUpload(Statement statement) throws SQLException {
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
                               String name, String surname, String email, String password) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return user_load(statement, name, surname, email, password);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int user_load(Statement statement,
                                String name, String surname, String email, String password) throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Users(Name,Surname,Email,Password) " +
                "VALUES('" + name + "' , '" + surname + "', '" + email + "','" + password + "')");

        return a;
    }

    public static int projectLoad(Statement statement,
                                  String id, String project_name, String description) throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return project_Load(statement, id, project_name, description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Load(Statement statement,
                                   String id, String project_name, String description) throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Projects(ID,Name,Description) " +
                "VALUES('" + id + "', '" + project_name + "', '" + description + "')");
        return a;
    }

    public static int projectRefresh(Statement statement,
                                     String id, String project_name, String description) throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return project_Refresh(statement, id, project_name, description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Refresh(Statement statement,
                                      String id, String project_name, String description) throws SQLException {

        int a = statement.executeUpdate("REPLACE INTO Projects(ID,Name,Description) " +
                "VALUES('" + id + "', '" + project_name + "', '" + description + "')");
        return a;
    }

    public static int projectDelete(Statement statement, String id) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return project_Delete(statement, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Delete(Statement statement, String id) throws SQLException {

        int a = statement.executeUpdate("DELETE FROM Projects WHERE ID = '" + id + "'  ");
        return a;
    }


    public static ResultSet projectUpload(Statement statement) throws SQLException {
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

    public static ResultSet staffUpload(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM Staff");
    }

    public static int Add_Staff(Statement statement, String Name, String Task, String Description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddStaff(statement, Name, Task, Description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddStaff(Statement statement, String Name, String Task, String Description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO 'Staff'(Name,Task,Description) " +
                "VALUES('" + Name + "','" + Task + "','" + Description + "')");
        return a;
    }

    public static int Delete_Staff(Statement statement, String Name, String Task) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return DeleteStaff(statement, Name, Task);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int DeleteStaff(Statement statement, String Name, String Task) throws SQLException {
        int a = statement.executeUpdate("DELETE FROM 'Staff' WHERE Name = '" + Name + "' AND Task = '" + Task + "'  ");
        return a;
    }

    public static ResultSet inventoryUpload(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM Inventory");
    }

    public static int Add_Inventory(Statement statement, String Object, String Quantity, String Description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddInventory(statement, Object, Quantity, Description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddInventory(Statement statement, String Object, String Quantity, String Description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO 'Inventory'(Object,Quantity,Description) " +
                "VALUES('" + Object + "','" + Quantity + "','" + Description + "')");
        return a;
    }

    public static int Delete_Inventory(Statement statement, String Object, String Quantity) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return DeleteInventory(statement, Object, Quantity);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int DeleteInventory(Statement statement, String Object, String Quantity) throws SQLException {
        int a = statement.executeUpdate("DELETE FROM 'Inventory' WHERE Object = '" + Object + "' AND Quantity = '" + Quantity + "'  ");
        return a;
    }

    public static ResultSet guestsUpload(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM Guest");
    }

    public static int Add_Guest(Statement statement, String Name, String Surname, String Email,
                                String Phone)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddGuest(statement, Name, Surname, Email, Phone);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddGuest(Statement statement, String Name, String Surname, String Email,
                               String Phone)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO 'Guest'(Name,Surname,Email,Phone_Number) " +
                "VALUES('" + Name + "','" + Surname + "','" + Email + "','" + Phone + "')");
        return a;
    }

    public static int Delete_Guest(Statement statement, String Name, String Surname, String Email,
                                   String Phone) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return DeleteGuest(statement, Name, Surname, Email, Phone);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int DeleteGuest(Statement statement, String Name, String Surname, String Email,
                                  String Phone) throws SQLException {
        int a = statement.executeUpdate("DELETE FROM 'Guest' WHERE Name = '" + Name + "' " +
                "AND Surname = '" + Surname + "' " + "AND Email = '" + Email + "' " +
                "AND Phone_Number = '" + Phone + "'");
        return a;
    }


}
