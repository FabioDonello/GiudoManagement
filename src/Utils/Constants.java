package Utils;

import java.awt.*;
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
    public static final EmptyBorder empty10 = new EmptyBorder(10,10,10,10);
    public static final EmptyBorder empty16 = new EmptyBorder(16,16,16,16);
    public static final EmptyBorder emptyBottom5 = new EmptyBorder(0,0,5,0);
    public static final EmptyBorder emptyBottom10 = new EmptyBorder(0,0,10,0);
    public static final EmptyBorder emptyBottom20 = new EmptyBorder(0,0,20,0);
    public static final CompoundBorder compoundBottom5 = new CompoundBorder(emptyBottom5, null);
    public static final CompoundBorder compoundBottom20 = new CompoundBorder(emptyBottom20, null);
    public static final Insets top10bottom10 = new Insets(10,0,10,0);
    //Colors
    public static final Color senapeColor = new Color(0xE5D224);
    public static final Color redAccent = new Color(0xffff4a4a);
    public static final Color greenAccent = new Color(0xff88dd7f);}
