package server;

public class ServerMain {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8090;
        Server server = new Server(port);
        server.start();
    }
}