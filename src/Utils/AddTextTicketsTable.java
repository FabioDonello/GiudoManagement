package Utils;

import Widgets.Button;
import Widgets.LabelTextField;
import Widgets.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextTicketsTable extends JFrame implements ActionListener {
    public Button Add_button;
    public LabelTextField TicketsLabel;
    public LabelTextField NameLabel;
    public LabelTextField DescriptionLabel;

    public JDialog InfoFrame;

    public AddTextTicketsTable() {
        InfoFrame = new JDialog();

        Text NameText = new Text("Name and Surname:");
        Text TicketsText = new Text("Number of ticket:");
        Text DescriptionText = new Text("Description:");

        NameLabel = new LabelTextField("");
        TicketsLabel = new LabelTextField(1.0);
        DescriptionLabel = new LabelTextField("");

        Add_button = new Button(this, "Add", "Add");
        Button Delete_button = new Button(this, "Cancel", "Del");

        JPanel InfoPanel = new JPanel(new GridLayout(4, 2));

        InfoPanel.add(NameText);
        InfoPanel.add(NameLabel);
        InfoPanel.add(TicketsText);
        InfoPanel.add(TicketsLabel);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.compareTo("Del") == 0) {
            Close();
        }
    }
}
