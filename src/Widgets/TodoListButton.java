package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TodoListButton extends JButton {

    public TodoListButton(ActionListener listener, String text, String cmd) {
        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setIcon(Constants.todolistIcon);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
