package Interface;

import Utils.AddTextToGuestsTable;
import Utils.Constants;
import Utils.DBOperations;
import Widgets.Button;
import Widgets.Container;
import Widgets.PannelloBorder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Ospiti extends PannelloBorder implements ActionListener, MouseListener {

    PannelloBorder mainPanel;
    PannelloBorder buttonPanel;
    JTable guestsJTable;
    DefaultTableModel guestsTableModel = null;

    public Ospiti(JFrame parent) throws SQLException {

        //Create
        Button addGuests = new Button(this, "Aggiungi ospite", "Add");
        Button deleteGuests = new Button(this, "Rimuovi ospite", "Delete");
        Button downloadTable = new Button(this, "Scarica", "Download");

        guestsTableModel = new DefaultTableModel() {


            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4)
                    return true;
                else
                    return false;
            }
        };
        guestsTableModel.addColumn("Name");
        guestsTableModel.addColumn("Surname");
        guestsTableModel.addColumn("Email");
        guestsTableModel.addColumn("Phone number");
        guestsTableModel.addColumn("Confirm");

        guestsJTable = new JTable(guestsTableModel);
        guestsJTable.setAutoCreateRowSorter(true);
        guestsJTable.setBounds(30, 40, 230, 280);
        guestsJTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(guestsJTable));

        JScrollPane guestsJScrollPane = new JScrollPane(guestsJTable);

        //Panels
        mainPanel = new PannelloBorder();
        buttonPanel = new PannelloBorder();

        Box button = Box.createHorizontalBox();
        button.add(Box.createHorizontalGlue());
        button.add(downloadTable);
        button.add(Box.createHorizontalStrut(10));
        button.add(addGuests);
        button.add(Box.createHorizontalStrut(10));
        button.add(deleteGuests);

        mainPanel.add(guestsJScrollPane);
        buttonPanel.add(button, BorderLayout.CENTER);

        //Container
        Container contentView = new Container();
        contentView.add(mainPanel);
        contentView.add(buttonPanel);
        parent.add(contentView, BorderLayout.CENTER);

        setVisible(true);

        UploadDataGuest();
    }

    public void UploadDataGuest() throws SQLException {

        Statement statement = DBOperations.establish_connection();
        ResultSet resultSet = DBOperations.guestsUpload(statement);
        if (resultSet != null) {
            while (resultSet.next()) {
                String DBName = resultSet.getString("Name");
                String DBSurname = resultSet.getString("Surname");
                String DBEmail = resultSet.getString("Email");
                String DBPhone = resultSet.getString("Phone_Number");
                Integer DBConfirm = Integer.valueOf(resultSet.getString("Confirm"));

                if (DBConfirm.equals(1)) {
                    guestsTableModel.insertRow(guestsTableModel.getRowCount(),
                            new Object[]{DBName, DBSurname, DBEmail, DBPhone, Boolean.TRUE});
                } else {
                    guestsTableModel.insertRow(guestsTableModel.getRowCount(),
                            new Object[]{DBName, DBSurname, DBEmail, DBPhone, Boolean.FALSE});
                }
            }
        }
    }

    public void AddGuest() throws SQLException {

        AddTextToGuestsTable addTextToGuestsTable = new AddTextToGuestsTable();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Name = addTextToGuestsTable.NameLabel.getText();
                String Surname = addTextToGuestsTable.SurnameLabel.getText();
                String Email = addTextToGuestsTable.EmailLabel.getText();
                String Phone = addTextToGuestsTable.PhoneLabel.getText();

                switch (cmd) {
                    case "Add":
                        Statement statement = null;
                        try {
                            statement = DBOperations.establish_connection();
                            DBOperations.Add_Guest(statement, Name, Surname, Email, Phone);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        guestsTableModel.insertRow(guestsTableModel.getRowCount(), new Object[]{Name, Surname, Email, Phone});
                        addTextToGuestsTable.Close();
                        break;
                    case "Delete":
                        addTextToGuestsTable.Close();
                }
            }
        };
        addTextToGuestsTable.AddButton.addActionListener(actionListener);
    }

    public void DeleteGuest() throws SQLException {
        String Name;
        String Surname;
        String Email;
        String Phone;
        int index = 0;
        index = guestsJTable.getSelectedRow();
        Name = (String) guestsTableModel.getValueAt(index, 0);
        Surname = (String) guestsTableModel.getValueAt(index, 1);
        Email = (String) guestsTableModel.getValueAt(index, 2);
        Phone = (String) guestsTableModel.getValueAt(index, 3);
        if (index != -1) {
            Statement statement = DBOperations.establish_connection();
            DBOperations.Delete_Guest(statement, Name, Surname, Email, Phone);
            guestsTableModel.removeRow(index);
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }

    public void downloadTable() throws FileNotFoundException {

        String file = "C:\\Users\\39377\\Desktop\\SW";

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Add":
                try {
                    AddGuest();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Delete":
                try {
                    DeleteGuest();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Download":
                try {
                    downloadTable();
                } catch (FileNotFoundException ex) {
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
