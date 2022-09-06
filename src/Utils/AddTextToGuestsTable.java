package Utils;

import Widgets.Button;
import Widgets.LabelTextField;
import Widgets.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextToGuestsTable extends JFrame implements ActionListener {

    public Button AddButton;
    public Button DeleteButton;
    public LabelTextField NameLabel;
    public LabelTextField SurnameLabel;
    public LabelTextField EmailLabel;
    public LabelTextField PhoneLabel;
    public LabelTextField DateLabel;
    public JCheckBox checkConfirm;

    public JDialog InfoFrame;

    public AddTextToGuestsTable() {

        InfoFrame = new JDialog();

        Text NameText = new Text("Name:");
        Text SurnameText = new Text("Surname:");
        Text DateText = new Text("Date of Birth:");
        Text EmailText = new Text("Email:");
        Text PhoneText = new Text("Phone Number:");
        Text ConfirmText = new Text("Confirm:");

        NameLabel = new LabelTextField();
        SurnameLabel = new LabelTextField();
        DateLabel = new LabelTextField();
        EmailLabel = new LabelTextField();
        PhoneLabel = new LabelTextField();
        checkConfirm=new JCheckBox();

        AddButton = new Button(this, "Aggiungi", "Add");
        DeleteButton = new Button(this, "Rimuovi", "Delete");

        JPanel InfoPanel = new JPanel(new GridLayout(7, 2));

        InfoPanel.add(NameText);
        InfoPanel.add(NameLabel);
        InfoPanel.add(SurnameText);
        InfoPanel.add(SurnameLabel);
        InfoPanel.add(DateText);
        InfoPanel.add(DateLabel);
        InfoPanel.add(EmailText);
        InfoPanel.add(EmailLabel);
        InfoPanel.add(PhoneText);
        InfoPanel.add(PhoneLabel);
        InfoPanel.add(ConfirmText);
        InfoPanel.add(checkConfirm);
        InfoPanel.add(AddButton);
        InfoPanel.add(DeleteButton);

        InfoFrame.add(InfoPanel);
        InfoFrame.setSize(400, 400);
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
