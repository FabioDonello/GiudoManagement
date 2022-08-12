package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button(ActionListener listener, String text){
        super(text);
        addActionListener(listener);
        setActionCommand("login");
        setMargin(Constants.top10bottom10);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public Button(ActionListener listener, String text, String cmd){
        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
