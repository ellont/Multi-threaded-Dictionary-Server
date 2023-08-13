package dictionaryServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DictionaryServer {

    //Server socket
    private ServerSocket listeningSocket;
    public static String dictionaryFilePath;
    //Server port
    public static final int SERVER_PORT = 2000;
    //Server IP address
    public static final String SERVER_IP = "localhost";

    public static void main(String[] args) {
        //check args
        ServerSocket listeningSocket = null;
        Socket clientSocket = null;
        DictionaryServer dictionaryServer = null;
        dictionaryFilePath = "C:/Programming/Java/DictionaryServer/src/main/java/dictionary.json";
        Dictionary dictionary = new Dictionary(dictionaryFilePath);

        try {
            listeningSocket = new ServerSocket(SERVER_PORT);
            dictionaryServer = new DictionaryServer(listeningSocket);
            System.out.println("Server listening on "+SERVER_PORT+" for a connection.");
            int counter = 0; //counter to keep track of the number of clients

            while (!listeningSocket.isClosed()) {
                clientSocket = listeningSocket.accept();

                counter++;
                System.out.println("Client connection # "+counter+" established.");
                ClientHandler clientHandler = new ClientHandler(clientSocket, dictionary);
                Thread clientThread = new Thread(clientHandler);
            }

        } catch (IOException e) {
            System.out.println("IOException: Server");
            dictionaryServer.closeServerSocket();
        }


//        try {
//            //Create a server socket listening on port 4444
//            listeningSocket = new ServerSocket(PORT);
//            int i = 0; //counter to keep track of the number of clients
//
//            //Listen for incoming connections for ver
//            while (true) {
//                System.out.println("Server listening on port 4444 for a connection");
//                //Accept an incoming client connection request
//                clientSocket = listeningSocket.accept(); //This method will block until a connection request is received
//                i++;
//                System.out.println("Client conection number " + i + " accepted:");
//                //System.out.println("Remote Port: " + clientSocket.getPort());
//                System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
//                System.out.println("Local Port: " + clientSocket.getLocalPort());
//
//                //Get the input/output streams for reading/writing data from/to the socket
//                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//
//
//                //Read the message from the client and reply
//                //Notice that no other connection can be accepted and processed until the last line of
//                //code of this loop is executed, incoming connections have to wait until the current
//                //one is processed unless...we use threads!
//                String clientMsg = null;
//                try {
//                    while ((clientMsg = in.readLine()) != null) {
//                        System.out.println("Message from client " + i + ": " + clientMsg);
//                        out.write("Server Ack " + clientMsg + "\n");
//                        out.flush();
//                        System.out.println("Response sent");
//                    }
//                    System.out.println("Server closed the client connection!!!!! - received null");
//                } catch (SocketException e) {
//                    System.out.println("closed...");
//                }
//                // close the client connection
//                clientSocket.close();
//            }
//        } catch (SocketException ex) {
//            ex.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("here");
//            e.printStackTrace();
//        } finally {
//            if (listeningSocket != null) {
//                try {
//                    // close the server socket
//                    listeningSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    public DictionaryServer(ServerSocket listeningSocket){
        this.listeningSocket = listeningSocket;
    }

    private void closeServerSocket(){
        try {
            if (listeningSocket != null)
                listeningSocket.close();
        } catch (IOException e) {
            System.out.println("IOException: Close listeningSocket.");
        }
    }
}
