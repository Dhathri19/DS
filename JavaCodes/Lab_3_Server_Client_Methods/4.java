
// Sndbuf and Recbuf control the send and receive buffer sizes respectively.
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            // Set SO_SNDBUF and SO_RCVBUF to custom values (e.g., 8192 bytes)
            serverSocket.setSendBufferSize(8192);
            serverSocket.setReceiveBufferSize(8192);

            System.out.println("Server is running. Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();

            // Test SO_SNDBUF and SO_RCVBUF
            System.out.println("SO_SNDBUF: " + clientSocket.getSendBufferSize());
            System.out.println("SO_RCVBUF: " + clientSocket.getReceiveBufferSize());

            // Close the sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
