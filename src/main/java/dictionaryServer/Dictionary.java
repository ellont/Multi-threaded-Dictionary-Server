package dictionaryServer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {

    private static String dictionaryFilePath; //File Path to dictionary.json
    private static JSONObject dictionary; //JSON Object for the dictionary

    private static final String WORDNOTFOUND = ": Not found.";
    private static final String WORDEXIST = ": Already in the dictionary.";


    //Constructor
    public Dictionary(String dictionaryFilePath){
        this.dictionaryFilePath = dictionaryFilePath;
        JSONParser jsonParser = new JSONParser();

        try(FileReader read = new FileReader(dictionaryFilePath)) {
            //Read JSON file
            dictionary = (JSONObject) jsonParser.parse(read);
        } catch (ParseException e) {
            System.out.println("ParseError: dictionary.json");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException: dictionary");
            //e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Dictionary d = new Dictionary("C:/Programming/Java/DictionaryServer/src/main/java/dictionary.json");
        Scanner input = new Scanner(System.in);

        Boolean loop = true;
        do {
            System.out.println("Enter the command: ");
            String command = input.nextLine().toLowerCase();
            String word = null;
            String meaning = null;
            switch (command) {
                case "search":
                    System.out.println("Search the word: ");
                    word = input.nextLine().toLowerCase();
                    d.search(word);
                    break;
                case "add":
                    System.out.println("Add the word: ");
                    word = input.nextLine().toLowerCase();
                    System.out.println("Add the meaning: ");
                    meaning = input.nextLine();
                    d.add(word, meaning);
                    break;
                case "remove":
                    System.out.println("Remove the word: ");
                    word = input.nextLine().toLowerCase();
                    d.remove(word);
                    break;
                case "update":
                    System.out.println("Update the word: ");
                    word = input.nextLine().toLowerCase();
                    System.out.println("Update the meaning: ");
                    meaning = input.nextLine();
                    d.update(word, meaning);
                    break;
                case "quit":
                    loop = false;
                    break;
            }
        }
        while (loop);


        d.showDict();

    }


    //show the dictionary
    public void showDict(){
        for (Object word : dictionary.keySet()){
            System.out.println(word + ": " + dictionary.get(word));
        }
    }

    private void showWord(String word){
        System.out.println(word + ": " + dictionary.get(word));
    }

    private boolean isExist(String word){
        return dictionary.containsKey(word);
    }

    private void search(String word){
        if (dictionary.containsKey(word)){
            showWord(word);
        } else {
            System.out.println(word + WORDNOTFOUND);
        }
    }

    //a new word added should be visible to all client
    private void add(String word, String meaning){
        if (!dictionary.containsKey(word)){ //check if the word is a duplicate
            if (meaning.isBlank()) {//if doesn't exist yet, check the meaning is not blank
                System.out.println("Error: Missing meaning."+ word + " is not added.");
            } else {
                //if all requirements are met, add word to the dictionary
                dictionary.put(word, meaning);

                /** this needs to be broadcast to all clients */
                System.out.println(word + " was successfully added!");
            }
        } else {
            System.out.println(word + WORDEXIST);
        }
    }

    private void remove(String word){
        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
            System.out.println(word + " is removed!");
            //print to server a word is removed.
        }
    }
    //updates are visible to all clients
    private void update(String word, String meaning){
        if (dictionary.containsKey(word)) {
            if (meaning.isBlank()) {
                System.out.println("Error: Missing meaning."+ word + " is not updated.");
            } else {
                // to allow multiple meanings
                dictionary.put(word, meaning);
                System.out.println(word + " is updated!");
                //print to server a word is updated.
            }
        }
    }



}
