package Utils;

import com.github.lgooddatepicker.components.*;

import javax.swing.*;
import java.time.LocalDate;

public class DateTextField extends JPanel {
    public DatePicker datePicker;
    public DateTextField()
    {
        // Create a date picker, and add it to the form.
        datePicker = new DatePicker();
        add(datePicker);
    }
    public LocalDate GetData(){
        return datePicker.getDate();
    }
}

