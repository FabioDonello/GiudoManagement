package Interface;

import Utils.AddTextToMoneyTable;
import Utils.DBOperations;
import Utils.LabelCheck;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MoneyFlow extends PannelloBorder implements ActionListener, MouseListener {

    JTable Cost_jTable;
    JTable Revenues_jTable;
    DefaultTableModel Cost_tableModel;
    DefaultTableModel Revenues_tableModel;
    String id;
    LabelTextField TotalCostLabel;
    LabelTextField TotalRevLabel;
    LabelTextField TotalContLabel;

    public MoneyFlow(JFrame parent,String ID) throws SQLException{
        //Create Cost Management

        Text CostText = new Text("COST");
        Cost_tableModel = new DefaultTableModel(){
            /**
             * Make the all cell not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        Cost_tableModel.addColumn("Cost Amount");
        Cost_tableModel.addColumn("Name");
        Cost_tableModel.addColumn("Description");

        Cost_jTable = new JTable(Cost_tableModel);
        Cost_jTable.setBounds(30, 40, 230, 280);
        Cost_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Cost_jTable));
        JScrollPane Cost_jScrollPane = new JScrollPane(Cost_jTable);

        //Create Revenues Management
        Text RevenuesText = new Text("REVENUES");
        Revenues_tableModel = new DefaultTableModel(){
            /**
             * Make the all cell not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        Revenues_tableModel.addColumn("Revenues Amount");
        Revenues_tableModel.addColumn("Name");
        Revenues_tableModel.addColumn("Description");

        Revenues_jTable = new JTable(Revenues_tableModel);
        Revenues_jTable.setBounds(30, 40, 230, 280);
        Revenues_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Revenues_jTable));
        JScrollPane Revenues_jScrollPane = new JScrollPane(Revenues_jTable);
        //Button

        Button add_Rev_button = new Button(this, "+", "AddRev");
        Button delete_Rev_button = new Button(this, "-", "DelRev");

        Button add_cost_button = new Button(this, "+", "AddCost");
        Button delete_cost_button = new Button(this, "-", "DelCost");

        //Info

        Text TotalCostText = new Text("Total cost:");
        TotalCostLabel = new LabelTextField();
        TotalCostLabel.setPreferredSize(new Dimension(100,20));
        TotalCostLabel.setMinimumSize(new Dimension(100,20));
        TotalCostLabel.setMaximumSize(new Dimension(100,20));
        TotalCostLabel.setEditable(false);

        Text TotalRevText = new Text("Total revenues:");
        TotalRevLabel = new LabelTextField();
        TotalRevLabel.setPreferredSize(new Dimension(100,20));
        TotalRevLabel.setMinimumSize(new Dimension(100,20));
        TotalRevLabel.setMaximumSize(new Dimension(100,20));
        TotalRevLabel.setEditable(false);

        Text TotalContText = new Text("Total cont:");
        TotalContLabel = new LabelTextField();
        TotalContLabel.setPreferredSize(new Dimension(100,20));
        TotalContLabel.setMinimumSize(new Dimension(100,20));
        TotalContLabel.setMaximumSize(new Dimension(100,20));
        TotalContLabel.setEditable(false);


        //Panel
        PannelloBorder TitleTablePanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder TablePanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder ButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder CostButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder RevButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder InfoCostPanel = new PannelloBorder();
        PannelloBorder InfoRevPanel = new PannelloBorder();
        PannelloBorder InfoContPanel = new PannelloBorder();

        TablePanel.add(Cost_jScrollPane,BorderLayout.WEST);
        TablePanel.add(Revenues_jScrollPane,BorderLayout.EAST);

        CostButtonPanel.add(add_cost_button,BorderLayout.WEST);
        CostButtonPanel.add(delete_cost_button,BorderLayout.CENTER);

        RevButtonPanel.add(add_Rev_button,BorderLayout.WEST);
        RevButtonPanel.add(delete_Rev_button,BorderLayout.CENTER);

        InfoCostPanel.add(TotalCostText,BorderLayout.WEST);
        InfoCostPanel.add(TotalCostLabel,BorderLayout.EAST);
        InfoRevPanel.add(TotalRevText,BorderLayout.WEST);
        InfoRevPanel.add(TotalRevLabel,BorderLayout.EAST);
        InfoContPanel.add(TotalContText,BorderLayout.WEST);
        InfoContPanel.add(TotalContLabel,BorderLayout.EAST);




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
        contentView.add(InfoCostPanel);
        contentView.add(InfoRevPanel);
        contentView.add(InfoContPanel);

        parent.add(contentView,BorderLayout.CENTER);
        parent.setSize(1200,600);
        setVisible(true);


        id = ID;
        UpLoadData();
    }
    public void UpLoadData() throws SQLException {
        Statement statement = DBOperations.establish_connection();

        ResultSet Cost = DBOperations.RevCost_Upload(statement,"Cost",id);

        if (Cost!=null){
            while (Cost.next()) {
                String DBAmount = Cost.getString("Amount");
                String DBName = Cost.getString("Name");
                String DBDescription = Cost.getString("Description");

                Cost_tableModel.insertRow(Cost_tableModel.getRowCount(), new Object[] { DBAmount,DBName,DBDescription});
            }
        }

        ResultSet Revenues = DBOperations.RevCostUpload(statement,"Revenues",id);

        if (Cost!=null){
            while (Revenues.next()) {
                String DBAmount = Revenues.getString("Amount");
                String DBName = Revenues.getString("Name");
                String DBDescription = Revenues.getString("Description");

                Revenues_tableModel.insertRow(Revenues_tableModel.getRowCount(), new Object[] { DBAmount,DBName,DBDescription});
            }
        }

        RefreshInfoPanel();
    }
    public void AddCostRev(String s) throws SQLException {

        AddTextToMoneyTable l = new AddTextToMoneyTable();
        ActionListener x = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Amount = l.ValueLabel.getText();
                String Name = l.NameLabel.getText();
                String Description = l.DescriptionLabel.getText();

                List<String> data=new LinkedList<String>(
                        Arrays.asList(Amount,Name,Description));

                switch (cmd){
                    case "Add":
                        if (LabelCheck.isEmpty(data)){
                            Statement statement = null;
                            try {
                                statement = DBOperations.establish_connection();
                                DBOperations.AddRevCostLoad(statement,
                                        s,id,Amount+"€",Name,Description);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            if (s.compareTo("Cost")==0) {
                                Cost_tableModel.insertRow(Cost_tableModel.getRowCount(), new Object[]{
                                        Amount+"€",Name,Description});
                                RefreshInfoPanel();
                            }

                            if (s.compareTo("Revenues")==0) {
                                Revenues_tableModel.insertRow(Revenues_jTable.getRowCount(), new Object[]{
                                        Amount+"€",Name,Description});
                                RefreshInfoPanel();
                            }
                            l.Close();
                            break;                            
                        }
                        else {
                            AddTextToMoneyTable.Error();
                        }
                    case "Del":
                        l.Close();
                        break;
                }
            }
        };
        l.Add_button.addActionListener(x);
    }

    public void DeleteRevCost(String s) throws SQLException {

        String Amount ;
        String Name ;
        int index = 0;
        if (s.compareTo("Cost")==0){
            index = Cost_jTable.getSelectedRow();
            Amount = (String) Cost_tableModel.getValueAt(index,0);
            Name = (String) Cost_tableModel.getValueAt(index,1);
            if (index != -1){
                System.out.println(Amount);
                System.out.println(Name);
                Statement statement = DBOperations.establish_connection();
                DBOperations.RevCostDelete(statement,s,Amount,Name);
                Cost_tableModel.removeRow(index);
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        }
        if (s.compareTo("Revenues")==0){
            index = Revenues_jTable.getSelectedRow();
            Amount = (String) Revenues_tableModel.getValueAt(index,0);
            Name = (String) Revenues_tableModel.getValueAt(index,1);
            if (index != -1){
                System.out.println(Amount);
                System.out.println(Name);
                Statement statement = DBOperations.establish_connection();
                DBOperations.RevCostDelete(statement,s,Amount,Name);
                Revenues_tableModel.removeRow(index);
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        }

        RefreshInfoPanel();
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
                    DeleteRevCost("Cost");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "AddRev":
                try {
                    AddCostRev("Revenues");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "DelRev":
                try {
                    DeleteRevCost("Revenues");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            default:
                break;
        }
    }

    public void RefreshInfoPanel() {
        double TotalCost = 0;
        double TotalRev = 0;
        double TotalCont = 0;
        int i=0;


        while (i<Cost_tableModel.getRowCount()){
            String Cost_Str = (String) Cost_tableModel.getValueAt(i,0);
            Cost_Str = Cost_Str.replace("€","");
            TotalCost+=Double.parseDouble(Cost_Str);
            i++;
        }
        i=0;
        while (i<Revenues_tableModel.getRowCount()){
            String Rev_Str = (String) Revenues_tableModel.getValueAt(i,0);
            Rev_Str = Rev_Str.replace("€","");
            TotalRev+=Double.parseDouble(Rev_Str);
            i++;
        }

        TotalCont = TotalRev-TotalCost;

        TotalCostLabel.setText(String.valueOf(TotalCost));
        TotalRevLabel.setText(String.valueOf(TotalRev));
        TotalContLabel.setText(String.valueOf(TotalCont));
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
