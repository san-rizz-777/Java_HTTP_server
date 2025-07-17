package server;

import java.io.*;
import java.net.Socket;
import java.nio.file.*;

/**
 * Handles a single client HTTP request.
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private static final String ROOT_DIR = "public"; // Static file directory

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream()
        ) {
            String requestLine = in.readLine();
            if (requestLine == null || !requestLine.startsWith("GET")) {
                HTTPUtils.sendResponse(out, "400 Bad Request", "text/plain", "Invalid Request".getBytes());
                return;
            }

            // Parse requested path
            String[] parts = requestLine.split(" ");
            String path = parts[1];
            if (path.equals("/")) path = "/index.html";
            File file = new File(ROOT_DIR + path);

            // Handle file or directory
            if (!file.exists()) {
                HTTPUtils.sendResponse(out, "404 Not Found", "text/plain", "File Not Found".getBytes());
            } else if (file.isDirectory()) {
                byte[] listing = HTTPUtils.generateDirectoryListing(file).getBytes();
                HTTPUtils.sendResponse(out, "200 OK", "text/html", listing);
            } else {
                String contentType = HTTPUtils.getContentType(file.getName());
                byte[] content = Files.readAllBytes(file.toPath());
                HTTPUtils.sendResponse(out, "200 OK", contentType, content);
            }

        } catch (IOException e) {
            System.err.println("⚠️ Client error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {}
        }
    }
}
