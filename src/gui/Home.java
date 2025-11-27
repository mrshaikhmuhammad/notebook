package gui;

import javax.swing.*;

import java.awt.*;
public class Home extends JFrame {
    public static void main(String[] args) {


        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Content for the scroll panel (left)
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 1; i <= 40; i++) {
            listModel.addElement("Item " + i);
        }
        JList<String> leftList = new JList<>(listModel);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Put the list inside a JScrollPane
        JScrollPane leftScrollPane = new JScrollPane(leftList);
        leftScrollPane.setPreferredSize(new Dimension(200, 0));
        // Add scroll pane to the WEST (left) of the BorderLayout
        mainPanel.add(leftScrollPane, BorderLayout.WEST);

        // Center content so layout is visible
        JTextArea centerArea = new JTextArea("Main content area\n\nResize the window to see behavior.");
        centerArea.setLineWrap(true);
        centerArea.setWrapStyleWord(true);
        mainPanel.add(new JScrollPane(centerArea), BorderLayout.CENTER);

        // Optional: toolbar at top and status at bottom
        mainPanel.add(new JLabel("Top toolbar (North)"), BorderLayout.NORTH);
        mainPanel.add(new JLabel("Status bar (South)"), BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    }
