package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;
import Utils.Constants;
import Utils.DBOperations;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;

public class PreMainPage extends JFrame implements ActionListener, MouseListener {

    private final LabelTextField newProject_field;
    private final LabelTextField loadProject_field;

    public PreMainPage(String Creator){

        super("GIUDO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        Text headerText = new Text("GIUDO", Constants.fontLabel26);
        Text newText = new Text("Assegna un nome al progetto:");
        Text loadText = new Text("Seleziona uno dei progetti esistenti:");

        Button NewProjectButton = new Button(this, "New project", "New");
        Button LoadDbProjectButton = new Button(this, "Load existing project", "Load");

        newProject_field = new LabelTextField();
        newProject_field.setBorder(Constants.compoundBottom20);

        loadProject_field = new LabelTextField();
        loadProject_field.setBorder(Constants.compoundBottom20);



        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);

        newText.setHorizontalAlignment(SwingConstants.CENTER);
        newText.setBorder(Constants.compoundBottom20);

        loadText.setHorizontalAlignment(SwingConstants.CENTER);
        loadText.setBorder(Constants.compoundBottom20);
        ;

        //Pannelli
        PannelloBorder pannelloLogo = new PannelloBorder();
        PannelloBorder pannelloNew = new PannelloBorder();
        PannelloBorder pannelloLoad = new PannelloBorder();

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(newText, BorderLayout.SOUTH);

        GrigliaBorder grigliaNewProjects = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 1;
        a.weighty = 1;
        grigliaNewProjects.add(newText,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 0;
        a.gridy = 1;
        a.weightx = 1;
        a.weighty = 1;
        grigliaNewProjects.add(newProject_field,a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 0;
        a.gridy = 2;
        a.weightx = 1;
        a.weighty = 1;
        grigliaNewProjects.add(NewProjectButton,a);
        pannelloNew.add(grigliaNewProjects);

        GrigliaBorder grigliaLoadProjects = new GrigliaBorder();
        GridBagConstraints b = new GridBagConstraints();

        b.fill = GridBagConstraints.HORIZONTAL;
        b.gridx = 0;
        b.gridy = 0;
        b.weightx = 1;
        b.weighty = 1;
        grigliaLoadProjects.add(loadText,b);

        b.fill = GridBagConstraints.HORIZONTAL;
        b.gridx = 0;
        b.gridy = 1;
        b.weightx = 1;
        b.weighty = 1;
        grigliaLoadProjects.add(loadProject_field,b);

        b.fill = GridBagConstraints.HORIZONTAL;
        b.gridx = 0;
        b.gridy = 2;
        b.weightx = 1;
        b.weighty = 1;
        grigliaLoadProjects.add(LoadDbProjectButton,b);
        pannelloLoad.add(grigliaLoadProjects);


        //Container
        Container contentView = new Container();
        contentView.add(pannelloLogo);
        contentView.add(pannelloNew);
        contentView.add(pannelloLoad);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "New":
                try {
                    Statement statement = DBOperations.establish_connection();
                    String DBName = newProject_field.getText();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                dispose();
                new MainPage();
                break;

            case "Load":

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
