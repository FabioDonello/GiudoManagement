package Interface;

import Utils.*;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ToDoList extends PannelloBorder implements ActionListener, MouseListener {

    private final JTable Done_jTable;
    private final JTable ToDo_jTable;
    private static DefaultTableModel Done_tableModel;
    private static DefaultTableModel ToDo_tableModel;
    private static String id;
    private static LabelTextField TotalDoneLabel;
    private static LabelTextField TotalToDoLabel;

    public ToDoList(JFrame parent, String ID) throws SQLException {

        //Create To do action Management
        Text ToDoText = new Text("To do actions:");
        ToDo_tableModel = new DefaultTableModel() {
            /**
             * Make the all cell not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        ToDo_tableModel.addColumn("Name");
        ToDo_tableModel.addColumn("Deadline");
        ToDo_tableModel.addColumn("Description");

        ToDo_jTable = new JTable(ToDo_tableModel);
        ToDo_jTable.setBounds(30, 40, 230, 280);
        ToDo_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(ToDo_jTable));
        JScrollPane ToDo_jScrollPane = new JScrollPane(ToDo_jTable);

        //Create done Management

        Text DoneText = new Text("Done actions:");
        Done_tableModel = new DefaultTableModel() {
            /**
             * Make the all cell not editable
             */
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        Done_tableModel.addColumn("Name");
        Done_tableModel.addColumn("Execution");
        Done_tableModel.addColumn("Description");

        Done_jTable = new JTable(Done_tableModel);
        Done_jTable.setBounds(30, 40, 230, 280);
        Done_jTable.getModel().addTableModelListener(new PreMainPage.MyTableModelListener(Done_jTable));
        JScrollPane Done_jScrollPane = new JScrollPane(Done_jTable);

        //Swap Button

        Button swap_button = new Button(this, "-->", "Swap");

        //ToDo Button

        Button add_To_Do_button = new Button(this, "+", "AddToDo");
        Button delete_To_Do_button = new Button(this, "-", "DelToDo");

        //Done Button

        Button add_Done_button = new Button(this, "+", "AddDone");
        Button delete_Done_button = new Button(this, "-", "DelDone");

        //Info

        Text TotalDoneText = new Text("Total done :");
        TotalDoneLabel = new LabelTextField();
        TotalDoneLabel.setPreferredSize(new Dimension(100, 20));
        TotalDoneLabel.setMinimumSize(new Dimension(100, 20));
        TotalDoneLabel.setMaximumSize(new Dimension(100, 20));
        TotalDoneLabel.setEditable(false);

        Text TotalToDoText = new Text("Total todo :");
        TotalToDoLabel = new LabelTextField();
        TotalToDoLabel.setPreferredSize(new Dimension(100, 20));
        TotalToDoLabel.setMinimumSize(new Dimension(100, 20));
        TotalToDoLabel.setMaximumSize(new Dimension(100, 20));
        TotalToDoLabel.setEditable(false);


        //Panel
        PannelloBorder TitleTablePanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder TablePanel = new PannelloBorder(new GridLayout(3, 3));
        PannelloBorder ButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder CostButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder RevButtonPanel = new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder InfoCostPanel = new PannelloBorder();
        PannelloBorder InfoRevPanel = new PannelloBorder();
        PannelloBorder InfoContPanel = new PannelloBorder();
        PannelloBorder SwapPanel = new PannelloBorder(new GridLayout(2, 1));

        TablePanel.add(ToDo_jScrollPane, BorderLayout.WEST);

        SwapPanel.add(swap_button, BorderLayout.CENTER);

        TablePanel.add(SwapPanel, BorderLayout.CENTER);

        TablePanel.add(Done_jScrollPane, BorderLayout.EAST);


        CostButtonPanel.add(add_To_Do_button, BorderLayout.WEST);
        CostButtonPanel.add(delete_To_Do_button, BorderLayout.CENTER);

        RevButtonPanel.add(add_Done_button, BorderLayout.WEST);
        RevButtonPanel.add(delete_Done_button, BorderLayout.CENTER);

        InfoRevPanel.add(TotalToDoText, BorderLayout.WEST);
        InfoRevPanel.add(TotalToDoLabel, BorderLayout.EAST);
        InfoCostPanel.add(TotalDoneText, BorderLayout.WEST);
        InfoCostPanel.add(TotalDoneLabel, BorderLayout.EAST);

        //Title Grid

        GrigliaBorder TitleGrid = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.5;
        a.weighty = 0.5;
        TitleGrid.add(ToDoText, a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 0.5;
        a.weighty = 0.5;
        TitleGrid.add(DoneText, a);
        TitleTablePanel.add(TitleGrid);

        //Title Grid

        GrigliaBorder ButtonGrid = new GrigliaBorder();
        GridBagConstraints b = new GridBagConstraints();

        b.fill = GridBagConstraints.BASELINE;
        b.gridx = 0;
        b.gridy = 0;
        b.weightx = 0.5;
        b.weighty = 0.5;
        ButtonGrid.add(CostButtonPanel, b);

        b.fill = GridBagConstraints.BASELINE;
        b.gridx = 1;
        b.gridy = 0;
        b.weightx = 0.5;
        b.weighty = 0.5;
        ButtonGrid.add(RevButtonPanel, b);
        ButtonPanel.add(ButtonGrid);

        //Container
        Widgets.Container contentView = new Container();
        contentView.add(TitleTablePanel);
        contentView.add(TablePanel);
        contentView.add(ButtonPanel);
        contentView.add(InfoCostPanel);
        contentView.add(InfoRevPanel);
        contentView.add(InfoContPanel);

        parent.add(contentView);
        parent.setSize(1100, 600);
        parent.setLocationRelativeTo(null);
        setVisible(true);

        id = ID;
        UpLoadData();
    }

    public void UpLoadData() throws SQLException {
        Statement statement = DBOperations.establish_connection();

        ResultSet DoneAction = DBOperations.TodoDoneUpLoad(statement, "DoneAction", id);

        if (DoneAction != null) {
            while (DoneAction.next()) {
                String DBName = DoneAction.getString("Name");
                String DBExecution = DoneAction.getString("Execution");
                String DBDescription = DoneAction.getString("Description");

                Done_tableModel.insertRow(Done_tableModel.getRowCount(), new Object[]{DBName, DBExecution, DBDescription});
            }
        }

        ResultSet ActionToDo = DBOperations.RevCostUpload(statement, "ToDoAction", id);

        if (DoneAction != null) {
            while (true) {
                assert ActionToDo != null;
                if (!ActionToDo.next()) break;
                String DBName = ActionToDo.getString("Name");
                String DBDeadline = ActionToDo.getString("Deadline");
                String DBDescription = ActionToDo.getString("Description");

                ToDo_tableModel.insertRow(ToDo_tableModel.getRowCount(), new Object[]{DBName, DBDeadline, DBDescription});
            }
        }

        RefreshInfoPanel();
    }

    public void Add_ToDo_Done(String s) throws SQLException {

        AddTextToDoTable l = new AddTextToDoTable(s);
        ActionListener x = e -> {
            String cmd = e.getActionCommand();
            String Name = l.NameLabel.getText();
            String Date = String.valueOf(l.DateTimeField.GetData());
            String Description = l.DescriptionLabel.getText();

            List<String> data=new LinkedList<>(
                    Arrays.asList(Name,Date,Description));

            switch (cmd) {
                case "Add":
                    if (!LabelCheck.isEmpty(data)){
                        try {
                            Statement statement = DBOperations.establish_connection();
                            DBOperations.TodoDoneLoad(statement,
                                    s, id, Name, Date, Description);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        if (s.compareTo("DoneAction") == 0) {
                            Done_tableModel.insertRow(Done_tableModel.getRowCount(), new Object[]{
                                    Name, Date, Description});
                        }

                        if (s.compareTo("ToDoAction") == 0) {
                            ToDo_tableModel.insertRow(ToDo_jTable.getRowCount(), new Object[]{
                                    Name, Date, Description});
                        }
                        RefreshInfoPanel();
                        l.Close();
                    }
                    else {
                        AddTextToDoTable.Error();
                    }
                    break;
                case "Del":
                    l.Close();
                    break;
            }
        };
        l.Add_button.addActionListener(x);
    }

    public void Delete_ToDo_Done(String s) throws SQLException {

        String Name;
        String Date;
        int index;
        if (s.compareTo("DoneAction") == 0) {
            index = Done_jTable.getSelectedRow();
            if (index != -1) {
                Name = (String) Done_tableModel.getValueAt(index, 0);
                Date = (String) Done_tableModel.getValueAt(index, 1);
                Statement statement = DBOperations.establish_connection();
                DBOperations.TodoDoneDelete(statement, s, Name, Date);
                Done_tableModel.removeRow(index);
            }
        }
        if (s.compareTo("ToDoAction") == 0) {
            index = ToDo_jTable.getSelectedRow();
            if (index != -1) {
                Name = (String) ToDo_tableModel.getValueAt(index, 0);
                Date = (String) ToDo_tableModel.getValueAt(index, 1);
                Statement statement = DBOperations.establish_connection();
                DBOperations.TodoDone_Delete(statement, s, Name, Date);
                ToDo_tableModel.removeRow(index);
            }
        }

        RefreshInfoPanel();
    }

    public void RefreshInfoPanel() {
        int TotalDoneActions = Done_jTable.getRowCount();
        int TotalToDoAction = ToDo_jTable.getRowCount();

        TotalDoneLabel.setText(String.valueOf(TotalDoneActions));
        TotalToDoLabel.setText(String.valueOf(TotalToDoAction));
    }

    public void SwapActions() throws SQLException {

        int index = ToDo_jTable.getSelectedRow();
        if (index != -1) {
            String Name = (String) ToDo_tableModel.getValueAt(index, 0);
            LocalDate now = LocalDate.now();
            String Date = String.valueOf(now);
            String Description = (String) ToDo_tableModel.getValueAt(index, 2);
            Delete_ToDo_Done("ToDoAction");

            Statement statement = DBOperations.establish_connection();
            DBOperations.TodoDoneLoad(statement,
                    "DoneAction", id, Name, Date, Description);
            Done_tableModel.insertRow(Done_tableModel.getRowCount(), new Object[]{
                    Name, Date, Description});
            RefreshInfoPanel();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd) {
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
