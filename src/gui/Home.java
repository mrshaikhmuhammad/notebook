package gui;

import note.*;
import store.SaveManager;
import gui.components.Frame;
import javax.swing.*;
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



            // Load all notes
            NoteCollection collection = NoteCollection.load();
            DefaultListModel<Note> listModel = new DefaultListModel<>();


            for (Note note : collection.getAll().values()) {
                listModel.addElement(note);
            }

            // JList for notes
            JList<Note> leftList = new JList<>(listModel);
            leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Cell renderer to show title + date
            leftList.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Note note = (Note) value;
                    label.setText(" • "+(note.getTitle() == null ? "(Untitled)" : note.getTitle()) );
                    return label;
                }
            });

            JScrollPane leftScrollPane = new JScrollPane(leftList);
            leftScrollPane.setPreferredSize(new Dimension(200, 0));
            mainPanel.add(leftScrollPane, BorderLayout.WEST);

            // Center text area
            JTextArea centerArea = new JTextArea();
            centerArea.setLineWrap(true);
            centerArea.setWrapStyleWord(true);
            mainPanel.add(new JScrollPane(centerArea), BorderLayout.CENTER);

            // Show note text when selected
            leftList.addListSelectionListener(_ -> {
                Note selected = leftList.getSelectedValue();
                if (selected != null) {
                    centerArea.setText(selected.getText());
                }
            });

            // Add New Note button
            JButton addNew = getJButton();

            mainPanel.add(new JLabel("Your Notes"), BorderLayout.NORTH);
            mainPanel.add(addNew, BorderLayout.SOUTH);

            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }

    private static JButton getJButton() {
        JButton addNew = new JButton("+ Add New Note");
        addNew.setBackground(Color.black);
        addNew.setForeground(Color.white);
        addNew.setOpaque(true);
        addNew.setBorderPainted(false);

        addNew.addActionListener(_ -> {


            // open Notepad UI
            SaveManager saveManager = new SaveManager();
            Notepad notepad = new Notepad(saveManager);
            JScrollPane scrollPane = notepad.component();
            Frame screen = new Frame(TITLE, ICON_ADDRESS, scrollPane);
            screen.start();
        });
        return addNew;
    }
}
