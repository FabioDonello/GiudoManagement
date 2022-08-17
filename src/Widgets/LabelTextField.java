package Widgets;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class LabelTextField extends PannelloBorder {

    private final JTextField textField;

    public LabelTextField(ActionListener listener, String label, KeyListener keyListener) {
        super();

        Text labelUsername = new Text(label, Constants.fontLabel13);
        labelUsername.setBorder(Constants.compoundBottom5);

        textField = new JTextField("", 25);
        textField.setActionCommand("login");
        textField.setBorder(Constants.empty10);
        textField.addActionListener(listener);
        textField.addKeyListener(keyListener);

        add(labelUsername, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);
    }

    public LabelTextField(ActionListener listener, String label, KeyListener keyListener, String actionCommand) {
        super();

        Text labelUsername = new Text(label, Constants.fontLabel13);
        labelUsername.setBorder(Constants.compoundBottom5);

        textField = new JTextField("", 25);
        textField.setActionCommand(actionCommand);
        textField.setBorder(Constants.empty10);
        textField.addActionListener(listener);
        textField.addKeyListener(keyListener);

        add(labelUsername, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);
    }

    public LabelTextField(ActionListener listener, String label, String text, String actionCommand) {
        super();
        Text labelUsername = new Text(label, Constants.fontLabel13);
        labelUsername.setBorder(Constants.compoundBottom5);

        textField = new JTextField(text, 25);
        textField.setActionCommand(actionCommand);
        textField.setBorder(Constants.empty10);
        textField.addActionListener(listener);

        add(labelUsername, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);
    }

    public String getTextField() {
        return textField.getText();
    }


    public void getFieldFocus() {
        textField.requestFocus();
    }


    public void setEnabledField(boolean value) {
        textField.setEnabled(value);
    }


    public void setTextField(String text) {
        textField.setText(text);
    }

}
