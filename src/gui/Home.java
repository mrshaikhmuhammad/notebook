package gui;
import store.SaveManager;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }


        final SaveManager saveManager = new SaveManager();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Home");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 400);
            frame.setLocationRelativeTo(null);


            JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

            // Content for the scroll panel (left)
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (int i = 1; i <= 40; i++) {
                listModel.addElement("note " + i);
            }
            JList<String> leftList = new JList<>(listModel);
            leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Put the list inside a JScrollPane
            JScrollPane leftScrollPane = new JScrollPane(leftList);
            leftScrollPane.setPreferredSize(new Dimension(200, 0));
            // Add scroll pane to the WEST (left) of the BorderLayout
            mainPanel.add(leftScrollPane, BorderLayout.WEST);

            // Center content so layout is visible
            JTextArea centerArea = new JTextArea("to be done: ~a button to add notes ~list of notes on the left ~clicking a note opens it in notepad");
            centerArea.setLineWrap(true);
            centerArea.setWrapStyleWord(true);
            mainPanel.add(new JScrollPane(centerArea), BorderLayout.CENTER);


            JButton addNew = new JButton("+ Add New Note");
            addNew.setBackground(Color.black);
            addNew.setForeground(Color.white);
            addNew.setOpaque(true);
            addNew.setBorderPainted(false);

            addNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // create Notepad UI and open it in a separate window so the user sees it
                    Notepad notepad = new Notepad(saveManager);
                    JScrollPane notePane = notepad.component();

                    JFrame noteFrame = new JFrame("New Note");
                    noteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    noteFrame.setSize(800, 600);
                    noteFrame.setLocationRelativeTo(frame);
                    noteFrame.setContentPane(notePane);
                    noteFrame.setVisible(true);
                }
            });

            mainPanel.add(new JLabel("Your Notes"), BorderLayout.NORTH);
            mainPanel.add(addNew, BorderLayout.SOUTH);


            frame.setContentPane(mainPanel);
            frame.setVisible(true);});
    }
    }
