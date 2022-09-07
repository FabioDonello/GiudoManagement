package Interface;

import Utils.AddTextToStaffTable;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Staff extends PannelloBorder implements ActionListener, MouseListener {

    PannelloBorder mainPanel;
    PannelloBorder buttonPanel;

    JTable staffJTable;
    DefaultTableModel staffTableModel = null;

    public Staff(JFrame parent) throws SQLException {

        //Create
        Button addStaff = new Button(this, "Aggiungi Personale", "Add");
        Button deleteStaff = new Button(this, "Rimuovi Personale", "Delete");

        staffTableModel = new DefaultTableModel() {

            public boolean isCellEditable(int row, int col) {
                if (col == 0)
                    return false;
                else
                    return true;
            }
        };
        staffTableModel.addColumn("Name");
        staffTableModel.addColumn("Task");
        staffTableModel.addColumn("Description");

        staffJTable = new JTable(staffTableModel);
        staffJTable.setAutoCreateRowSorter(true);
        staffJTable.setBounds(30, 40, 230, 280);
        staffJTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(staffJTable));

        JScrollPane staffJScrollPane = new JScrollPane(staffJTable);

        //Panels
        mainPanel = new PannelloBorder();
        buttonPanel = new PannelloBorder();

        Box button = Box.createHorizontalBox();
        button.add(Box.createHorizontalGlue());
        button.add(addStaff);
        button.add(Box.createHorizontalStrut(10));
        button.add(deleteStaff);

        mainPanel.add(staffJScrollPane);
        buttonPanel.add(button, BorderLayout.CENTER);

        //Container
        Container contentView = new Container();
        contentView.add(mainPanel);
        contentView.add(buttonPanel);
        parent.add(contentView, BorderLayout.CENTER);
        parent.pack();

        setVisible(true);

        UploadDataStaff();
    }

    public void UploadDataStaff() throws SQLException {

        Statement statement = DBOperations.establish_connection();
        ResultSet resultSet = DBOperations.staffUpload(statement);
        if (resultSet != null) {
            while (resultSet.next()) {
                String DBName = resultSet.getString("Name");
                String DBTask = resultSet.getString("Task");
                String DBDescription = resultSet.getString("Description");

                staffTableModel.insertRow(staffTableModel.getRowCount(), new Object[]{DBName, DBTask, DBDescription});
            }
        }
    }

    public void AddStaff() throws SQLException {
        AddTextToStaffTable addTextToStaffTable = new AddTextToStaffTable();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Name = addTextToStaffTable.NameLabel.getText();
                String Task = addTextToStaffTable.TaskLabel.getText();
                String Description = addTextToStaffTable.DescriptionLabel.getText();

                switch (cmd) {
                    case "Add":
                        Statement statement = null;
                        try {
                            statement = DBOperations.establish_connection();
                            DBOperations.Add_Staff(statement, Name, Task, Description);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        staffTableModel.insertRow(staffTableModel.getRowCount(), new Object[]{Name, Task, Description});
                        addTextToStaffTable.Close();
                        break;
                    case "Delete":
                        addTextToStaffTable.Close();
                        break;
                }
            }
        };
        addTextToStaffTable.AddButton.addActionListener(actionListener);
    }

    public void DeleteStaff() throws SQLException {
        String Name;
        String Task;
        int index = 0;
        index = staffJTable.getSelectedRow();
        Name = (String) staffTableModel.getValueAt(index, 0);
        Task = (String) staffTableModel.getValueAt(index, 1);
        if (index != -1) {
            System.out.println(Task);
            System.out.println(Name);
            Statement statement = DBOperations.establish_connection();
            DBOperations.Delete_Staff(statement, Name, Task);
            staffTableModel.removeRow(index);
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Add":
                try {
                    AddStaff();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Delete":
                try {
                    DeleteStaff();
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
