package Widgets;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LabelTextField extends JTextField implements KeyListener {

    public LabelTextField(String text, KeyListener keyListener) {

        LabelTextField.super.addKeyListener(keyListener);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char c = e.getKeyChar();
                if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c) || e.getKeyCode() == KeyEvent.VK_SHIFT ||
                        e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
                    LabelTextField.super.setEditable(true);
                } else {
                    LabelTextField.super.setEditable(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                LabelTextField.super.setEditable(true);
            }
        });
    }

    public LabelTextField(Double num, KeyListener keyListener) {

        LabelTextField.super.addKeyListener(keyListener);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char c = e.getKeyChar();
                String value = LabelTextField.super.getText();

                if (Character.isLetter(c)) {
                    LabelTextField.super.setEditable(false);

                } else {
                    if (((value.contains(Character.toString(',')) || value.contains(Character.toString('.')))
                            && (c == ',' || c=='.'))) {
                        LabelTextField.super.setEditable(false);
                    } else {
                        LabelTextField.super.setEditable(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                LabelTextField.super.setEditable(true);
            }
        });
    }

    public LabelTextField(int number, KeyListener keyListener) {

        LabelTextField.super.addKeyListener(keyListener);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char c = e.getKeyChar();
                if (Character.isLetter(c) || c == ',' || c == '.') {
                    LabelTextField.super.setEditable(false);
                } else {
                    LabelTextField.super.setEditable(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                LabelTextField.super.setEditable(true);
            }
        });
    }

    public LabelTextField() {
    }

    public void setEnabledField(boolean value) {
        LabelTextField.super.setEnabled(value);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}