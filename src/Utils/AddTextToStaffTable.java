package Utils;

import Widgets.Button;
import Widgets.LabelTextField;
import Widgets.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextToStaffTable extends JFrame implements ActionListener {

    public Button AddButton;
    public Button DeleteButton;
    public LabelTextField NameLabel;
    public LabelTextField TaskLabel;
    public LabelTextField DescriptionLabel;

    public JDialog InfoFrame;

    public AddTextToStaffTable() {

        InfoFrame = new JDialog();

        Text NameText = new Text("Name:");
        Text TaskText = new Text("Task:");
        Text DescriptionText = new Text("Description:");

        NameLabel = new LabelTextField("");
        TaskLabel = new LabelTextField("");
        DescriptionLabel = new LabelTextField("");

        AddButton = new Button(this, "Aggiungi", "Add");
        DeleteButton = new Button(this, "Cancella", "Delete");

        JPanel InfoPanel = new JPanel(new GridLayout(4, 2));

        InfoPanel.add(NameText);
        InfoPanel.add(NameLabel);
        InfoPanel.add(TaskText);
        InfoPanel.add(TaskLabel);
        InfoPanel.add(DescriptionText);
        InfoPanel.add(DescriptionLabel);
        InfoPanel.add(AddButton);
        InfoPanel.add(DeleteButton);

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
