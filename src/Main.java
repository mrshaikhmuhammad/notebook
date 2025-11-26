import javax.swing.*;
import gui.components.Frame;
import gui.Notepad;

public class Main {
    private static final String TITLE = "Type Scribe";
    private static final String ICON_ADDRESS = "./src/logo/LogoBlack.png";
    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        JScrollPane scrollPane = notepad.component();
        Frame screen = new Frame(TITLE, ICON_ADDRESS, scrollPane);
        screen.start();        
    }
}