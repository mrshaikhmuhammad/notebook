package note;

import java.util.TreeMap;

public class NoteCollection {
    private int counter;
    private TreeMap<Integer, Note> tree;

    public NoteCollection(){
        counter = 0;
        tree = new TreeMap<>();
    }

    public void insert(Note note){
        // if already exist then update
        if(note.getId() == -1){
            note.setTitle();
            note.setId(++counter);
        }

        // otherwize update it
        tree.put(note.getId(), note);
    }
}