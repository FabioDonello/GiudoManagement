package Interface;

import Utils.Constants;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

    PannelloBorder center;
    PannelloBorder west;

    public Dashboard() {

        super("Giudo - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Text headerText = new Text("HOME", Constants.fontLabel26);

        CoinsButton coinsButton = new CoinsButton(this,"Contabilità","costi");
        Button funz2 = new Button(this, "Personale", "cmd2");
        Button magazzino = new Button(this, "Magazzino", "magazzino");
        Button funz4 = new Button(this, "To-Do List", "cmd4");
        Button funz5 = new Button(this, "Sponsor/Fornitori", "cmd5");
        Button funz6 = new Button(this, "Pre-vendite", "cmd6");
        HomeButton homeButton = new HomeButton(this);
        LogoutButton logoutButton = new LogoutButton(this);

        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);

        west = new PannelloBorder();
        center = new PannelloBorder();

        Box b= Box.createHorizontalBox();
        b.add(Box.createHorizontalGlue());
        b.add(homeButton);
        b.add(Box.createHorizontalStrut(10));
        b.add(logoutButton);

        west.setPreferredSize(Constants.FieldDimensions100);
        west.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        west.add(headerText, BorderLayout.NORTH);
        west.add(b,BorderLayout.SOUTH);

        center.setBackground(Constants.senapeColor);
        center.setPreferredSize(Constants.FieldDimensions100);
        center.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        center.add(coinsButton);
        center.add(funz2);
        center.add(magazzino);
        center.add(funz4);
        center.add(funz5);
        center.add(funz6);

        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
        setSize(900, 600);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        if ("magazzino".equals(cmd)) {
            center.setVisible(false);
            new Magazzino(this);
        }
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
