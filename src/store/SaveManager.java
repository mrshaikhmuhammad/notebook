package store;

import note.*;
import java.io.*;
import com.google.gson.Gson;

public class SaveManager {
    private final static String JSON_ADDRESS = "src/store/Notes.json";
    private static NoteCollection notes;

    public SaveManager(){
        try (FileReader reader = new FileReader(JSON_ADDRESS);) {
            Gson gson = new Gson();
            notes = gson.fromJson(reader, NoteCollection.class);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        if (notes == null) {
            notes = new NoteCollection();
        }   
    }

    public void save(Note note){
        notes.insert(note);
        
        try (FileWriter writer = new FileWriter(JSON_ADDRESS);) {
            Gson gson = new Gson();
            gson.toJson(notes, writer);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

//saveAll notes in collection after deleting or modifying:
public void saveAll(NoteCollection collection){
    try (FileWriter writer = new FileWriter(JSON_ADDRESS)) {
        Gson gson = new Gson();
        gson.toJson(collection, writer);
    } catch (Exception e){
        System.err.println("Error saving notes: " + e.getMessage());
    }
}

}