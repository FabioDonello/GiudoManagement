package Utils;

import Widgets.Button;
import Widgets.LabelTextField;
import Widgets.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextToDoTable implements ActionListener {
    public Button Add_button;
    public DateTextField DateTimeField;
    public LabelTextField NameLabel;
    public LabelTextField DescriptionLabel;

    public JDialog InfoFrame;

    public AddTextToDoTable(String s) {
        InfoFrame = new JDialog();

        Text NameText = new Text("Name:");
        Text DateText = new Text("In Deadline:");

        if (s.compareTo("DoneAction") == 0) {
            DateText.setText("In date:");
        }

        DateTimeField = new DateTextField();
        DateTimeField.setVisible(true);
        Text DescriptionText = new Text("Description:");

        NameLabel = new LabelTextField("");
        DescriptionLabel = new LabelTextField("");

        Add_button = new Button(this, "Add", "Add");
        Button Delete_button = new Button(this, "Cancel", "Del");

        JPanel InfoPanel = new JPanel(new GridLayout(4, 2));

        InfoPanel.add(NameText);
        InfoPanel.add(NameLabel);
        InfoPanel.add(DateText);
        InfoPanel.add(DateTimeField);
        InfoPanel.add(DescriptionText);
        InfoPanel.add(DescriptionLabel);
        InfoPanel.add(Add_button);
        InfoPanel.add(Delete_button);


        InfoFrame.add(InfoPanel);
        InfoFrame.setSize(450, 200);
        InfoFrame.setLocationRelativeTo(null);
        InfoFrame.setVisible(true);
    }

    public void Close() {
        InfoFrame.dispose();
    }

    public static void Error(){
        JOptionPane.showMessageDialog(null, "Attention, you must fill in all fields correctly!", "Warning"
                , JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.compareTo("Del") == 0) {
            Close();
        }

    }

}
