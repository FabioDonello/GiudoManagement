package Utils;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public abstract class Constants {
    //Fonts
    public static final Font fontText15 = new Font("sans-serif", Font.PLAIN, 15);
    public static final Font fontLabel13 = new Font("sans-serif", Font.BOLD, 13);
    public static final Font fontLabel16 = new Font("sans-serif", Font.BOLD, 16);
    public static final Font fontLabel26 = new Font("sans-serif", Font.BOLD, 26);
    //Borders
    public static final EmptyBorder empty10 = new EmptyBorder(10, 10, 10, 10);
    public static final EmptyBorder empty16 = new EmptyBorder(16, 16, 16, 16);
    public static final EmptyBorder emptyBottom5 = new EmptyBorder(0, 0, 5, 0);
    public static final EmptyBorder emptyBottom10 = new EmptyBorder(0, 0, 10, 0);
    public static final EmptyBorder emptyBottom20 = new EmptyBorder(0, 0, 20, 0);
    public static final CompoundBorder compoundBottom5 = new CompoundBorder(emptyBottom5, null);
    public static final CompoundBorder compoundBottom20 = new CompoundBorder(emptyBottom20, null);
    public static final Insets top10bottom10 = new Insets(10, 0, 10, 0);

    //Label dimensions
    public static final Dimension FieldDimensions200 = new Dimension(200, 100);
    public static final Dimension FieldDimensions100 = new Dimension(100, 100);

    //Colors
    public static final Color senapeColor = new Color(0xE5D224);
    public static final Color redAccent = new Color(0xffff4a4a);
    public static final Color greenAccent = new Color(0xff88dd7f);
    public static final Color bluElettrico = new Color(2172);

    //Icons
    public static final Icon homeIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\home.png");
    public static final Icon logoutIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\logout.png");
    public static final Icon leftarrowIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\back.png");
    public static final Icon coinsIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\coins.png");
    public static final Icon garageIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\garage.png");
    public static final Icon todolistIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\clipboard-list.png");
    public static final Icon ticketIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\ticket.png");
    public static final Icon usersIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\users.png");
    public static final Icon guestsIcon = new ImageIcon("C:\\Users\\39377\\Desktop\\SW\\GESTIONALE\\src\\icons\\guest.png");
}
