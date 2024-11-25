package client;

public class ClientMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: <host> <port>");
            return;
        }

        try {
            String host = args[0]; // Get host from arguments
            int port = Integer.parseInt(args[1]); // Parse port as an integer

            // Use ClientHandler for the client functionality
            ClientHandler clientHandler = new ClientHandler(host, port);
            clientHandler.start();

        } catch (NumberFormatException e) {
            System.err.println("Error: Port must be a valid number: " + args[1]);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}