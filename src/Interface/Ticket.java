package Interface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import java.util.Objects;

import Utils.AddTextTicketsTable;
import Utils.DBOperations;
import Utils.LabelCheck;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;


public class Ticket extends PannelloBorder implements ActionListener, MouseListener {

    private final JTable Tickets_jTable;
    private static DefaultTableModel Tickets_TableModel = null;
    private static String id;
    private JComboBox<String> PriceComboBox;
    private JComboBox<String> AddComboBox;
    private JComboBox<String> SubComboBox;
    private LabelTextField AddPriceLabel;
    private LabelTextField NameLabel;
    private LabelTextField TicketsLabel;
    private LabelTextField ValueLabel;

    public Ticket(JFrame parent, String ID) throws SQLException {
        //Create

        Button add_button = new Button(this, "Add", "Add");
        Button delete_button = new Button(this, "Delete", "Del");

        Text SelectionPriceText = new Text("Select price ticket (€):");
        Text AddPriceText = new Text("Or add price ticket:");
        Text NameText = new Text("Name and surname:");
        Text TicketsText = new Text("Tickets:");
        Text ValueText = new Text("Value (€):");

        AddPriceLabel = new LabelTextField();
        NameLabel = new LabelTextField();
        TicketsLabel = new LabelTextField();
        ValueLabel = new LabelTextField();

        PriceComboBox = new JComboBox<String>();
        PriceComboBox.addItem("None");
        PriceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Tickets_jTable.getSelectedRow();
                if (index != -1) {
                    String InfoName = (String) Tickets_TableModel.getValueAt(index, 0);
                    String InfoTickets = (String) Tickets_TableModel.getValueAt(index, 1);
                    NameLabel.setText(InfoName);
                    TicketsLabel.setText(InfoTickets);

                    String TicketsPrice = (String) PriceComboBox.getSelectedItem();
                    assert TicketsPrice != null;
                    if (TicketsPrice.compareTo("None") == 0) {
                        ValueLabel.setText("None");
                    } else {
                        int PriceComboValue = Integer.parseInt((String) PriceComboBox.getSelectedItem());
                        int TicketsValue = Integer.parseInt(InfoTickets);
                        int value1 = PriceComboValue * TicketsValue;
                        String value = String.valueOf(value1);
                        ValueLabel.setText(value + "€");
                    }
                }

            }
        });

        AddComboBox = new JComboBox<String>();
        SubComboBox = new JComboBox<String>();
        for (int i = 0; i < 21; i++) {
            AddComboBox.addItem(String.valueOf(i));
            SubComboBox.addItem(String.valueOf(i));
        }

        Button add_price_tickets_button = new Button(this, "Add", "AddPrice");
        Button add_tickets_button = new Button(this, "Add tickets", "AddTickets");
        Button sub_tickets_button = new Button(this, "Sub tickets", "SubTickets");

        PriceComboBox.setPreferredSize(new Dimension(100, 20));
        PriceComboBox.setMinimumSize(new Dimension(100, 20));
        PriceComboBox.setMaximumSize(new Dimension(100, 20));

        AddPriceLabel.setPreferredSize(new Dimension(100, 20));
        AddPriceLabel.setMinimumSize(new Dimension(100, 20));
        AddPriceLabel.setMaximumSize(new Dimension(100, 20));

        NameLabel.setPreferredSize(new Dimension(100, 20));
        NameLabel.setMinimumSize(new Dimension(100, 20));
        NameLabel.setMaximumSize(new Dimension(100, 20));

        TicketsLabel.setPreferredSize(new Dimension(100, 20));
        TicketsLabel.setMinimumSize(new Dimension(100, 20));
        TicketsLabel.setMaximumSize(new Dimension(100, 20));

        ValueLabel.setPreferredSize(new Dimension(100, 20));
        ValueLabel.setMinimumSize(new Dimension(100, 20));
        ValueLabel.setMaximumSize(new Dimension(100, 20));

        AddComboBox.setPreferredSize(new Dimension(100, 20));
        AddComboBox.setMinimumSize(new Dimension(100, 20));
        AddComboBox.setMaximumSize(new Dimension(100, 20));

        SubComboBox.setPreferredSize(new Dimension(100, 20));
        SubComboBox.setMinimumSize(new Dimension(100, 20));
        SubComboBox.setMaximumSize(new Dimension(100, 20));

        NameLabel.setEditable(false);
        TicketsLabel.setEditable(false);
        ValueLabel.setEditable(false);


        Tickets_TableModel = new DefaultTableModel() {
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
        Tickets_jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = Tickets_jTable.getSelectedRow();
                if (index != -1) {
                    String InfoName = (String) Tickets_TableModel.getValueAt(index, 0);
                    String InfoTickets = (String) Tickets_TableModel.getValueAt(index, 1);
                    NameLabel.setText(InfoName);
                    TicketsLabel.setText(InfoTickets);

                    String TicketsPrice = (String) PriceComboBox.getSelectedItem();
                    assert TicketsPrice != null;
                    if (TicketsPrice.compareTo("None") == 0) {
                        ValueLabel.setText("None");
                    } else {
                        int PriceComboValue = Integer.parseInt((String) PriceComboBox.getSelectedItem());
                        int TicketsValue = Integer.parseInt(InfoTickets);
                        int value1 = PriceComboValue * TicketsValue;
                        String value = String.valueOf(value1);
                        ValueLabel.setText(value + "€");
                    }
                }
            }
        });

        JScrollPane jScrollPane = new JScrollPane(Tickets_jTable);

        //Panel
        PannelloBorder ButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder TicketsPricePanel = new PannelloBorder(new GridLayout(1, 5));
        PannelloBorder InfoPanel1 = new PannelloBorder();
        PannelloBorder InfoPanel2 = new PannelloBorder();

        ButtonPanel.add(add_button, BorderLayout.WEST);
        ButtonPanel.add(delete_button, BorderLayout.EAST);

        GrigliaBorder InfoGrid1 = new GrigliaBorder();
        GridBagConstraints a1 = new GridBagConstraints();

        a1.fill = GridBagConstraints.WEST;
        a1.gridx = 0;
        a1.gridy = 0;
        InfoGrid1.add(SelectionPriceText, a1);

        a1.fill = GridBagConstraints.HORIZONTAL;
        a1.gridx = 1;
        a1.gridy = 0;
        InfoGrid1.add(PriceComboBox, a1);

        a1.fill = GridBagConstraints.WEST;
        a1.gridx = 2;
        a1.gridy = 0;
        InfoGrid1.add(AddPriceText, a1);

        a1.fill = GridBagConstraints.HORIZONTAL;
        a1.gridx = 3;
        a1.gridy = 0;
        InfoGrid1.add(AddPriceLabel, a1);

        a1.fill = GridBagConstraints.EAST;
        a1.gridx = 4;
        a1.gridy = 0;
        a1.weightx = 0.3;
        a1.weighty = 0.3;
        InfoGrid1.add(add_price_tickets_button, a1);
        InfoPanel1.add(InfoGrid1);

        GrigliaBorder InfoGrid2 = new GrigliaBorder();
        GridBagConstraints a2 = new GridBagConstraints();

        a2.fill = GridBagConstraints.WEST;
        a2.gridx = 0;
        a2.gridy = 0;
        InfoGrid2.add(NameText, a2);

        a2.fill = GridBagConstraints.HORIZONTAL;
        a2.gridx = 1;
        a2.gridy = 0;
        InfoGrid2.add(NameLabel, a2);

        a2.fill = GridBagConstraints.WEST;
        a2.gridx = 2;
        a2.gridy = 0;
        InfoGrid2.add(TicketsText, a2);

        a2.fill = GridBagConstraints.HORIZONTAL;
        a2.gridx = 3;
        a2.gridy = 0;
        InfoGrid2.add(TicketsLabel, a2);

        a2.fill = GridBagConstraints.EAST;
        a2.gridx = 4;
        a2.gridy = 0;
        InfoGrid2.add(ValueText, a2);

        a2.fill = GridBagConstraints.HORIZONTAL;
        a2.gridx = 5;
        a2.gridy = 0;
        InfoGrid2.add(ValueLabel, a2);

        a2.fill = GridBagConstraints.EAST;
        a2.gridx = 6;
        a2.gridy = 0;
        InfoGrid2.add(add_tickets_button, a2);

        a2.fill = GridBagConstraints.HORIZONTAL;
        a2.gridx = 7;
        a2.gridy = 0;
        InfoGrid2.add(AddComboBox, a2);

        a2.fill = GridBagConstraints.EAST;
        a2.gridx = 8;
        a2.gridy = 0;
        InfoGrid2.add(sub_tickets_button, a2);

        a2.fill = GridBagConstraints.HORIZONTAL;
        a2.gridx = 9;
        a2.gridy = 0;
        InfoGrid2.add(SubComboBox, a2);
        InfoPanel2.add(InfoGrid2);

        //Container
        Container contentView = new Container();
        contentView.add(jScrollPane);
        contentView.add(ButtonPanel);
        contentView.add(InfoPanel1);
        contentView.add(InfoPanel2);
        contentView.add(TicketsPricePanel);

        parent.add(contentView);
        parent.setSize(1100, 600);
        parent.setLocationRelativeTo(null);
        setVisible(true);

        id = ID;
        UpLoadData();
    }

    public void UpLoadData() throws SQLException {
        Statement statement = DBOperations.establish_connection();
        ResultSet Tickets = DBOperations.TicketsUpLoad(statement, id);
        if (Tickets != null) {
            while (Tickets.next()) {
                String DBName = Tickets.getString("NS");
                String DBTickets = Tickets.getString("Tickets");
                String DBDescription = Tickets.getString("Description");
                Tickets_TableModel.insertRow(Tickets_TableModel.getRowCount(),
                        new Object[]{DBName, DBTickets, DBDescription});
            }
        }

        ResultSet Price = DBOperations.TicketsPriceUpLoad(statement, id);
        if (Price != null) {
            while (Price.next()) {
                String DBPrice = Price.getString("Price");
                PriceComboBox.addItem(DBPrice);
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
                List<String> data = new LinkedList<String>(
                        Arrays.asList(Name, Tickets, Description));

                switch (cmd) {
                    case "Add":
                        Statement statement = null;
                        try {
                            if (LabelCheck.isEmpty(data)) {
                                JOptionPane.showMessageDialog(null, "Attention, you must fill in all fields correctly!",
                                        "Warning", JOptionPane.WARNING_MESSAGE);
                                l.dispose();
                            } else {
                                statement = DBOperations.establish_connection();
                                DBOperations.TicketsLoad(statement, id, Name, Tickets, Description);

                                Tickets_TableModel.insertRow(Tickets_TableModel.getRowCount(), new Object[]{
                                        Name, Tickets, Description});
                                l.Close();
                            }
                            break;

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    case "Del":
                        l.Close();
                        break;
                }
            }
        };
        l.Add_button.addActionListener(x);
    }

    public void Delete_Tickets() throws SQLException {
        int index = Tickets_jTable.getSelectedRow();
        System.out.println("ciao");
        if (index != -1) {
            String Name = (String) Tickets_TableModel.getValueAt(index, 0);
            Statement statement = DBOperations.establish_connection();
            DBOperations.TicketsDelete(statement, id, Name);
            Tickets_TableModel.removeRow(index);
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }

    public void Add_Tickets_Price() throws SQLException {
        String Price = AddPriceLabel.getText();
        if (Price.compareTo("") != 0) {
            Statement statement = DBOperations.establish_connection();
            DBOperations.TicketsPriceLoad(statement, id, Price);
            PriceComboBox.addItem(Price);
        }
    }

    public void Refresh_Tickets(String s) throws SQLException {
        int index = Tickets_jTable.getSelectedRow();
        if (index != -1) {
            String NS = (String) Tickets_TableModel.getValueAt(index, 0);
            String Tickets = (String) Tickets_TableModel.getValueAt(index, 1);
            String Description = (String) Tickets_TableModel.getValueAt(index, 2);

            int TicketsValue = Integer.parseInt(Tickets);
            int TicketsRefreshValue;
            if (s.compareTo("+") == 0) {
                TicketsRefreshValue = Integer.parseInt((String) Objects.requireNonNull(AddComboBox.getSelectedItem()));
                TicketsValue += TicketsRefreshValue;
            } else if (s.compareTo("-") == 0) {
                TicketsRefreshValue = Integer.parseInt((String) Objects.requireNonNull(SubComboBox.getSelectedItem()));
                TicketsValue -= TicketsRefreshValue;
            }

            Statement statement = DBOperations.establish_connection();
            DBOperations.TicketsDelete(statement, id, NS);
            DBOperations.TicketsLoad(statement, id, NS, String.valueOf(TicketsValue), Description);

            Tickets_TableModel.removeRow(index);
            Tickets_TableModel.insertRow(Tickets_TableModel.getRowCount(), new Object[]{
                    NS, String.valueOf(TicketsValue), Description});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd) {
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
            case "AddPrice":
                try {
                    Add_Tickets_Price();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "AddTickets":
                try {
                    Refresh_Tickets("+");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "SubTickets":
                try {
                    Refresh_Tickets("-");
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
