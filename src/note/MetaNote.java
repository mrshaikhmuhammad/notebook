package note;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class MetaNote {
    protected String title;
    protected LocalDate date;

    public MetaNote(){
        title = JOptionPane.showInputDialog("Enter File Name Here: ");
        date = LocalDate.now();
    }

    public String getTitle(){
        return title;
    }
}
