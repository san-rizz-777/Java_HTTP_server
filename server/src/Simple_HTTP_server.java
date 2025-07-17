package server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Entry point for the simple HTTP server.
 * Creates a thread pool based on CPU cores and listens on port 8080.
 */
public class Simple_HTTP_server {

    private static final int PORT = 8080;

    // Detect available processor cores to size thread pool
    private static final int CORES = Runtime.getRuntime().availableProcessors();
    private static volatile boolean running = true;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(CORES);
        ServerSocket serverSocket = null;

        System.out.println("🧠 Detected " + CORES + " CPU cores — using " + CORES + " threads.");
        
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("🚀 Server running on http://localhost:" + PORT);

            // Graceful shutdown hook on Ctrl+C or stop
            ServerSocket finalServerSocket = serverSocket;
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\n🛑 Shutting down server...");
                running = false;

                try {
                    finalServerSocket.close(); // this unblocks accept()
                } catch (IOException ignored) {}

                threadPool.shutdown();
                System.out.println("✅ Server shut down cleanly.");
            }));

            // Main server loop: accept connections and handle them in thread pool
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    threadPool.execute(new ClientHandler(clientSocket));
                } catch (IOException e) {
                    if (running) {
                        System.err.println("⚠️ Connection error: " + e.getMessage());
                    }
                    break; // exit if not running
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Failed to start server: " + e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException ignored) {}
            }
            threadPool.shutdown();
        }
    }
}
