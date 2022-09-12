package Utils;

import java.util.Iterator;
import java.util.List;

public class LabelCheck {
    public static boolean isEmpty(List<String> data){
        for(Iterator<String> tmp =data.iterator();tmp.hasNext();){
            String d=tmp.next();
            if(d.isBlank()) {
                return false;
            }
        }
        return true;
    }
}
