package Widgets;
import javax.swing.*;
import java.awt.*;
/**
 * Rappresenta un <code>JPanel</code> con un <code>BorderLayout</code>.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class PannelloBorder extends JPanel {

    /**
     * Metodo costruttore.
     */
    public PannelloBorder(){
        super();
        setLayout(new BorderLayout());
    }
}