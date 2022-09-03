package Interface;

import Utils.Constants;
import Widgets.*;
import Widgets.Button;
import Widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MainPage extends JFrame implements ActionListener,MouseListener {

    public MainPage(String id){
        super("Giudo - Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(1800,1000);
        setVisible(true);


        Text headerText = new Text("HOME", Constants.fontLabel26);
        Button funz1=new Button(this,"funz1","cmd1");
        Button funz2=new Button(this,"funz2","cmd1");
        Button funz3=new Button(this,"funz3","cmd1");
        Button funz4=new Button(this,"funz4","cmd1");
        Button funz5=new Button(this,"funz5","cmd1");
        Button funz6=new Button(this,"funz6","cmd1");

        headerText.setHorizontalAlignment(SwingConstants.LEFT);
        headerText.setBorder(Constants.compoundBottom5);

        PannelloBorder pannellologo= new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloBtn1= new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloBtn2= new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloBtn3= new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloBtn4= new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloBtn5= new PannelloBorder(new GridLayout(3, 2));
        PannelloBorder pannelloBtn6= new PannelloBorder(new GridLayout(3, 2));

        pannellologo.add(headerText, BorderLayout.NORTH);

        pannelloBtn1.add(funz1);
        pannelloBtn1.setBorder(Constants.emptyBottom20);

        pannelloBtn2.add(funz2);
        pannelloBtn2.setBorder(Constants.emptyBottom20);

        pannelloBtn3.add(funz3);
        pannelloBtn3.setBorder(Constants.emptyBottom20);

        pannelloBtn4.add(funz4);
        pannelloBtn4.setBorder(Constants.emptyBottom20);

        pannelloBtn5.add(funz5);
        pannelloBtn5.setBorder(Constants.emptyBottom20);

        pannelloBtn6.add(funz6);
        pannelloBtn6.setBorder(Constants.emptyBottom20);

        Container contentView=new Container();
        contentView.add(pannellologo);
        contentView.add(pannelloBtn1);
        contentView.add(pannelloBtn2);
        contentView.add(pannelloBtn3);
        contentView.add(pannelloBtn4);
        contentView.add(pannelloBtn5);
        contentView.add(pannelloBtn6);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
