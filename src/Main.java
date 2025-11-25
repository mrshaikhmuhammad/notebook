import javax.swing.*;

import gui.components.Frame;
import gui.Notepad;


public class Main {
    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        JScrollPane scrollPane = notepad.component();
        Frame screen = new Frame("Type Scribe", "./src/logo/LogoBlack.png", scrollPane);
        screen.start();        
    }
}


 /*
    Main(){
        AVL tree = new AVL();
        tree = gson(file, AVL);

        Frame screen = new Frame("Type Scribe");
        SaveManager save = new SaveManager(tree);
        Notepad notepad = new Notepad(SaveManager);
        
        screen.add(notepad);

        gson.toJson(tree, file);
    }
 
 
 
 
 */

