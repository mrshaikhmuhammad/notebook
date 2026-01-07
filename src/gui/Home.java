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
            JFrame frame = new JFrame(TITLE);
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


            JButton deleteButton = new JButton("🗑 Delete Note");
            deleteButton.setBackground(Color.black);
            deleteButton.setForeground(Color.white);
            deleteButton.setOpaque(true);
            deleteButton.setBorderPainted(false);

            deleteButton.addActionListener(_ -> {
                Note selected = leftList.getSelectedValue();
                if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this note?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    collection.getAll().remove(selected.getDate());}
                //Updating JSON:
                    SaveManager saveManager = new SaveManager();
                    saveManager.saveAll(collection);
                //Updating UI:
                    listModel.removeElement(selected);
                    centerArea.setText("");
                }else {
                    JOptionPane.showMessageDialog(frame, "No note selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            });

    JButton summaryButton = new JButton("📝 Summarize");
            summaryButton.setBackground(Color.black);
            summaryButton.setForeground(Color.white);
            summaryButton.setOpaque(true);
            summaryButton.setBorderPainted(false);
            summaryButton.setPreferredSize(new Dimension(110,25));

            summaryButton.addActionListener(_ -> {
                Note selected = leftList.getSelectedValue();
                if (selected != null) {
                    try {
                        String text = selected.getText();
                        String summary = ai.Summarize.summarize(text);

                        // Update the note object
                        selected.setSummary(summary);

                        // Update JSON file permanently
                        SaveManager saveManager = new SaveManager();
                        saveManager.saveAll(collection);


                        // Show summary in a dialog
                        JTextArea summaryArea = new JTextArea(summary);
                        summaryArea.setLineWrap(true);
                        summaryArea.setWrapStyleWord(true);
                        summaryArea.setEditable(false);
                        summaryArea.setFont(new Font("Arial", Font.PLAIN, 14));
                        JScrollPane scrollPane = new JScrollPane(summaryArea);
                        scrollPane.setPreferredSize(new Dimension(400, 300));

                        JOptionPane.showMessageDialog(frame, scrollPane, "Summary", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "Error generating summary:\n" + e.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "No note selected to summarize.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });


            // Top panel with label and buttons
            // --- Top panel with label and Delete button ---
            JPanel topPanel = new JPanel(new BorderLayout(5, 5));
            JLabel titleLabel = new JLabel("Your Notes");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            topPanel.add(titleLabel, BorderLayout.WEST);

// Delete button on the right
            // Top panel button panel (right side)
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            buttonPanel.add(summaryButton);
            buttonPanel.add(deleteButton);
            topPanel.add(buttonPanel, BorderLayout.EAST);


// Add top panel to main panel
            mainPanel.add(topPanel, BorderLayout.NORTH);

// Add New button
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
