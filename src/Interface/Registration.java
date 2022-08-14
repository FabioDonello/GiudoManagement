package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Utils.Constants;
import Utils.DBManager;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;
public class Registration extends JFrame implements ActionListener, MouseListener {

    public Registration() {
        super("Gestionale Eventi - Registrati");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Creo
        Text headerText = new Text("Registrazione", Constants.fontLabel26);
        Text subText = new Text("Inserisci i dati richiesti per procedere alla registrazione");

        Text name_text = new Text("Name:    ");
        LabelTextField name_field = new LabelTextField();

        Text surname_text = new Text("Surname:    ");
        LabelTextField surname_field = new LabelTextField();

        Text email_text = new Text("Email:    ");
        LabelTextField email_field = new LabelTextField();

        Text password_text = new Text("Password: ");
        PasswordTextField password_field = new PasswordTextField();


        Button deletebutton = new Button(this, "Delete", "Delete");
        Button loginbutton = new Button(this, "Sing in", "Sing in");

        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        subText.setBorder(Constants.compoundBottom20);

        name_text.setBorder(Constants.compoundBottom20);
        surname_text.setBorder(Constants.compoundBottom20);
        email_text.setBorder(Constants.compoundBottom20);
        password_text.setBorder(Constants.compoundBottom20);

        name_field.setBorder(Constants.compoundBottom20);
        surname_field.setBorder(Constants.compoundBottom20);
        email_field.setBorder(Constants.compoundBottom20);
        password_field.setBorder(Constants.compoundBottom20);

        //Pannelli
        PannelloBorder pannelloLogo = new PannelloBorder();
        PannelloBorder pannelloSingIn = new PannelloBorder();
        PannelloBorder pannelloButtonSingIn = new PannelloBorder();
        PannelloBorder pannelloButtonAnnulla = new PannelloBorder();
        JPanel pannelloAdmin = new JPanel();

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pannelloButtonSingIn.add(loginbutton);
        pannelloButtonSingIn.setBorder(Constants.emptyBottom5);

        pannelloButtonAnnulla.add(deletebutton);
        pannelloButtonAnnulla.setBorder(Constants.emptyBottom20);

        //Grid

        GrigliaBorder grigliaSingIn = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(name_text,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(name_field,a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 1;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(surname_text,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 1;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(surname_field,a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 2;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(email_text,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 2;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(email_field,a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 3;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(password_text,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 3;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(password_field,a);
        pannelloSingIn.add(grigliaSingIn);

        //Container
        Container contentView = new Container();
        contentView.add(pannelloLogo);
        contentView.add(pannelloSingIn);
        contentView.add(pannelloButtonSingIn);
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
            case "Sing in":
                DBManager.setConnection(DBManager.JDBC_Driver,DBManager.JDBC_URL);
                try {
                    Statement statement = DBManager.getConnection().createStatement(
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    DBManager.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
