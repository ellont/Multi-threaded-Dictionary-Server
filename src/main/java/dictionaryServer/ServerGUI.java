package dictionaryServer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;

public class ServerGUI {

    private static final String FILE_PATH = "path_to_your_json_file.json";

    public static void main(String[] args) {
        //The main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Dictionary"));
        mainPanel.setLayout(new GridLayout(2, 1));


        JFrame frame = new JFrame("Dictionary App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel wordPanel = new JPanel();
        JPanel meaningPanel = new JPanel();
        JPanel buttonPanel = new JPanel();


        JTextField wordField = new JTextField(15);
        JTextArea meaningField = new JTextArea(2,15);
        meaningField.setWrapStyleWord(true);

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");

        wordPanel.add(new JLabel("Word:"));
        wordPanel.add(wordField);
        meaningPanel.add(new JLabel("Meaning:"));
        meaningPanel.add(meaningField);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);

        // Add button listeners
        addButton.addActionListener(e -> handleAdd(wordField, meaningField));
        removeButton.addActionListener(e -> handleRemove(wordField));
        searchButton.addActionListener(e -> handleSearch(wordField, meaningField));
        updateButton.addActionListener(e -> handleUpdate(wordField, meaningField));

        frame.setVisible(true);
    }

    private static void handleAdd(JTextField wordField, JTextArea meaningField) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(FILE_PATH));
            jsonObject.put(wordField.getText(), meaningField.getText());
            try (FileWriter file = new FileWriter(FILE_PATH)) {
                file.write(jsonObject.toJSONString());
            }
            JOptionPane.showMessageDialog(null, "Added successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void handleRemove(JTextField wordField) {
        // Similar to handleAdd but remove the word
    }

    private static void handleSearch(JTextField wordField, JTextArea meaningField) {
        // Search for the word and set the meaning in meaningField
    }

    private static void handleUpdate(JTextField wordField, JTextArea meaningField) {
        // Similar to handleAdd but update the meaning for the existing word
    }

}
