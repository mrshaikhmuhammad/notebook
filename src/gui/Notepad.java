package gui;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.undo.*;
import javax.swing.text.*;

import note.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Notepad {
    private final static String JSON_ADDRESS = "src/store/Notes.json";
    private static ArrayList<Note> notes = new ArrayList<Note>();
    private JTextArea textArea(){

        JTextArea textArea = new JTextArea();
        textArea.setRows(99);
        textArea.setColumns(70);
        textArea.setFont(new Font("lato",Font.PLAIN, 16));
        textArea.setMargin(new Insets(60, 100, 60, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(this.actionManager(textArea));
        

        return textArea;
    }

    private KeyAdapter actionManager(JTextArea textArea){   
        Document doc = textArea.getDocument();
        UndoManager undoManager = new UndoManager();
        doc.addUndoableEditListener(undoManager);

        KeyAdapter actionManager = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){
                try{
                    if(event.isControlDown() && event.getKeyCode() == KeyEvent.VK_Z){
                        undoManager.undo();
                    }
                    else if(event.isControlDown() && event.getKeyCode() == KeyEvent.VK_Y){
                        undoManager.redo();
                    }
                    else if(event.isControlDown() && event.getKeyCode() == KeyEvent.VK_S){
                        
                        Note note = new Note(textArea.getText());
                        
                        Gson gson = new Gson();

                        File file  = new File(JSON_ADDRESS);

                        if(file.exists()){
                            FileReader reader = new FileReader(file);
                            notes = gson.fromJson(reader, new TypeToken<ArrayList<Note>>(){}.getType());

                            reader.close();
                        }

                        notes.add(note);

                        FileWriter writer = new FileWriter(file);
                       

                        gson.toJson(notes, writer);
                        writer.close();
                    }
                } catch(CannotRedoException | CannotUndoException cantException){
                } catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        };

        return actionManager;
    }

    public JScrollPane component(){
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        JTextArea textArea = this.textArea();
        panel.add(textArea);

        return scrollPane;
    }
}
