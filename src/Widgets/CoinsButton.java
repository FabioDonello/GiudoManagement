package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CoinsButton extends JButton {

    public CoinsButton(ActionListener listener, String text, String cmd) {
        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setIcon(Constants.coinsIcon);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


    }
}

