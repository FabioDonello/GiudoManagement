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
public class UserLogin extends JFrame implements ActionListener, MouseListener {

    public UserLogin() {
        super("Gestionale Eventi - Accedi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Creo
        Text headerText = new Text("Accedi", Constants.fontLabel26);
        Text subText = new Text("Accedi utilizzando le credenziali utilizzate al momento della registrazione");

        Text email_text = new Text("Email:    ");
        LabelTextField email_field = new LabelTextField();
        email_field.setBorder(Constants.compoundBottom20);
        Text password_text = new Text("Password: ");
        PasswordTextField password_field = new PasswordTextField();
        password_field.setBorder(Constants.compoundBottom20);


        Button deletebutton = new Button(this, "Delete", "Delete");
        Button loginbutton = new Button(this, "Login", "Login");

        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        subText.setBorder(Constants.compoundBottom20);

        email_text.setBorder(Constants.compoundBottom20);
        password_text.setBorder(Constants.compoundBottom20);

        //Pannelli
        PannelloBorder pannelloLogo = new PannelloBorder();
        PannelloBorder pannelloLogin = new PannelloBorder();
        PannelloBorder pannelloButtonAccedi = new PannelloBorder();
        PannelloBorder pannelloButtonAnnulla = new PannelloBorder();
        JPanel pannelloAdmin = new JPanel();

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pannelloButtonAccedi.add(loginbutton);
        pannelloButtonAccedi.setBorder(Constants.emptyBottom5);

        pannelloButtonAnnulla.add(deletebutton);
        pannelloButtonAnnulla.setBorder(Constants.emptyBottom20);

        //Grid

        GrigliaBorder griglialogin = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.1;
        a.weighty = 0.1;
        griglialogin.add(email_text,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 1;
        a.weighty = 1;
        griglialogin.add(email_field,a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 1;
        a.weightx = 0.1;
        a.weighty = 0.1;
        griglialogin.add(password_text,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 1;
        a.weightx = 1;
        a.weighty = 1;
        griglialogin.add(password_field,a);
        pannelloLogin.add(griglialogin);

        //Container
        Container contentView = new Container();
        contentView.add(pannelloLogo);
        contentView.add(pannelloLogin);
        contentView.add(pannelloButtonAccedi);
        contentView.add(pannelloButtonAnnulla);
        contentView.add(pannelloAdmin);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Login":
                dispose();
                new UserLogin();
                break;
            case "Delete":
                dispose();
                new Welcome();
                break;
            default:
                break;
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
