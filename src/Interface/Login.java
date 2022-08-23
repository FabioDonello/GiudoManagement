package Interface;


import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
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

public class Login extends JFrame implements ActionListener, MouseListener {


    private static LabelTextField email_field;
    private static PasswordTextField password_field;

    public Login() {
        super("Gestionale Eventi - Accedi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //Creo

        BasicArrowButton Arrow = new BasicArrowButton(BasicArrowButton.WEST);
        Arrow.setBackground(Color.red);
        Arrow.addActionListener(this);

        Text headerText = new Text("Accedi", Constants.fontLabel26);
        Text subText = new Text("Accedi utilizzando le credenziali utilizzate al momento della registrazione");

        Text email_text = new Text("Email:    ");
        email_field = new LabelTextField();
        email_field.setBorder(Constants.compoundBottom20);

        Text password_text = new Text("Password: ");
        password_field = new PasswordTextField();
        password_field.setBorder(Constants.compoundBottom20);

        Button loginbutton = new Button(this, "Login", "Login");

        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        subText.setBorder(Constants.compoundBottom20);

        email_text.setBorder(Constants.compoundBottom20);
        password_text.setBorder(Constants.compoundBottom20);

        //Pannelli
        PannelloBorder pannelloLogo = new PannelloBorder();
        PannelloBorder pannelloArrow = new PannelloBorder();
        PannelloBorder pannelloLogin = new PannelloBorder();
        PannelloBorder pannelloButtonAccedi = new PannelloBorder();

        pannelloArrow.add(Arrow,BorderLayout.WEST);

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pannelloButtonAccedi.add(loginbutton);
        pannelloButtonAccedi.setBorder(Constants.emptyBottom5);

        //Grid
        GrigliaBorder griglialogin = new GrigliaBorder();
        GridBagConstraints a = new GridBagConstraints();

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.1;
        a.weighty = 0.1;
        griglialogin.add(email_text, a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 1;
        a.weighty = 1;
        griglialogin.add(email_field, a);

        a.fill = GridBagConstraints.BASELINE;
        a.gridx = 0;
        a.gridy = 1;
        a.weightx = 0.1;
        a.weighty = 0.1;
        griglialogin.add(password_text, a);

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 1;
        a.gridy = 1;
        a.weightx = 1;
        a.weighty = 1;
        griglialogin.add(password_field, a);
        pannelloLogin.add(griglialogin);

        //Container
        Container contentView = new Container();
        contentView.add(pannelloArrow);
        contentView.add(pannelloLogo);
        contentView.add(pannelloLogin);
        contentView.add(pannelloButtonAccedi);


        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void LoginCheck() throws SQLException {
        Statement statement = DBOperations.establish_connection();
        String email = email_field.getText();
        String password = String.valueOf(password_field.getPassword());
        ResultSet ris = DBOperations.users_upload(statement);
        if (ris == null){
            System.out.println("Caricamento users non riuscito");
            dispose();
            new Welcome();
        }
        //Check e-mail and password after upload
        int x = 0;
        while (true) {
            assert ris != null;
            if (!ris.next()) break;
            String DBEmail = ris.getString("Email");
            String DBPassword = ris.getString("Password");
            if (DBEmail.compareTo(email)==0 && DBPassword.compareTo(password)==0){
                dispose();
                new PreMainPage();
                x=1;
            }
        }
        if (x==0){
            email_field.setText("");
            password_field.setText("");
            JOptionPane.showMessageDialog(null,
                    "Email or password d incorrect, please retry");

        }

}

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd) {
            case "Login":
                try {

                    LoginCheck();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "":
                dispose();
                new Welcome();
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
