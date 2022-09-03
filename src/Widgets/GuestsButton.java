package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuestsButton extends JButton {

    public GuestsButton(ActionListener listener, String text, String cmd) {

        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setIcon(Constants.guestsIcon);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
