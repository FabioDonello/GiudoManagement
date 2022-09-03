package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {

    public Container(){
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(Constants.empty16);
    }

    public void add(GridLayout table_grid) {
    }
}
