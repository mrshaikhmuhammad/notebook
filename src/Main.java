import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import gui.Frame;

public class Main {
    public static void main(String[] args) {
        //Link Panel to Frame
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        Frame screen = new Frame("Type Scribe", "logo/LogoBlack.png", scrollPane);

        //Add Text Area to Panel
        JTextArea textArea = new JTextArea();
        textArea.setRows(99);
        textArea.setColumns(70);
        textArea.setFont(new Font("lato",Font.PLAIN, 12));
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
                } catch (CannotRedoException | CannotUndoException exception){
                    System.err.println("Text Area is Empty ");
                }
            }
        });
        
        panel.add(textArea);
        screen.start();        
    }
}
