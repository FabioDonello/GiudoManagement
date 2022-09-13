package Interface;

import Utils.Constants;
import Utils.DBOperations;
import Widgets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

    JPanel DecisionMenu;
    PannelloBorder MainMenu;

    public String id;

    public Dashboard(String ID) throws SQLException {


        super("Giudo - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text headerText = new Text("DASHBOARD");

        CoinsButton coinsButton = new CoinsButton(this, "Contabilità", "costi");
        UsersButton usersButton = new UsersButton(this, "Personale", "personale");
        StorageButton storageButton = new StorageButton(this, "Magazzino", "magazzino");
        TodoListButton todoListButton = new TodoListButton(this, "To-Do List", "todolist");
        GuestsButton guestsButton = new GuestsButton(this, "Ospiti", "guests");
        TicketButton ticketButton = new TicketButton(this, "Pre-vendite", "ticket");

        HomeButton homeButton = new HomeButton(this, ID);
        PreButton preButton = new PreButton(this, GetEmail(ID));
        LogoutButton logoutButton = new LogoutButton(this);

        homeButton.setBorder(Constants.compoundBottom20);
        preButton.setBorder(Constants.compoundBottom20);
        logoutButton.setBorder(Constants.compoundBottom20);

        //Pannelli
        MainMenu = new PannelloBorder();
        DecisionMenu = new JPanel(new GridLayout(2, 3));
        MainMenu = new PannelloBorder();

        GrigliaBorder MenuGrid = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        MenuGrid.setBorder(Constants.compoundBottom5);

        a.fill = GridBagConstraints.CENTER;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 1;
        a.weighty = 1;
        MenuGrid.add(headerText, a);

        a.fill = GridBagConstraints.CENTER;
        a.ipady = 120;
        a.gridx = 0;
        a.gridy = 1;
        a.weightx = 1;
        a.weighty = 1;
        MenuGrid.add(homeButton, a);

        a.fill = GridBagConstraints.CENTER;
        a.ipady = 120;
        a.gridx = 0;
        a.gridy = 2;
        a.weightx = 1;
        a.weighty = 1;
        MenuGrid.add(preButton, a);

        a.fill = GridBagConstraints.CENTER;
        a.ipady = 120;
        a.gridx = 0;
        a.gridy = 3;
        a.weightx = 1;
        a.weighty = 1;
        MenuGrid.add(logoutButton, a);
        MenuGrid.setBorder(Constants.compoundBottom5);
        MainMenu.add(MenuGrid);

        DecisionMenu.setBackground(Constants.senapeColor);
        DecisionMenu.add(coinsButton);
        DecisionMenu.add(usersButton);
        DecisionMenu.add(storageButton);
        DecisionMenu.add(todoListButton);
        DecisionMenu.add(guestsButton);
        DecisionMenu.add(ticketButton);

        this.add(MainMenu, BorderLayout.WEST);
        this.add(DecisionMenu, BorderLayout.CENTER);

        setSize(1200, 600);
        setVisible(true);
        setLocationRelativeTo(null);

        id = ID;
    }

    public static String GetEmail(String ID) throws SQLException {
        Statement statement = DBOperations.establish_connection();
        ResultSet ris = DBOperations.getEmail(statement, ID);
        assert ris != null;
        return ris.getString("Email");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        switch (cmd) {
            case "costi" -> {
                DecisionMenu.setVisible(false);
                try {
                    new MoneyFlow(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "personale" -> {
                DecisionMenu.setVisible(false);
                try {
                    new Staff(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "magazzino" -> {
                DecisionMenu.setVisible(false);
                try {
                    new Magazzino(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "todolist" -> {
                DecisionMenu.setVisible(false);
                try {
                    new ToDoList(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "guests" -> {
                DecisionMenu.setVisible(false);
                try {
                    new Ospiti(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "ticket" -> {
                DecisionMenu.setVisible(false);
                try {
                    new Ticket(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            default -> {
            }
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
