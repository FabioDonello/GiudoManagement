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
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database");
        }

        try {
            statement.executeQuery("SELECT * FROM Users LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("CREATE TABLE Users (" + "Name VARCHAR(30), " + "Surname VARCHAR(30),"
                    + "Email VARCHAR(30)," + "Password VARCHAR(30))");
        }

        try {
            statement.executeQuery("SELECT * FROM Projects LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Projects");
            statement.executeUpdate("CREATE TABLE Projects (" + "Email VARCHAR(30)," + "ID VARCHAR(30), " + "Name VARCHAR(30),"
                    + "Description VARCHAR(30))");
        }

        try {
            statement.executeQuery("SELECT * FROM Cost LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Revenues");
            statement.executeUpdate("CREATE TABLE Cost (" + "ID VARCHAR(30)," + "Amount VARCHAR(30),"
                    + "Name VARCHAR(30)," + "Description VARCHAR(30))");
        }

        try {
            statement.executeQuery("SELECT * FROM Revenues LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Revenues");
            statement.executeUpdate("CREATE TABLE Revenues (" + "ID VARCHAR(30)," + "Amount VARCHAR(30),"
                    + "Name VARCHAR(30)," + "Description VARCHAR(30))");
        }

        try {
            statement.executeQuery("SELECT * FROM Staff LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Staff");
            statement.executeUpdate("CREATE TABLE Staff (" + "ID VARCHAR(30)," + "Name VARCHAR(30), " + "Task VARCHAR(30),"
                    + "Description VARCHAR)");
        }

        try {
            statement.executeQuery("SELECT * FROM Inventory LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Inventory");
            statement.executeUpdate("CREATE TABLE Inventory (" + "ID VARCHAR(30)," + "Object VARCHAR(30), " + "Quantity INTEGER,"
                    + "Description VARCHAR)");
        }

        try {
            statement.executeQuery("SELECT * FROM Guest LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Guest");
            statement.executeUpdate("CREATE TABLE Guest (" + "Name VARCHAR(30), " + "Surname VARCHAR(30),"
                    + "Email VARCHAR, " + "Phone_Number VARCHAR(10)," + "Confirm INTEGER," + "ID VARCHAR(30))");
        }

        try {
            statement.executeQuery("SELECT * FROM Tickets LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS Tickets");
            statement.executeUpdate("CREATE TABLE Tickets (" + "ID VARCHAR(30), " + "NS VARCHAR(30),"
                    + "Tickets VARCHAR(30), " + "Description VARCHAR(30))");
        }

        try {
            statement.executeQuery("SELECT * FROM TicketsPrice LIMIT 1");
        } catch (SQLException e) {
            statement.executeUpdate("DROP TABLE IF EXISTS TicketsPrice");
            statement.executeUpdate("CREATE TABLE TicketsPrice (" + "ID VARCHAR(30), " + "Price VARCHAR(30))");
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
                                  String id, String project_name, String description, String Email)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return project_Load(statement, id, project_name, description, Email);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Load(Statement statement,
                                   String id, String project_name, String description, String Email)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Projects(Email,ID,Name,Description) " +
                "VALUES('" + Email + "','" + id + "', '" + project_name + "', '" + description + "')");
        return a;
    }


    public static int projectRefresh(Statement statement,
                                     String id, String project_name, String description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return project_Refresh(statement, id, project_name, description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int project_Refresh(Statement statement,
                                      String id, String project_name, String description)
            throws SQLException {

        int a = statement.executeUpdate("REPLACE INTO Projects(Email,ID,Name,Description) " +
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


    public static ResultSet projectsUpload(Statement statement, String Email) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return projects_Upload(statement, Email);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet projects_Upload(Statement statement, String Email) throws SQLException {
        return statement.executeQuery("SELECT * FROM Projects WHERE Email LIKE '" + Email + "'");
    }

    public static ResultSet checkID(Statement statement) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return check_ID(statement);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet check_ID(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT ID FROM Projects");
    }

    public static ResultSet checkEmail(Statement statement) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return check_Email(statement);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet check_Email(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT Email FROM Users");
    }


    public static int AddRevCostLoad(Statement statement,
                                     String table, String id, String amount, String name, String description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddRevCost_Load(statement, table, id, amount, name, description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddRevCost_Load(Statement statement,
                                      String table, String id, String amount, String name, String description)
            throws SQLException {
        int a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Amount,Name,Description) " +
                "VALUES('" + id + "','" + amount + "','" + name + "','" + description + "')");
        return a;
    }


    public static ResultSet RevCostUpload(Statement statement, String table, String id) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return RevCost_Upload(statement, table, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet RevCost_Upload(Statement statement, String table, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '" + table + "' WHERE ID = '" + id + "'  ");
    }


    public static int RevCostDelete(Statement statement, String table, String amount, String name) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return RevCost_Delete(statement, table, amount, name);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int RevCost_Delete(Statement statement, String table, String amount, String name) throws SQLException {

        int a = statement.executeUpdate("DELETE FROM '" + table + "' WHERE Amount = '" + amount + "' AND Name = '" + name + "'  ");
        return a;
    }


    public static ResultSet TodoDoneUpLoad(Statement statement,
                                           String table, String id) throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return TodoDoneUpload(statement, table, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet TodoDoneUpload(Statement statement,
                                           String table, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '" + table + "' WHERE ID = '" + id + "'  ");

    }


    public static int TodoDoneLoad(Statement statement,
                                   String table, String id, String name, String date, String description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return Todo_Done_Load(statement, table, id, name, date, description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int Todo_Done_Load(Statement statement,
                                     String table, String id, String name, String date, String description)
            throws SQLException {
        int a = 0;
        if (table.compareTo("DoneAction") == 0) {
            a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Name,Execution,Description) " +
                    "VALUES('" + id + "','" + name + "','" + date + "','" + description + "')");
        }
        if (table.compareTo("ToDoAction") == 0) {
            a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Name,Deadline,Description) " +
                    "VALUES('" + id + "','" + name + "','" + date + "','" + description + "')");
        }

        return a;
    }


    public static int TodoDoneDelete(Statement statement, String table, String name, String date) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return TodoDone_Delete(statement, table, name, date);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int TodoDone_Delete(Statement statement, String table, String name, String date) throws SQLException {

        int a = 0;
        if (table.compareTo("ToDoAction") == 0) {
            a = statement.executeUpdate("DELETE FROM '" + table + "' WHERE Name = '" + name + "' AND Deadline = '" + date + "'  ");
        }
        if (table.compareTo("DoneAction") == 0) {
            a = statement.executeUpdate("DELETE FROM '" + table + "' WHERE Name = '" + name + "' AND Execution = '" + date + "'  ");
        }
        return a;
    }


    public static int TicketsLoad(Statement statement,
                                  String id, String name, String tickets, String description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return Tickets_Load(statement, id, name, tickets, description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int Tickets_Load(Statement statement,
                                   String id, String name, String tickets, String description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO Tickets (ID,NS,Tickets,Description) " +
                "VALUES('" + id + "','" + name + "','" + tickets + "','" + description + "')");

        return a;
    }

    public static int TicketsDelete(Statement statement, String id, String name) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return Tickets_Delete(statement, id, name);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int Tickets_Delete(Statement statement, String id, String name) throws SQLException {
        int a = statement.executeUpdate("DELETE FROM Tickets WHERE ID = '" + id + "' AND NS = '" + name + "' ");
        return a;
    }


    public static ResultSet TicketsUpLoad(Statement statement,
                                          String id) throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return Tickets_Upload(statement, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet Tickets_Upload(Statement statement, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM Tickets WHERE ID = '" + id + "' ");
    }

    public static int TicketsPriceLoad(Statement statement, String id, String price)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return Tickets_Price_Load(statement, id, price);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int Tickets_Price_Load(Statement statement, String id, String price)
            throws SQLException {
        int a = statement.executeUpdate("INSERT INTO TicketsPrice (ID,Price) " +
                "VALUES('" + id + "','" + price + "')");
        return a;
    }

    public static ResultSet TicketsPriceUpLoad(Statement statement,
                                               String id) throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return Tickets_Price_Upload(statement, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet Tickets_Price_Upload(Statement statement, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM TicketsPrice WHERE ID = '" + id + "' ");
    }

    public static ResultSet staffUpload(Statement statement, String table, String id) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return staff_Upload(statement, table, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet staff_Upload(Statement statement, String table, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '" + table + "' WHERE ID = '" + id + "'  ");
    }


    public static int Add_Staff(Statement statement, String table, String id,
                                String Name, String Task, String Description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddStaff(statement, table, id, Name, Task, Description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddStaff(Statement statement, String table, String id,
                               String Name, String Task, String Description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Name,Task,Description) " +
                "VALUES('" + id + "','" + Name + "','" + Task + "','" + Description + "')");
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

    public static ResultSet inventoryUpload(Statement statement, String table, String id) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return inventory_Upload(statement, table, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet inventory_Upload(Statement statement, String table, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '" + table + "' WHERE ID = '" + id + "'  ");
    }


    public static int Add_Inventory(Statement statement, String table, String id,
                                    String Object, String Quantity, String Description)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddInventory(statement, table, id, Object, Quantity, Description);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddInventory(Statement statement, String table, String id,
                                   String Object, String Quantity, String Description)
            throws SQLException {

        int a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Object,Quantity,Description) " +
                "VALUES('" + id + "','" + Object + "','" + Quantity + "','" + Description + "')");
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

    public static ResultSet guestsUpload(Statement statement, String table, String id) throws SQLException {
        try {
            System.out.println("\n- reading database...");
            return guests_Upload(statement, table, id);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static ResultSet guests_Upload(Statement statement, String table, String id) throws SQLException {
        return statement.executeQuery("SELECT * FROM '" + table + "' WHERE ID = '" + id + "'  ");
    }

    public static int Add_Guest(Statement statement, String table, String id, String Name, String Surname, String Date, String Email,
                                String Phone, String Confirm)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return AddGuest(statement, table, id, Name, Surname, Date, Email, Phone, String.valueOf(Boolean.parseBoolean(Confirm)));
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int AddGuest(Statement statement, String table, String id, String Name, String Surname, String Date, String Email,
                               String Phone, String Confirm)
            throws SQLException {
        int a;
        if (Confirm.equals("true")) {
            a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Name,Surname,Email,Phone_Number,Confirm,Date) " +
                    "VALUES('" + id + "','" + Name + "','" + Surname + "','" + Email + "','" + Phone + "','" + 1 + "','" + Date + "')");
        } else {
            a = statement.executeUpdate("INSERT INTO '" + table + "'(ID,Name,Surname,Email,Phone_Number,Confirm,Date) " +
                    "VALUES('" + id + "','" + Name + "','" + Surname + "','" + Email + "','" + Phone + "','" + 0 + "','" + Date + "')");
        }
        return a;
    }

    public static int Delete_Guest(Statement statement, String Name, String Surname, String Date, String Email,
                                   String Phone) throws SQLException {
        try {
            System.out.println("\n- Deleting project...");
            return DeleteGuest(statement, Name, Surname, Date, Email, Phone);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int DeleteGuest(Statement statement, String Name, String Surname, String Date, String Email,
                                  String Phone) throws SQLException {
        int a = statement.executeUpdate("DELETE FROM 'Guest' WHERE Name = '" + Name + "' " +
                "AND Surname = '" + Surname + "' " + "AND Email = '" + Email + "' " +
                "AND Phone_Number = '" + Phone + "' " +
                "AND Date = '" + Date + "'");
        return a;
    }

    public static int guestsRefresh(Statement statement, String Name, String Surname, String Date, String Email,
                                    String Phone, String Confirm)
            throws SQLException {
        try {
            System.out.println("\n- Writing database...");
            return guests_Refresh(statement, Name, Surname, Date, Email, Phone, Confirm);
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return 0;
        }
    }

    public static int guests_Refresh(Statement statement, String Name, String Surname, String Date, String Email,
                                     String Phone, String Confirm)
            throws SQLException {
        int a;

        if (Confirm.equals("true")) {
            a = statement.executeUpdate("UPDATE Guest set Confirm = '" + 1 + "' where Email='" + Email + "'");
        } else {
            a = statement.executeUpdate("UPDATE Guest set Confirm = '" + 0 + "' where Email='" + Email + "'");
        }
        return a;
    }
}
