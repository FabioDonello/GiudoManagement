package Interface;

import Utils.AddTextToStaffTable;
import Utils.DBOperations;
import Utils.LabelCheck;
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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Staff extends PannelloBorder implements ActionListener, MouseListener {

    PannelloBorder mainPanel;
    PannelloBorder buttonPanel;

    JTable staffJTable;
    DefaultTableModel staffTableModel;
    String id;

    public Staff(JFrame parent, String ID) throws SQLException {

        //Create
        Button addStaff = new Button(this, "Aggiungi Personale", "Add");
        Button deleteStaff = new Button(this, "Rimuovi Personale", "Delete");
        Button downloadTable = new Button(this, "Scarica", "Download");

        staffTableModel = new DefaultTableModel() {

            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };
        staffTableModel.addColumn("Name");
        staffTableModel.addColumn("Task");
        staffTableModel.addColumn("Description");

        staffJTable = new JTable(staffTableModel);
        staffJTable.setAutoCreateRowSorter(true);
        staffJTable.setBounds(30, 40, 300, 280);
        staffJTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(staffJTable));

        JScrollPane staffJScrollPane = new JScrollPane(staffJTable);

        //Panels
        mainPanel = new PannelloBorder(new GridLayout());
        buttonPanel = new PannelloBorder(new GridLayout());

        Box button = Box.createHorizontalBox();
        button.add(Box.createHorizontalGlue());
        button.add(downloadTable);
        button.add(Box.createHorizontalStrut(10));
        button.add(addStaff);
        button.add(Box.createHorizontalStrut(10));
        button.add(deleteStaff);

        mainPanel.add(staffJScrollPane);
        buttonPanel.add(button, BorderLayout.CENTER);

        //Container
        Container contentView = new Container();
        contentView.add(mainPanel);
        contentView.add(buttonPanel);
        parent.add(contentView);
        parent.setLocationRelativeTo(null);
        setVisible(true);

        id = ID;
        UploadDataStaff();
    }

    public void UploadDataStaff() throws SQLException {

        Statement statement = DBOperations.establish_connection();
        ResultSet resultSet = DBOperations.staffUpload(statement, "Staff", id);
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
                List<String> data = new LinkedList<String>(
                        Arrays.asList(Name, Task, Description));
                switch (cmd) {
                    case "Add":
                        Statement statement = null;
                        try {
                            if (LabelCheck.isEmpty(data)) {
                                JOptionPane.showMessageDialog(null, "Attention, you must fill in all fields correctly!",
                                        "Warning", JOptionPane.WARNING_MESSAGE);
                                addTextToStaffTable.dispose();
                            } else {
                                statement = DBOperations.establish_connection();
                                DBOperations.Add_Staff(statement, "Staff", id, Name, Task, Description);
                                staffTableModel.insertRow(staffTableModel.getRowCount(), new Object[]{Name, Task, Description});
                                addTextToStaffTable.Close();
                            }
                            break;
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
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
        int index;
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
