package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class CheckEmail {

    public static boolean isAddressValid(String address) {

        return false;
    }

    public static boolean checkEmail(String Email) throws SQLException {

        Statement statement = DBOperations.establish_connection();
        ResultSet resultSet = DBOperations.checkEmail(statement);
        if (resultSet != null) {
            while (resultSet.next()) {
                String DBEmail = resultSet.getString("Email");
                if (Email.equals(DBEmail)) {
                    JOptionPane.showMessageDialog(null, "This email already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }
}
