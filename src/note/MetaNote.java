package note;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class MetaNote {
    protected int id;
    protected String title;
    protected String date;

    public MetaNote(){
        title = null;
        date = LocalDate.now().toString();
        id = -1;
    }

    public String getTitle(){
        return title;
    }
    public int getId() {
        return id;
    }
    public void setId(int uniqueId) {
        this.id = uniqueId;
    }
    public void setTitle(){
        this.title = JOptionPane.showInputDialog("Enter File Name Here: ");
    }
}
