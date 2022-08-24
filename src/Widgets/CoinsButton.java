package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CoinsButton extends JButton {

    public CoinsButton(ActionListener listener,String text,String cmd) {
        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setIcon(Constants.coinsIcon);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


    }
}

