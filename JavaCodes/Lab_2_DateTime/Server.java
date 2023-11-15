import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentDayTimeServer {
    public static void main(String[] args) {
        final int PORT = 12345;
        ExecutorService executor = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                
                Runnable task = new ClientHandler(clientSocket);
                executor.submit(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (OutputStream outputStream = clientSocket.getOutputStream();
             PrintWriter out = new PrintWriter(outputStream, true)) {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(new Date());
            out.println(dateTime);
            System.out.println("Sent date-time to " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
