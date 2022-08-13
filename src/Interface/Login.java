package Interface;

import Widgets.*;
import Utils.Constants;
import Utils.Constants.*;
import Widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Login extends JFrame {
    public Login(){
        super("GiudoMenagement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        Text headerText = new Text("ciao");
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        PannelloBorder pannello = new PannelloBorder();
        pannello.add(headerText,BorderLayout.CENTER);
        Container containerview = new Container();
        containerview.add(pannello);
        this.add(containerview);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}