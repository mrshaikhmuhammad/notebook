package note;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.util.TreeMap;

public class NoteCollection {

    private final TreeMap<String, Note> tree = new TreeMap<>();

    public NoteCollection() {}

    public static NoteCollection load() {
        try {
            String path = System.getProperty("user.dir") + "/src/store/Notes.json";
            FileReader reader = new FileReader(path);

            NoteCollection collection = new Gson().fromJson(reader, NoteCollection.class);
            if (collection == null) collection = new NoteCollection();

            for (Note note : collection.tree.values()) {
                if (note.date == null) note.setDate();
                if (note.title == null || note.title.isBlank()) {
                    note.setTitle("(Untitled)");
                }
            }

            return collection;
        } catch (Exception e) {
            e.printStackTrace();
            return new NoteCollection();
        }
    }

    public void insert(Note note) {
        if (note.getDate() == null) note.setDate();
        if (note.getTitle() == null || note.getTitle().isBlank())
            note.setTitle();

        tree.put(note.getDate(), note);
    }

    public TreeMap<String, Note> getAll() {
        return tree;
    }
}
