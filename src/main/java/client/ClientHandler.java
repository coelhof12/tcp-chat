package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Handles client-side communication with the server.
 */
public class ClientHandler {
    private final String host;
    private final int port;

    public ClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            Scanner scanner = new Scanner(System.in);

            String serverMessage = reader.readLine();
            if (serverMessage != null) {
                System.out.println(serverMessage);

                String username = scanner.nextLine();
                writer.println(username);

                serverMessage = reader.readLine();
                if (serverMessage != null) {
                    System.out.println(serverMessage);
                }
            } else {
                System.err.println("Server disconnected.");
                return;
            }

            new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.err.println("Disconnected from server.");
                }
            }).start();

            while (true) {
                String input = scanner.nextLine();
                writer.println(input);

                if (input.equalsIgnoreCase("/quit")) {
                    System.out.println("Disconnected from the server.");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}