package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private final Map<String, List<ClientThread>> channels;
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        channels = new HashMap<>();
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    public synchronized void joinChannel(String channel, ClientThread client) {
        channels.computeIfAbsent(channel, k -> new ArrayList<>()).add(client);
        broadcast(channel, client.getUsername() + " joined the channel.");
    }

    public synchronized void leaveChannel(String channel, ClientThread client) {
        List<ClientThread> clients = channels.get(channel);
        if (clients != null) {
            clients.remove(client);
            if (clients.isEmpty()) {
                channels.remove(channel);
            } else {
                broadcast(channel, client.getUsername() + " left the channel.");
            }
        }
    }

    public synchronized void broadcast(String channel, String message) {
        List<ClientThread> clients = channels.get(channel);
        if (clients != null) {
            for (ClientThread client : clients) {
                client.sendMessage(message);
            }
        }
    }

    public synchronized List<String> listChannels() {
        return new ArrayList<>(channels.keySet());
    }
}
