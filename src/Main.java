import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;

import gui.Frame;
import note.Note;

import com.google.gson.*;


public class Main {
    public static void main(String[] args) {
        //Link Panel to Frame
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        Frame screen = new Frame("Type Scribe", "./src/logo/LogoBlack.png", scrollPane);

        //Add Text Area to Panel
        JTextArea textArea = new JTextArea();
        textArea.setRows(99);
        textArea.setColumns(70);
        textArea.setFont(new Font("lato",Font.PLAIN, 16));
        textArea.setMargin(new Insets(60, 125, 60, 125));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //Add Undo Manager to Text Area
        Document doc = textArea.getDocument();
        UndoManager undoManager = new UndoManager();
        doc.addUndoableEditListener(undoManager);

        //Add Key Listeners
        textArea.addKeyListener(new KeyAdapter() {
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
                        FileWriter writer = new FileWriter("src/store/Notes.json");
                        
                        Gson gson = new Gson();
                        gson.toJson(note, writer);

                        writer.close();
                    }
                } catch(CannotRedoException | CannotUndoException cantException){
                } catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
        
        panel.add(textArea);
        screen.start();        
    }
}
