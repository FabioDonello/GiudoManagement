package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class CheckEmail {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean isAddressValid(String address) {
        Matcher matcher=VALID_EMAIL_ADDRESS_REGEX.matcher(address);
        return matcher.find();
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
