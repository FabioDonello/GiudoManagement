package Interface;

import Utils.AddTextToInventoryTable;
import Utils.Constants;
import Utils.DBOperations;
import Utils.DownloadTable;
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

public class Magazzino extends PannelloBorder implements ActionListener, MouseListener {

    PannelloBorder mainPanel;
    PannelloBorder buttonPanel;

    JTable inventoryJTable;
    DefaultTableModel inventoryTableModel = null;
    String id;

    public Magazzino(JFrame parent, String ID) throws SQLException {

        //Create
        Button AddObject = new Button(this, "Aggiungi oggetto", "Add");
        Button DeleteObject = new Button(this, "Rimouvi oggetto", "Delete");
        Button DownloadTable = new Button(this, "Scarica", "Download");

        inventoryTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) {
                if (col == 0)
                    return false;
                else
                    return true;
            }
        };
        inventoryTableModel.addColumn("Object");
        inventoryTableModel.addColumn("Quantity");
        inventoryTableModel.addColumn("Description");

        inventoryJTable = new JTable(inventoryTableModel);
        inventoryJTable.setAutoCreateRowSorter(true);
        inventoryJTable.setBounds(30, 40, 230, 280);
        inventoryJTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(inventoryJTable));

        JScrollPane inventoryJScrollPane = new JScrollPane(inventoryJTable);

        //Panels
        mainPanel = new PannelloBorder();
        buttonPanel = new PannelloBorder();

        Box button = Box.createHorizontalBox();
        button.add(Box.createHorizontalGlue());
        button.add(AddObject);
        button.add(Box.createHorizontalStrut(10));
        button.add(DeleteObject);
        button.add(Box.createHorizontalStrut(10));
        button.add(DownloadTable);


        mainPanel.add(inventoryJScrollPane);
        buttonPanel.add(button, BorderLayout.CENTER);

        //Container
        Container contentView = new Container();
        contentView.add(mainPanel);
        contentView.add(buttonPanel);
        parent.add(contentView, BorderLayout.CENTER);

        setVisible(true);

        id = ID;
        UploadDataInventory();
    }

    public void UploadDataInventory() throws SQLException {
        Statement statement = DBOperations.establish_connection();
        ResultSet resultSet = DBOperations.inventoryUpload(statement, "Inventory", id);
        if (resultSet != null) {
            while (resultSet.next()) {
                String DBObject = resultSet.getString("Object");
                String DBQuantity = resultSet.getString("Quantity");
                String DBDescription = resultSet.getString("Description");

                inventoryTableModel.insertRow(inventoryTableModel.getRowCount(), new Object[]{DBObject, DBQuantity, DBDescription});
            }
        }

    }

    public void AddObject() throws SQLException {
        AddTextToInventoryTable addTextToInventoryTable = new AddTextToInventoryTable();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Object = addTextToInventoryTable.ObjectLabel.getText();
                String Quantity = addTextToInventoryTable.QuantityLabel.getText();
                String Description = addTextToInventoryTable.DescriptionLabel.getText();

                switch (cmd) {
                    case "Add":
                        Statement statement = null;
                        try {
                            statement = DBOperations.establish_connection();
                            DBOperations.Add_Inventory(statement, "Inventory", id,
                                    Object, Quantity, Description);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        inventoryTableModel.insertRow(inventoryTableModel.getRowCount(), new Object[]{Object, Quantity, Description});
                        addTextToInventoryTable.Close();
                        break;
                    case "Delete":
                        addTextToInventoryTable.Close();
                }
            }
        };
        addTextToInventoryTable.AddButton.addActionListener(actionListener);
    }

    public void DeleteObject() throws SQLException {

        String Object;
        String Quantity;
        int index = 0;
        index = inventoryJTable.getSelectedRow();
        Object = (String) inventoryTableModel.getValueAt(index, 0);
        Quantity = (String) inventoryTableModel.getValueAt(index, 1);
        if (index != -1) {
            System.out.println(Object);
            System.out.println(Quantity);
            Statement statement = DBOperations.establish_connection();
            DBOperations.Delete_Inventory(statement, Object, Quantity);
            inventoryTableModel.removeRow(index);
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Add":
                try {
                    AddObject();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Delete":
                try {
                    DeleteObject();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Download":
                new DownloadTable(inventoryJTable);
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
