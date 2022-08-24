package Interface;

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
import Utils.Constants;
import Utils.DBOperations;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.Random;
import javax.swing.table.TableModel;

public class PreMainPage extends JFrame implements ActionListener, MouseListener {

    private final JTable jTable;
    private static DefaultTableModel tableModel = null;
    public PreMainPage() throws SQLException {

        super("GIUDO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Create

        Text headerText = new Text("Giudo", Constants.fontLabel26);
        Text subText = new Text("Seleziona un progetto esistente o creane uno nuovo:");

        Button open_project_button = new Button(this, "Open project", "Open");
        Button create_table_button = new Button(this, "Create project", "Create");
        Button delete_table_button = new Button(this, "Delete project", "Delete");


        tableModel = new DefaultTableModel(){
            /**
             * Make the cell ID not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Description");
        jTable = new JTable(tableModel);
        jTable.setBounds(30, 40, 230, 280);
        jTable.getModel().addTableModelListener(new MyTableModelListener(jTable));

        JScrollPane jScrollPane = new JScrollPane(jTable);

        //Panel
        PannelloBorder LogoPanel = new PannelloBorder();
        PannelloBorder ButtonPanel = new PannelloBorder();

        LogoPanel.add(headerText, BorderLayout.NORTH);
        LogoPanel.add(subText, BorderLayout.SOUTH);

        ButtonPanel.add(open_project_button,BorderLayout.WEST);
        ButtonPanel.add(create_table_button,BorderLayout.CENTER);
        ButtonPanel.add(delete_table_button,BorderLayout.EAST);

        //Container
        Container contentView = new Container();
        contentView.add(LogoPanel);
        contentView.add(jScrollPane);
        contentView.add(ButtonPanel);
        contentView.setSize(600, 300);


        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        UpLoadData();

    }


    /**
     * Upload all project from the database when the table is build
     */
    public void UpLoadData() throws SQLException {
        Statement statement = DBOperations.establish_connection();
        ResultSet ris = DBOperations.projects_Upload(statement);
        if (ris!=null){
            while (ris.next()) {
                String DBId = ris.getString("ID");
                String DBName = ris.getString("Name");
                String DBDescription = ris.getString("Description");

                tableModel.insertRow(tableModel.getRowCount(), new Object[] { DBId,DBName,DBDescription});
            }
        }
    }


    /**
     * Create a new project in the table
     * After the project was created with default option they add to database
     */
    public void CreateProject() throws SQLException {
        int Row = tableModel.getRowCount();
        String ID = String.valueOf((int) Math.floor(Math.random()*(19999-10000+1)+10000));
        tableModel.insertRow(tableModel.getRowCount(), new Object[] { ID,"Project "+String.valueOf(Row),
                "This is project "+String.valueOf(Row) });

        Statement statement = DBOperations.establish_connection();
        DBOperations.projectLoad(statement,ID,"Project "+String.valueOf(Row),
                "This is project "+String.valueOf(Row));
    }

    /**
     * Delete a selected project in the table
     * After the project was deleted they also delete in the database
     */
    public void DeleteProject() throws SQLException {
        int index = jTable.getSelectedRow();
        if (index != -1){
            Statement statement = DBOperations.establish_connection();
            String id = (String) tableModel.getValueAt(index,0);
            DBOperations.projectDelete(statement,id);
            tableModel.removeRow(index);
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }

    public void OpenProject(){
        int index = jTable.getSelectedRow();
        if (index!=-1){
            String id = (String) tableModel.getValueAt(index,0);
            dispose();
            new Dashboard();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "Create":
                try {
                    CreateProject();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            case "Delete":
                try {
                    DeleteProject();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            case "Open":
                OpenProject();
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

    static class MyTableModelListener implements TableModelListener {
        JTable table;

        MyTableModelListener(JTable table) {
            this.table = table;
        }

        public void tableChanged(TableModelEvent e) {
            int firstRow = e.getFirstRow();
            int lastRow = e.getLastRow();
            int index = e.getColumn();

            switch (e.getType()) {
                case TableModelEvent.UPDATE:
                    try {
                        Statement statement = DBOperations.establish_connection();
                        String id = (String) tableModel.getValueAt(firstRow,0);
                        String name = (String) tableModel.getValueAt(firstRow,1);
                        String description = (String) tableModel.getValueAt(firstRow,2);
                        DBOperations.projectRefresh(statement, id, name, description);
                        break;
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                case TableModelEvent.DELETE:

                    break;
            }
        }
    }
}
