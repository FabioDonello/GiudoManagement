package Widgets;

import javax.swing.*;

import Utils.Constants;
import javax.swing.*;
import java.awt.*;
public class Text extends JLabel {
    public Text(String testo){
        super(testo);
        setFont(Constants.fontText15);
    }
    public Text(String testo, Font font){
        super(testo);
        setFont(font);
    }


}
