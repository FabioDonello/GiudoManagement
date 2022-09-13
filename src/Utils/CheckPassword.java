package Utils;

import javax.swing.*;
import java.lang.String;

public class CheckPassword {
    public static boolean isValid(String password) {
        if (!((password.length()) >= 8)) {
            JOptionPane.showMessageDialog(null, "Short password. Minimum 8 characters.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (password.contains(" ")) {
            JOptionPane.showMessageDialog(null, "The password must not contain spaces!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int i = 0, count = 0;
        while (i <= 9) {
            String str = Integer.toString(i);
            if (password.contains(str)) {
                count = 1;
                break;
            }
            i++;
        }
        return count != 0;
    }
}
