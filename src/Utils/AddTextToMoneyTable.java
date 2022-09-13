package Utils;

import Widgets.*;
import Widgets.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextToMoneyTable extends JFrame implements ActionListener {

    public Button Add_button;
    public LabelTextField ValueLabel;
    public LabelTextField NameLabel;
    public LabelTextField DescriptionLabel;

    public JDialog InfoFrame;

    public AddTextToMoneyTable() {

        InfoFrame = new JDialog();

        Text ValueText = new Text("Amount:");
        Text NameText = new Text("Name:");
        Text DescriptionText = new Text("Description:");

        ValueLabel = new LabelTextField(1.0);
        NameLabel = new LabelTextField("");
        DescriptionLabel = new LabelTextField("");

        Add_button = new Button(this, "Add", "Add");
        Button Delete_button = new Button(this, "Cancel", "Del");

        JPanel InfoPanel = new JPanel(new GridLayout(4, 2));

        InfoPanel.add(ValueText);
        InfoPanel.add(ValueLabel);
        InfoPanel.add(NameText);
        InfoPanel.add(NameLabel);
        InfoPanel.add(DescriptionText);
        InfoPanel.add(DescriptionLabel);
        InfoPanel.add(Add_button);
        InfoPanel.add(Delete_button);


        InfoFrame.add(InfoPanel);
        InfoFrame.setSize(400, 200);
        InfoFrame.setLocationRelativeTo(null);
        InfoFrame.setVisible(true);

    }

    public void Close() {
        InfoFrame.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
