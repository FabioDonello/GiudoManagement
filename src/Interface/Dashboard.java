package Interface;

import Utils.Constants;
import Widgets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

    PannelloBorder center;
    PannelloBorder west;
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

        //Pannelli
        west = new PannelloBorder();
        center = new PannelloBorder();

        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);

        Box button = Box.createHorizontalBox();
        button.add(Box.createHorizontalGlue());
        button.add(homeButton);
        button.add(Box.createHorizontalStrut(10));
        button.add(logoutButton);

        west.setPreferredSize(Constants.FieldDimensions100);
        west.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        west.add(headerText, BorderLayout.NORTH);
        west.add(button, BorderLayout.NORTH);


        center.setBackground(Constants.senapeColor);
        center.setPreferredSize(Constants.FieldDimensions100);
        center.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));

        center.add(coinsButton);
        center.add(usersButton);
        center.add(storageButton);
        center.add(todoListButton);
        center.add(guestsButton);
        center.add(ticketButton);

        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);

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
                center.setVisible(false);
                try {
                    new MoneyFlow(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "personale":
                center.setVisible(false);
                try {
                    new Staff(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "magazzino":
                center.setVisible(false);
                try {
                    new Magazzino(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "todolist":
                center.setVisible(false);
                try {
                    new ToDoList(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "guests":
                center.setVisible(false);
                try {
                    new Ospiti(this, id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "ticket":
                center.setVisible(false);
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
