package dictionaryServer;//This class is for Server to handle all the client connections

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket clientSocket;
    private BufferedWriter send;
    private BufferedReader receive;
    private Dictionary dictionary;


    private String client;


    public ClientHandler (Socket clientSocket, Dictionary dictionary){
        try {
            this.clientSocket = clientSocket;
            this.dictionary = dictionary;
            //Server to send message
            this.send = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            //Server to receive message
            this.receive = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("IOException: Client Handler");
            closeEverything(clientSocket, receive, send);
        }


    }
    @Override
    public void run() {
        System.out.println("You are connected to the dictionary server!");
        String command;
        String word;
        String meaning;
        while (clientSocket.isConnected()){
            System.out.println("What's your request?");
            try {
                command = receive.readLine().toLowerCase();


            } catch (IOException e) {
                System.out.println("IOException: ClientHandler - run() - readLine()");
                closeEverything(clientSocket, receive, send);
            }
        }
    }

    private void dictionaryHandle(){
        Dictionary d = new Dictionary("C:/Programming/Java/DictionaryServer/src/main/java/dictionary.json");
        Scanner input = new Scanner(System.in);

//        Boolean loop = true;
//        do {
//            System.out.println("Enter the command: ");
//            String command = input.nextLine().toLowerCase();
//            String word = null;
//            String meaning = null;
//            switch (command) {
//                case "search":
//                    System.out.println("Search the word: ");
//                    word = input.nextLine().toLowerCase();
//                    d.search(word);
//                    break;
//                case "add":
//                    System.out.println("Add the word: ");
//                    word = input.nextLine().toLowerCase();
//                    System.out.println("Add the meaning: ");
//                    meaning = input.nextLine();
//                    d.add(word, meaning);
//                    break;
//                case "remove":
//                    System.out.println("Remove the word: ");
//                    word = input.nextLine().toLowerCase();
//                    d.remove(word);
//                    break;
//                case "update":
//                    System.out.println("Update the word: ");
//                    word = input.nextLine().toLowerCase();
//                    System.out.println("Update the meaning: ");
//                    meaning = input.nextLine();
//                    d.update(word, meaning);
//                    break;
//                case "quit":
//                    loop = false;
//                    break;
//            }
//        }
//        while (loop);
    }

    private void closeEverything(Socket clientSocket, BufferedReader receive, BufferedWriter send){
        try {
            if (clientSocket != null){
                clientSocket.close();
            }
            if (receive != null){
                receive.close();
            }
            if (send != null) {
                send.close();
            }
        } catch (IOException e) {
            System.out.println("IOException: ClientHandler - Close Everything");
        }
    }
}
