package gui;
import store.SaveManager;
import javax.swing.*;
import gui.components.Frame;
import java.awt.*;

public class Home {
    private static final String TITLE = "Type Scribe";
    private static final String ICON_ADDRESS = "./src/logo/LogoBlack.png";
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }


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

            addNew.addActionListener(e -> {
                // create Notepad UI and open it in a separate window so the user sees it
                SaveManager saveManager = new SaveManager();
                Notepad notepad = new Notepad(saveManager);
                JScrollPane scrollPane = notepad.component();
                Frame screen = new Frame(TITLE, ICON_ADDRESS, scrollPane);
                screen.start();
            });

            mainPanel.add(new JLabel("Your Notes"), BorderLayout.NORTH);
            mainPanel.add(addNew, BorderLayout.SOUTH);


            frame.setContentPane(mainPanel);
            frame.setVisible(true);});
    }
    }
