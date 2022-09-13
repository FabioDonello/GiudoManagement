package Utils;

import java.util.Iterator;
import java.util.List;

public class LabelCheck {
    public static boolean isEmpty(List<String> data) {
        for (Iterator<String> tmp = data.iterator(); tmp.hasNext(); ) {
            String d = tmp.next();
            System.out.println(d);
            if (d.isBlank() || d.equals("null")) {
                return true;
            }
        }
        return false;
    }
}
