package Interface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Utils.*;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;

import javax.swing.plaf.basic.BasicArrowButton;

public class Registration extends JFrame implements ActionListener, MouseListener {

    private final LabelTextField name_field;
    private final LabelTextField surname_field;
    private final LabelTextField email_field;
    private final LabelTextField password_field;

    public Registration() {
        super("Gestionale Eventi - Registrati");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Creo
        BackButton backButton = new BackButton(this);

        Text headerText = new Text("Registrazione", Constants.fontLabel26);
        Text subText = new Text("Inserisci i dati richiesti per procedere alla registrazione");

        Text name_text = new Text("Name:    ");
        name_field = new LabelTextField();

        Text surname_text = new Text("Surname:    ");
        surname_field = new LabelTextField();

        Text email_text = new Text("Email:    ");
        email_field = new LabelTextField();

        Text password_text = new Text("Password: ");
        password_field = new LabelTextField();

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
        PannelloBorder pannelloArrow = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloLogo = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloSingIn = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloButtonSingIn = new PannelloBorder(new GridLayout(3, 2));

        pannelloArrow.add(backButton, BorderLayout.WEST);

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pannelloButtonSingIn.add(loginbutton);
        pannelloButtonSingIn.setBorder(Constants.emptyBottom5);


        //Grid

        GrigliaBorder grigliaSingIn = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(name_text, a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(name_field, a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 1;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(surname_text, a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 1;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(surname_field, a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 2;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(email_text, a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 2;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(email_field, a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 3;
        a.weightx = 0.1;
        a.weighty = 0.1;
        grigliaSingIn.add(password_text, a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 3;
        a.weightx = 1;
        a.weighty = 1;
        grigliaSingIn.add(password_field, a);
        pannelloSingIn.add(grigliaSingIn);

        //Container
        Container contentView = new Container();
        contentView.add(pannelloArrow);
        contentView.add(pannelloLogo);
        contentView.add(pannelloSingIn);
        contentView.add(pannelloButtonSingIn);

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
                try {
                    Statement statement = DBOperations.establish_connection();
                    String name = name_field.getText();
                    String surname = surname_field.getText();
                    String email = email_field.getText();
                    String password = password_field.getText();

                    List<String> data=new LinkedList<String>(
                            Arrays.asList(name,surname,email,password));

                    if (LabelCheck.isEmpty(data)) {
                        JOptionPane.showMessageDialog(null, "Attention, you must fill in all fields correctly!", "Warning"
                                ,JOptionPane.WARNING_MESSAGE);
                        dispose();
                        new Registration();
                        break;
                    } else if (!CheckEmail.isAddressValid(email)) {
                        JOptionPane.showMessageDialog(null, "Incorrect Email!", "Warning", JOptionPane.WARNING_MESSAGE);
                        dispose();
                        new Registration();
                        break;
                    } else if (!CheckEmail.checkEmail(email)) {
                        dispose();
                        new Registration();
                        break;
                    } else if (!CheckPassword.isValid(password)) {
                        dispose();
                        new Registration();
                        break;
                    } else {
                        int a = DBOperations.userLoad(statement, name, surname, email, password);
                        if (a == 0) {
                            JOptionPane.showMessageDialog(null, "Loading users failed!", "Warning", JOptionPane.WARNING_MESSAGE);
                            dispose();
                            new Registration();
                        } else {
                            dispose();
                            new PreMainPage(email);
                        }
                        break;
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            case "":
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
