package note;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.Map;


class NoteTree{
    Map<String, MetaNote> tree; // key: time
}
public class access {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();

            NoteTree noteTree = gson.fromJson(new FileReader("out/production/notebook/store/Notes.json"), NoteTree.class);

            // Iterate through all notes
            for (Map.Entry<String, MetaNote> entry : noteTree.tree.entrySet()) {
                String timestamp = entry.getKey();
                MetaNote note = entry.getValue();

                System.out.println("Timestamp: " + timestamp);
                System.out.println(note);
                System.out.println("-----");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
