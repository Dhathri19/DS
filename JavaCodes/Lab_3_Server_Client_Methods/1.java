import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Automatically send keep-alive packets.
// Set keep alive to 1 using setsockopt.

public class ServerSocketExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            
            // Enable SO_KEEPALIVE
            serverSocket.setKeepAlive(true);
            
            System.out.println("Server is running. Waiting for a client to connect...");
            
            Socket clientSocket = serverSocket.accept();
            
            // Test SO_KEEPALIVE
            System.out.println("SO_KEEPALIVE enabled: " + clientSocket.getKeepAlive());
            
            // Close the sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
