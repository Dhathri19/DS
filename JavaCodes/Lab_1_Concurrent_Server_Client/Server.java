import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) { 
        // The server will listen to incoming connections.
        int portNumber = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server listening on port " + portNumber);
            
            // The server enters an infinite loop and waits and accepts client connections.
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // The client's socket info is printed.
                System.out.println("Accepted connection from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Behavior of server when interacting with each connected client.
// Runnable allows instances to be executed in seperate threads.
class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    // The run method is overriden from the Runnable interface.
    @Override
    // It creates input and output streams.
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + ": " + inputLine);
                out.println(inputLine);
            }
            
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
