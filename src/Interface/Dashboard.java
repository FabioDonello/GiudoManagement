package Interface;

import Utils.Constants;
import Widgets.*;
import Widgets.Button;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

    JPanel DecisionMenu;
    PannelloBorder MainMenu;

    public String id;

    public Dashboard(String ID) {

        super("Giudo - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text headerText = new Text("HOME", Constants.fontLabel26);

        CoinsButton coinsButton = new CoinsButton(this, "Contabilità", "costi");
        UsersButton usersButton = new UsersButton(this, "Personale", "personale");
        StorageButton storageButton = new StorageButton(this, "Magazzino", "magazzino");
        TodoListButton todoListButton = new TodoListButton(this, "To-Do List", "todolist");
        GuestsButton guestsButton = new GuestsButton(this, "Ospiti", "guests");
        TicketButton ticketButton = new TicketButton(this, "Pre-vendite", "ticket");

        HomeButton homeButton = new HomeButton(this, ID);
        LogoutButton logoutButton = new LogoutButton(this);
        BackButton backButton = new BackButton(this);
        Button SpaceButton = new Button(this, "");
        SpaceButton.setBorder(Constants.emptyBottom5);
        SpaceButton.setEnabled(false);


        //Pannelli
        MainMenu = new PannelloBorder();
        DecisionMenu = new JPanel(new GridLayout(2, 3));

        //UI Settings
        //headerText.setBorder(Constants.compoundBottom5);


        Box button = Box.createHorizontalBox();
        button.add(Box.createHorizontalGlue());
        button.add(homeButton);
        button.add(Box.createHorizontalStrut(10));
        button.add(logoutButton);
        button.add(Box.createHorizontalStrut(10));
        button.add(backButton);


        /*
        MainMenu.setPreferredSize(Constants.FieldDimensions100);
        MainMenu.setMaximumSize(Constants.FieldDimensions100);
        MainMenu.setMinimumSize(Constants.FieldDimensions100);
        MainMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         */


        GrigliaBorder InfoGrid1 = new GrigliaBorder();
        GridBagConstraints a1 = new GridBagConstraints();

        a1.fill = GridBagConstraints.HORIZONTAL;
        a1.gridx = 0;
        a1.gridy = 0;
        InfoGrid1.add(headerText, a1);

        a1.fill = GridBagConstraints.HORIZONTAL;
        a1.ipady = 315;
        a1.gridx = 0;
        a1.gridy = 1;
        InfoGrid1.add(SpaceButton, a1);

        a1.fill = GridBagConstraints.HORIZONTAL;
        a1.gridx = 0;
        a1.gridy = 2;
        InfoGrid1.add(button, a1);
        MainMenu.add(InfoGrid1);



        /*DecisionMenù.setPreferredSize(Constants.FieldDimensions100);
        DecisionMenù.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
         */

        DecisionMenu.setBackground(Constants.senapeColor);

        DecisionMenu.add(coinsButton);
        DecisionMenu.add(usersButton);
        DecisionMenu.add(storageButton);
        DecisionMenu.add(todoListButton);
        DecisionMenu.add(guestsButton);
        DecisionMenu.add(ticketButton);

        this.add(MainMenu, BorderLayout.WEST);
        this.add(DecisionMenu, BorderLayout.CENTER);

        setSize(900, 600);
        setVisible(true);
        setLocationRelativeTo(null);

        id = ID;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        switch (cmd) {
            case "costi":
                DecisionMenu.setVisible(false);
                try {
                    new MoneyFlow(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "personale":
                DecisionMenu.setVisible(false);
                try {
                    new Staff(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "magazzino":
                DecisionMenu.setVisible(false);
                try {
                    new Magazzino(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "todolist":
                DecisionMenu.setVisible(false);
                try {
                    new ToDoList(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "guests":
                DecisionMenu.setVisible(false);
                try {
                    new Ospiti(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "ticket":
                DecisionMenu.setVisible(false);
                try {
                    new Ticket(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            default:
                break;
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
