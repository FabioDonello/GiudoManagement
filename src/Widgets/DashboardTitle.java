package Widgets;

import Utils.Constants;

import javax.swing.*;

public class DashboardTitle extends JLabel {

    public DashboardTitle(){
        super("Giudo - Dashboard");
        setIcon(Constants.homeIcon);
        setFont(Constants.fontLabel16);
        setBorder(Constants.emptyBottom20);
    }
}
