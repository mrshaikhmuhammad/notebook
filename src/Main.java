import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.*;

public class Main {
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        Frame screen = new Frame("Type Scribe", "logo/LogoBlack.png", panel);

        JTextArea field = new JTextArea();
        field.setPreferredSize(new Dimension(1000, 1400));
        field.setFont(new Font("lato",Font.PLAIN, 12));
        field.setMargin(new Insets(60, 125, 60, 125));
        field.setLineWrap(true);
        field.setWrapStyleWord(true);
        panel.add(field);



        screen.start();
        



        
    }
}
