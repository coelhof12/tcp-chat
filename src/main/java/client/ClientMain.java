package client;

public class ClientMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ClientMain <host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        ClientHandler clientHandler = new ClientHandler(host, port);
        clientHandler.start();
    }
}