import javax.swing.*;
import gui.components.Frame;
import store.SaveManager;
import gui.Notepad;

public class Main {
    private static final String TITLE = "Type Scribe";
    private static final String ICON_ADDRESS = "./src/logo/LogoBlack.png";
    public static void main(String[] args) {
        SaveManager saveManager = new SaveManager();
        Notepad notepad = new Notepad(saveManager);
        JScrollPane scrollPane = notepad.component();
        Frame screen = new Frame(TITLE, ICON_ADDRESS, scrollPane);
        screen.start();        
    }
}