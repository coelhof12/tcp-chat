package server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private final Server server;
    private PrintWriter writer;
    private String username;
    private String currentChannel;

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Enter your username:");
            username = reader.readLine();
            writer.println("Welcome, " + username + "! Use /join <channel> to join a chat room.");

            while (true) {
                String message = reader.readLine();
                if (message == null || message.equalsIgnoreCase("/quit")) {
                    disconnect(); // Handle disconnection here
                    break;
                }
                handleCommand(message);
            }
        } catch (IOException e) {
            System.err.println("Error in client thread for user " + username + ": " + e.getMessage());
        } finally {
            disconnect(); // Ensure cleanup even in case of an error
        }
    }

    private void handleCommand(String message) {
        if (message.startsWith("/join ")) {
            String channel = message.substring(6).trim();
            if (currentChannel != null) {
                server.leaveChannel(currentChannel, this);
            }
            currentChannel = channel;
            server.joinChannel(channel, this);
        } else if (message.equals("/list-channels")) {
            sendMessage("Available channels: " + server.listChannels());
        } else {
            if (currentChannel != null) {
                server.broadcast(currentChannel, username + ": " + message);
            } else {
                sendMessage("Join a channel first using /join <channel>");
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String getUsername() {
        return username;
    }

    /**
     * Disconnects the client and performs cleanup.
     */
    public void disconnect() {
        try {
            if (currentChannel != null) {
                server.leaveChannel(currentChannel, this); // Remove client from the channel
            }
            if (socket != null && !socket.isClosed()) {
                socket.close(); // Close the socket
            }
            System.out.println("Client " + username + " disconnected.");
        } catch (IOException e) {
            System.err.println("Error while disconnecting client " + username + ": " + e.getMessage());
        }
    }
}