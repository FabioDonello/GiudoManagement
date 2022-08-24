package Interface;

import Utils.AddTextToTable;
import Utils.DBOperations;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;

public class MoneyFlow extends JFrame implements ActionListener, MouseListener {

    private final JTable Cost_jTable;
    private final JTable Revenues_jTable;
    private static DefaultTableModel Cost_tableModel = null;
    private static DefaultTableModel Revenues_tableModel = null;

    public MoneyFlow(String ID) throws SQLException{
        super("GIUDO - Money Flow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Create Cost Management

        Text CostText = new Text("COST");
        Cost_tableModel = new DefaultTableModel();

        Cost_tableModel.addColumn("Cost Amount");
        Cost_tableModel.addColumn("Name");
        Cost_tableModel.addColumn("Description");

        Cost_jTable = new JTable(Cost_tableModel);
        Cost_jTable.setBounds(30, 40, 230, 280);
        Cost_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Cost_jTable));
        JScrollPane Cost_jScrollPane = new JScrollPane(Cost_jTable);

        Button add_cost_button = new Button(this, "+", "AddCost");
        Button delete_cost_button = new Button(this, "-", "DelCost");


        //Create Revenues Management
        Text RevenuesText = new Text("REVENUES");
        Revenues_tableModel = new DefaultTableModel();

        Revenues_tableModel.addColumn("Revenues Amount");
        Revenues_tableModel.addColumn("Name");
        Revenues_tableModel.addColumn("Description");

        Revenues_jTable = new JTable(Revenues_tableModel);
        Revenues_jTable.setBounds(30, 40, 230, 280);
        Revenues_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Revenues_jTable));
        JScrollPane Revenues_jScrollPane = new JScrollPane(Revenues_jTable);

        Button add_Rev_button = new Button(this, "+", "AddRev");
        Button delete_Rev_button = new Button(this, "-", "DelRev");


        //Panel
        PannelloBorder TitleTablePanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder TablePanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder ButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder CostButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder RevButtonPanel = new PannelloBorder(new GridLayout(3, 2));

        TablePanel.add(Cost_jScrollPane,BorderLayout.WEST);
        TablePanel.add(Revenues_jScrollPane,BorderLayout.EAST);

        CostButtonPanel.add(add_cost_button,BorderLayout.WEST);
        CostButtonPanel.add(delete_cost_button,BorderLayout.CENTER);

        RevButtonPanel.add(add_Rev_button,BorderLayout.WEST);
        RevButtonPanel.add(delete_Rev_button,BorderLayout.CENTER);

        //Title Grid

        GrigliaBorder TitleGrid = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.5;
        a.weighty = 0.5;
        TitleGrid.add(CostText, a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 0.5;
        a.weighty = 0.5;
        TitleGrid.add(RevenuesText, a);
        TitleTablePanel.add(TitleGrid);

        //Title Grid

        GrigliaBorder ButtonGrid = new GrigliaBorder();
        GridBagConstraints b = new GridBagConstraints();

        b.fill = GridBagConstraints.BASELINE;
        b.gridx = 0;
        b.gridy = 0;
        b.weightx = 0.5;
        b.weighty = 0.5;
        ButtonGrid.add(CostButtonPanel,b);

        b.fill = GridBagConstraints.BASELINE;
        b.gridx = 1;
        b.gridy = 0;
        b.weightx = 0.5;
        b.weighty = 0.5;
        ButtonGrid.add(RevButtonPanel,b);
        ButtonPanel.add(ButtonGrid);

        //Container
        Widgets.Container contentView = new Container();
        contentView.add(TitleTablePanel);
        contentView.add(TablePanel);
        contentView.add(ButtonPanel);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void AddCostRev(String s) throws SQLException {
        AddTextToTable l = new AddTextToTable();
        Statement statement = DBOperations.establish_connection();
        ActionListener x = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Amount = l.ValueLabel.getText();
                String Name = l.NameLabel.getText();
                String Description = l.DescriptionLabel.getText();

                switch (cmd){
                    case "Add":
                        if (s.compareTo("Cost")==0){
                            try {
                                DBOperations.AddRevCost_Upload(statement,
                                        "Cost",Amount,Name,Description);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (s.compareTo("Cost")==0){
                            try {
                                DBOperations.AddRevCostUpload(statement,
                                        "Revenues",Amount,Name,Description);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        break;
                    case "Del":
                        l.dispose();
                        break;
                }
            }
        };
        l.Add_button.addActionListener(x);




    }

    public void DeleteCost() throws SQLException {


    }

    public void DeleteRev() throws SQLException {


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd){
            case "AddCost":
                try {
                    AddCostRev("Cost");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "DelCost":
                try {
                    DeleteCost();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "AddRev":
                try {
                    AddCostRev("Rev");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "DelRev":
                try {
                    DeleteRev();
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
