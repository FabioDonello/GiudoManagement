import Interface.Welcome;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                new Welcome();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}