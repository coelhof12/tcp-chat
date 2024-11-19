package server;

import java.io.IOException; // Ensure imports are correct

public class ServerMain {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8090; // Default port

        try {
            Server server = new Server(port); // Create Server instance
            server.start(); // Start the server
        } catch (IOException e) {
            System.err.println("Failed to start the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}