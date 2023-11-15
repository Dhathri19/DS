
// Is an option the Nagle's algo which can introduce small delays in small data packet transmission.

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            // Disable TCP_NODELAY
            serverSocket.setTcpNoDelay(false);

            System.out.println("Server is running. Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();

            // Test TCP_NODELAY
            System.out.println("TCP_NODELAY enabled: " + !clientSocket.getTcpNoDelay());

            // Close the sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
