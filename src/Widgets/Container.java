package Widgets;

import Utils.Constants;
import javax.swing.*;
/**
 * Rappresenta un oggetto <code>Container</code>, ovvero un <code>JPanel</code> con un
 * <code>BoxLayout</code> verticale e un padding di 16.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class Container extends JPanel {

    /**
     * Metodo costruttore del <code>Container</code>.
     */
    public Container(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(Constants.empty16);
    }
}