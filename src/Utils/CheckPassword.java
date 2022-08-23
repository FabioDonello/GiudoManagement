package Utils;

import java.lang.String;

public class CheckPassword {
    public static boolean isValid(String password) {
        if (!((password.length()) >= 8)) return false;
        if (password.contains(" ")) return false;
        int i = 0, count = 0;
        while (i <= 9) {
            String str = Integer.toString(i);
            if (password.contains(str)) {
                count = 1;
            }
            i++;
        }
        return count != 0;
    }
}
