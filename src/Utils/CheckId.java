package Utils;

import Interface.PreMainPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckId {

    public static boolean CheckId(String ID) throws SQLException {

        Statement statement = DBOperations.establish_connection();
        ResultSet resultSet = DBOperations.checkID(statement);
        if (resultSet != null) {
            while (resultSet.next()) {
                String DBId = resultSet.getString("ID");
                if(ID.equals(DBId)){
                    return false;
                }
            }
        }
        return true;
    }
}
