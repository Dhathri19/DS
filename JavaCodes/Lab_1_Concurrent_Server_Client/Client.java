import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int portNumber = 12345;

        try {
            Socket socket = new Socket(serverAddress, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String userInput;
            while (true) {
                System.out.print("Enter a message to send: ");
                userInput = scanner.nextLine();

                if (userInput.equals("exit")) {
                    break;
                }

                out.println(userInput);
                String response = in.readLine();
                System.out.println("Received: " + response);
            }

            in.close();
            out.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
