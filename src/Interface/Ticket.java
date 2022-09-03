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

import Utils.AddTextTicketsTable;
import Utils.DBOperations;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;


public class Ticket extends JFrame implements ActionListener, MouseListener {

    private final JTable Tickets_jTable;
    private static DefaultTableModel Tickets_TableModel = null;
    private static String id;
    private  JComboBox PriceComboBox;
    private  LabelTextField AddPriceLabel;
    private  LabelTextField NameLabel;
    private  LabelTextField TicketsLabel;
    private  LabelTextField ValueLabel;
    private  LabelTextField AddLabel;
    private  LabelTextField SubLabel;

    public Ticket(String ID) throws SQLException {

        super("GIUDO - Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Create

        Button add_button = new Button(this, "Add", "Add");
        Button delete_button = new Button(this, "Delete", "Del");

        Text SelectionPriceText = new Text("Select price ticket:");
        Text AddPriceText = new Text("Or add price ticket:");
        Text NameText = new Text("Name and surname:");
        Text TicketsText = new Text("Tickets:");
        Text Value = new Text("Value:");

        AddPriceLabel = new LabelTextField();
        PriceComboBox = new JComboBox();
        NameLabel = new LabelTextField();
        TicketsLabel = new LabelTextField();
        ValueLabel = new LabelTextField();
        AddLabel = new LabelTextField();
        SubLabel = new LabelTextField();

        Button add_price_tickets_button = new Button(this, "Add", "AddPrice");
        Button add_tickets_button = new Button(this, "Add tickets", "AddTickets");
        Button sub_tickets_button = new Button(this, "Sub tickets", "SubTickets");

        NameLabel.setEditable(false);
        TicketsLabel.setEditable(false);
        ValueLabel.setEditable(false);


        Tickets_TableModel = new DefaultTableModel(){
            /**
             * Make the cell ID not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };
        Tickets_TableModel.addColumn("Name & Surname");
        Tickets_TableModel.addColumn("Number of ticket");
        Tickets_TableModel.addColumn("Description");
        Tickets_jTable = new JTable(Tickets_TableModel);
        Tickets_jTable.setBounds(30, 40, 600, 300);
        Tickets_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Tickets_jTable));

        JScrollPane jScrollPane = new JScrollPane(Tickets_jTable);

        //Panel
        PannelloBorder ButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder TicketsPricePanel = new PannelloBorder(new GridLayout(1, 5));
        PannelloBorder ManagementTicketsPanel = new PannelloBorder(new GridLayout(1, 10));

        ButtonPanel.add(add_button,BorderLayout.WEST);
        ButtonPanel.add(delete_button,BorderLayout.EAST);

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


        TicketsPricePanel.add();
        TicketsPricePanel.add(PriceComboBox);
        TicketsPricePanel.add(AddPriceText);
        TicketsPricePanel.add(AddPriceLabel);
        TicketsPricePanel.add(add_price_tickets_button);

        //Container
        Container contentView = new Container();
        contentView.add(jScrollPane);
        contentView.add(ButtonPanel);
        contentView.add(TicketsPricePanel);


        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        id = ID;
        UpLoadData();

    }
    public void UpLoadData() throws SQLException {
        Statement statement = DBOperations.establish_connection();
        ResultSet Cost = DBOperations.TicketsUpLoad(statement,id);
        if (Cost!=null){
            while (Cost.next()) {
                String DBName = Cost.getString("NS");
                String DBTickets = Cost.getString("Tickets");
                String DBDescription = Cost.getString("Description");
                Tickets_TableModel.insertRow(Tickets_TableModel.getRowCount(),
                        new Object[] { DBName,DBTickets,DBDescription});
            }
        }
    }

    public void Add_Tickets() throws SQLException {

        AddTextTicketsTable l = new AddTextTicketsTable();
        ActionListener x = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Name = l.NameLabel.getText();
                String Tickets = l.TicketsLabel.getText();
                String Description = l.DescriptionLabel.getText();

                switch (cmd){
                    case "Add":
                        Statement statement = null;
                        try {

                            statement = DBOperations.establish_connection();
                            DBOperations.TicketsLoad(statement,id,Name,Tickets,Description);

                            Tickets_TableModel.insertRow(Tickets_TableModel.getRowCount(), new Object[]{
                                    Name,Tickets,Description});

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        l.Close();
                        break;
                    case "Del":
                        l.Close();
                        break;
                }
            }
        };
        l.Add_button.addActionListener(x);
    }
    public void Delete_Tickets() throws SQLException{
        int index = Tickets_jTable.getSelectedRow();
        System.out.println("ciao");
        if (index != -1){
            String Name = (String) Tickets_TableModel.getValueAt(index,0);
            Statement statement = DBOperations.establish_connection();
            DBOperations.TicketsDelete(statement, id, Name);
            Tickets_TableModel.removeRow(index);
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd){
            case "Add":
                try {
                    Add_Tickets();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Del":
                try {
                    Delete_Tickets();
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
