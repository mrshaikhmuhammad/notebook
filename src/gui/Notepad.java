package gui;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import com.google.gson.Gson;

import note.Note;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

import javax.swing.text.*;
import javax.swing.undo.*;


public class Notepad {
    private final static String JSON_ADDRESS = "src/store/Notes.json";
    
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
                        FileWriter writer = new FileWriter(JSON_ADDRESS);
                        Gson gson = new Gson();

                        gson.toJson(note, writer);
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
