package Utils;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public abstract class Constants {
    //Fonts
    public static final Font fontText15 = new Font("sans-serif", Font.PLAIN, 15);
    public static final Font fontLabel26 = new Font("sans-serif", Font.BOLD, 26);
    //Borders
    public static final EmptyBorder empty16 = new EmptyBorder(16, 16, 16, 16);
    public static final EmptyBorder emptyBottom5 = new EmptyBorder(0, 0, 5, 0);
    public static final EmptyBorder emptyBottom20 = new EmptyBorder(0, 0, 20, 0);
    public static final CompoundBorder compoundBottom5 = new CompoundBorder(emptyBottom5, null);
    public static final CompoundBorder compoundBottom20 = new CompoundBorder(emptyBottom20, null);
    public static final Insets top10bottom10 = new Insets(10, 0, 10, 0);

    //Colors
    public static final Color senapeColor = new Color(0xE5D224);

    //Icons
    public static final Icon homeIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\home.png");
    public static final Icon logoutIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\logout.png");
    public static final Icon leftArrowIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\back.png");
    public static final Icon coinsIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\coins.png");
    public static final Icon garageIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\garage.png");
    public static final Icon todolistIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\clipboard-list.png");
    public static final Icon ticketIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\ticket.png");
    public static final Icon usersIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\users.png");
    public static final Icon guestsIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\guest.png");
}
