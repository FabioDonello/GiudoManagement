package Utils;

import Widgets.Button;
import Widgets.LabelTextField;
import Widgets.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextToInventoryTable extends JFrame implements ActionListener {

    public Button AddButton;
    public Button DeleteButton;
    public LabelTextField ObjectLabel;
    public LabelTextField QuantityLabel;
    public LabelTextField DescriptionLabel;
    public JDialog InfoFrame;

    public AddTextToInventoryTable() {

        InfoFrame = new JDialog();

        Text ObjectText = new Text("Object:");
        Text QuantityText = new Text("Quantity:");
        Text DescriptionText = new Text("Description:");

        ObjectLabel = new LabelTextField("");
        QuantityLabel = new LabelTextField(0);
        DescriptionLabel = new LabelTextField("");

        AddButton = new Button(this, "Aggiungi", "Add");
        DeleteButton = new Button(this, "Cancella", "Delete");

        JPanel InfoPanel = new JPanel(new GridLayout(4, 2));

        InfoPanel.add(ObjectText);
        InfoPanel.add(ObjectLabel);
        InfoPanel.add(QuantityText);
        InfoPanel.add(QuantityLabel);
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
