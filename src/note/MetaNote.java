package note;

import java.time.*;
import javax.swing.JOptionPane;

public class MetaNote {
    protected String title;
    protected String date;

    public MetaNote(){
        title = null;
        date = null;
    }

    public String getDate() {
        return date;
    }
    public void setDate() {
        this.date = LocalDateTime.now().toString();
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(){
        this.title = JOptionPane.showInputDialog("Enter File Name Here: ");
    }
}
