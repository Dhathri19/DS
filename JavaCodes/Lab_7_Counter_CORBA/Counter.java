import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedMemoryCounter {
    public static void main(String[] args) {
        // Create an AtomicInteger with an initial value of 0
        AtomicInteger counter = new AtomicInteger(0);

        // Create an ExecutorService with a fixed number of threads
        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // Submit tasks to the ExecutorService
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    // Increment the counter atomically
                    counter.incrementAndGet();
                }
            });
        }

        // Shutdown the ExecutorService to prevent new tasks from being submitted
        executorService.shutdown();

        try {
            // Wait for all threads to finish
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                // Handle if threads do not finish within the specified time
                System.err.println("Threads did not finish in time.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the counter
        System.out.println("Final Counter Value: " + counter.get());
    }
}
