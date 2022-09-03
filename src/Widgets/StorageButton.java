package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StorageButton extends JButton {

    public StorageButton(ActionListener listener, String text, String cmd) {
        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setIcon(Constants.garageIcon);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }
}
