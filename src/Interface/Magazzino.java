package Interface;

import Utils.Constants;
import Widgets.PannelloBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Magazzino extends PannelloBorder implements ActionListener,MouseListener {

    public Magazzino(JFrame parent){

        PannelloBorder pannelloMagazzino= new PannelloBorder();

        pannelloMagazzino.setBackground(Constants.greenAccent);
        pannelloMagazzino.setPreferredSize(Constants.FieldDimensions100);

        parent.add(pannelloMagazzino,BorderLayout.CENTER);

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
