package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Utils.Constants;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;

public class Welcome extends JFrame implements ActionListener, MouseListener {

    public Welcome() {
        super("Gestionale Eventi - Benvenuti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text headerText = new Text("Gestionale Eventi", Constants.fontLabel26);
        Text subText = new Text("Con questo software potrai gestire i tuoi eventi.");
        Button loginButton = new Button(this, "Accedi");
        Button registerButton = new Button(this, "Registrati", "regis");

        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        subText.setBorder(Constants.compoundBottom20);

        //Pannelli
        PannelloBorder pannelloLogo = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloButtonAccedi = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloButtonRegis = new PannelloBorder(new GridLayout(3, 2));

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pannelloButtonAccedi.add(loginButton);
        pannelloButtonAccedi.setBorder(Constants.emptyBottom5);

        pannelloButtonRegis.add(registerButton);
        pannelloButtonRegis.setBorder(Constants.emptyBottom20);

        //Container
        Container contentView = new Container();

        contentView.add(pannelloLogo);
        contentView.add(pannelloButtonAccedi);
        contentView.add(pannelloButtonRegis);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "login" -> {
                dispose();
                new Login();
            }
            case "regis" -> {
                dispose();
                new Registration();
            }
            default -> {
            }
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