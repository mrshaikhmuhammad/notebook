package note;

import java.util.TreeMap;

public class NoteCollection {
    private TreeMap<String, Note> tree;

    public NoteCollection(){
        tree = new TreeMap<>();
    }

    public void insert(Note note){
        // if already exist then update
        if(note.getDate() == null){
            note.setTitle();
            note.setDate();
        }

        // otherwize update it
        tree.put(note.getDate(), note);
    }
}