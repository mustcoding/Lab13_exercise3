package lab13_exercise3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
public class TextProcessingServer {

    int serverSidePortNo = 4321;

    public void startServer() {
        try {
            // 1. Create a server socket and bind it to the specified port
            ServerSocket serverSocket = new ServerSocket(serverSidePortNo);
            System.out.println("Server started. Waiting for client connection...");

            // 2. Listen for client connections and accept them
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // 3. Receive request from the client
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024]; // Buffer size for reading data from the client
            int bytesRead = inputStream.read(buffer);
            String text = new String(buffer, 0, bytesRead);
            System.out.println("Received text from client: " + text);

            // 4. Process the request (count the number of words)
            int wordCount = countWords(text);

            // 5. Send response back to the client
            OutputStream outputStream = clientSocket.getOutputStream();
            String response = "Word count: " + wordCount;
            byte[] responseBytes = response.getBytes();
            outputStream.write(responseBytes);
            outputStream.flush();
            System.out.println("Response sent to client: " + response);

            // 6. Close the client socket and server socket
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countWords(String text) {
        Scanner scanner = new Scanner(text);
        int wordCount = 0;
        while (scanner.hasNext()) {
            scanner.next();
            wordCount++;
        }
        scanner.close();
        return wordCount;
    }

    public static void main(String[] args) {
        TextProcessingServer server = new TextProcessingServer();
        server.startServer();
    }
}

