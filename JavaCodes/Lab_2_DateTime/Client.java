import java.io.*;
import java.net.*;

public class DayTimeClient {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // Change this to the server's IP address
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String serverResponse = in.readLine();
            System.out.println("Server date and time: " + serverResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
