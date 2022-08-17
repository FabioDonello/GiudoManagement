package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class LabelPasswordField extends PannelloBorder {

    private final JPasswordField password;

    public LabelPasswordField(ActionListener listener, String label, KeyListener keyListener) {
        super();

        Text labePassword = new Text(label, Constants.fontLabel13);
        labePassword.setBorder(Constants.compoundBottom5);

        password = new JPasswordField("", 25);
        password.setActionCommand("login");
        password.setBorder(Constants.empty10);
        password.addActionListener(listener);
        password.addKeyListener(keyListener);

        add(labePassword, BorderLayout.NORTH);
        add(password, BorderLayout.SOUTH);
        setBorder(Constants.emptyBottom10);
    }

    public LabelPasswordField(ActionListener listener, String label, KeyListener keyListener, String actionCommand) {
        super();

        Text labePassword = new Text(label, Constants.fontLabel13);
        labePassword.setBorder(Constants.compoundBottom5);

        password = new JPasswordField("", 25);
        password.setActionCommand(actionCommand);
        password.setBorder(Constants.empty10);
        password.addActionListener(listener);
        password.addKeyListener(keyListener);

        add(labePassword, BorderLayout.NORTH);
        add(password, BorderLayout.SOUTH);
        setBorder(Constants.emptyBottom10);
    }


    public String getPassword() {
        return String.valueOf(password.getPassword());
    }


    public void getPasswordFocus() {
        password.requestFocus();
    }


    public void setPassword(String text) {
        password.setText(text);
    }
}
