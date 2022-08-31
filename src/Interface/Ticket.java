package Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;

import Utils.AddTextToDoTable;
import Utils.DBOperations;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;


public class Ticket extends JFrame implements ActionListener, MouseListener {

    private final JTable Tickets_jTable;
    private static DefaultTableModel Tickets_TableModel = null;

    private static String id;
    public Ticket(String ID) {

        super("GIUDO - Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Create

        Button add_button = new Button(this, "Add", "Add");
        Button modify_button = new Button(this, "Modify existing", "Modify");
        Button delete_button = new Button(this, "Delete", "Delete");


        Tickets_TableModel = new DefaultTableModel(){
            /**
             * Make the cell ID not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };
        Tickets_TableModel.addColumn("ID");
        Tickets_TableModel.addColumn("Owner");
        Tickets_TableModel.addColumn("Number of ticket");
        Tickets_TableModel.addColumn("Number of ticket sold");
        Tickets_TableModel.addColumn("Remnant");
        Tickets_TableModel.addColumn("Description");
        Tickets_jTable = new JTable(Tickets_TableModel);
        Tickets_jTable.setBounds(30, 40, 600, 300);
        Tickets_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Tickets_jTable));

        JScrollPane jScrollPane = new JScrollPane(Tickets_jTable);

        //Panel
        PannelloBorder LogoPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder ButtonPanel = new PannelloBorder(new GridLayout(3, 2));

        ButtonPanel.add(add_button,BorderLayout.WEST);
        ButtonPanel.add(modify_button,BorderLayout.CENTER);
        ButtonPanel.add(delete_button,BorderLayout.EAST);

        //Container
        Container contentView = new Container();
        contentView.add(LogoPanel);
        contentView.add(jScrollPane);
        contentView.add(ButtonPanel);


        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        id = ID;


    }

    public void Add_ToDo_Done(String s) throws SQLException {

        AddTextToDoTable l = new AddTextToDoTable(s);
        ActionListener x = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String Name = l.NameLabel.getText();
                String Date = String.valueOf(l.DateTimeField.GetData());
                String Description = l.DescriptionLabel.getText();

                switch (cmd){
                    case "Add":
                        Statement statement = null;
                        try {
                            statement = DBOperations.establish_connection();
                            DBOperations.TodoDoneLoad(statement,
                                    s,id,Name,Date,Description);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        if (s.compareTo("DoneAction")==0) {
                            Done_tableModel.insertRow(Done_tableModel.getRowCount(), new Object[]{
                                    Name,Date,Description});
                        }

                        if (s.compareTo("ToDoAction")==0) {
                            ToDo_tableModel.insertRow(ToDo_jTable.getRowCount(), new Object[]{
                                    Name,Date,Description});
                        }
                        RefreshInfoPanel();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd){
            case "AddDone":
                try {
                    Add_ToDo_Done("DoneAction");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "DelDone":
                try {
                    Delete_ToDo_Done("DoneAction");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "AddToDo":
                try {
                    Add_ToDo_Done("ToDoAction");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "DelToDo":
                try {
                    Delete_ToDo_Done("ToDoAction");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Swap":
                try {
                    SwapActions();
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
