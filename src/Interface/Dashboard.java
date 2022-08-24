package Interface;

import Utils.Constants;
import Widgets.*;
import Widgets.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Dashboard extends JFrame implements ActionListener, MouseListener {


    public Dashboard() {

        super("Giudo - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Text headerText = new Text("HOME", Constants.fontLabel26);

        Button funz1 = new Button(this, "Contabilità", "cmd1");
        funz1.setBounds(10,10,30,30);
        Button funz2 = new Button(this, "Personale", "cmd2");
        Button funz3 = new Button(this, "Magazzino", "cmd3");
        Button funz4 = new Button(this, "To-Do List", "cmd4");
        Button funz5 = new Button(this, "Sponsor/Fornitori", "cmd5");
        Button funz6 = new Button(this, "Pre-vendite", "cmd6");

        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        headerText.setForeground(Color.GREEN);

        PannelloBorder west = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder center = new PannelloBorder(new GridLayout(3, 2));

        west.setBackground(Constants.bluElettrico);
        west.setPreferredSize(new Dimension(100, 100));
        west.add(headerText, BorderLayout.NORTH);

        center.setBackground(Constants.senapeColor);
        center.setPreferredSize(new Dimension(100, 100));
        center.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
        center.add(funz1);
        center.add(funz2);
        center.add(funz3);
        center.add(funz4);
        center.add(funz5);
        center.add(funz6);

        /*Container contentView = new Container();
        contentView.add(west,BorderLayout.WEST);
        contentView.add(center,BorderLayout.CENTER);

        this.add(contentView);*/
        this.add(west,BorderLayout.WEST);
        this.add(center,BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setSize(900,600);
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
