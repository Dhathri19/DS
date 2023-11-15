import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            // Enable SO_LINGER with a timeout of 30 seconds
            serverSocket.setSoLinger(true, 30);

            System.out.println("Server is running. Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();

            // Test SO_LINGER
            System.out.println("SO_LINGER enabled: " + clientSocket.getSoLinger());

            // Close the sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
