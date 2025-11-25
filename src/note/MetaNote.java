package note;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class MetaNote {
    protected String title;
    protected String date;

    public MetaNote(){
        title = JOptionPane.showInputDialog("Enter File Name Here: ");
        date = LocalDate.now().toString();
    }

    public String getTitle(){
        return title;
    }
}
