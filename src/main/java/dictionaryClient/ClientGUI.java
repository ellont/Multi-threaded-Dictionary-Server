package dictionaryClient;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;

public class ClientGUI {

    private static final String FILE_PATH = "path_to_your_json_file.json";

    public static void main(String[] args) {
        //The main panel
        JFrame frame = new JFrame("Dictionary");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Dictionary"));
        mainPanel.setLayout(new GridLayout(1, 2));

        //The word/meaning section.
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JLabel wordLabel = new JLabel("Word: ");
        JLabel meaningLabel = new JLabel("Meaning: ");
        JTextField wordField = new JTextField(15);
        JTextArea meaningField = new JTextArea(2,15);
        meaningField.setWrapStyleWord(true);
        meaningField.setLineWrap(true);

        panel1.add(wordLabel);
        panel1.add(wordField);
        panel1.add(meaningLabel);
        panel1.add(meaningField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);

        mainPanel.add(panel1);
        mainPanel.add(buttonPanel);
        frame.add(mainPanel);

        //Add button listeners
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

