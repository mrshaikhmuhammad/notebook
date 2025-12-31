package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.undo.*;
import javax.swing.text.*;

import note.Note;
import store.SaveManager;

public class Notepad {
    private static SaveManager saveManager;
    private UndoManager undoManager = new UndoManager();
    private Note note;

    public Notepad(SaveManager saveManager){
        Notepad.saveManager = saveManager;
        try {
            note = new Note("");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private JTextArea textArea(){

        JTextArea textArea = new JTextArea();
        textArea.setRows(99);
        textArea.setColumns(70);
        textArea.setFont(new Font("lato",Font.PLAIN, 16));
        textArea.setMargin(new Insets(60, 100, 60, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(this.keyManager(textArea));
        return textArea;
    }

    private KeyAdapter keyManager(JTextArea textArea){

        Document doc = textArea.getDocument();
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
                        note.setText(textArea.getText());
                        saveManager.save(note);
                    }
                } catch(CannotRedoException | CannotUndoException cantException){
                    System.err.println("Undo/Redo operation failed: " + cantException.getMessage());
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
