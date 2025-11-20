package gui;
import java.awt.BorderLayout;

import javax.swing.*;

public class Frame{
    public JFrame screen;

    public Frame(String title, String icon, JPanel panel){
        //Intial Setting
        screen = new JFrame();
        screen.setSize(1280, 720);
        screen.setTitle(title);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);

        //Icon Configuration
        setIcon(icon);

        //Set Panel
        screen.add(panel, BorderLayout.CENTER);
    }

    private void setIcon(String address){
        ImageIcon icon = new ImageIcon(address);
        screen.setIconImage(icon.getImage());
    }

    public void start(){
        screen.setVisible(true);
    }
}
