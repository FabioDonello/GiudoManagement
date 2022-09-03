package Interface;

import Utils.Constants;
import Widgets.PannelloBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToDoList extends PannelloBorder implements ActionListener, MouseListener {

    public ToDoList(JFrame parent) {

        PannelloBorder pannellotodolist = new PannelloBorder();

        pannellotodolist.setBackground(Color.CYAN);
        pannellotodolist.setPreferredSize(Constants.FieldDimensions100);

        parent.add(pannellotodolist, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
