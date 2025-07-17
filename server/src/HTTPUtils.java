package server;

import java.io.*;
import java.util.*;

/**
 * Utility class with helper functions for HTTP operations.
 */
public class HTTPUtils {

    // Sends HTTP response to client
    public static void sendResponse(OutputStream out, String status, String contentType, byte[] content) throws IOException {
        PrintWriter writer = new PrintWriter(out, false);
        writer.printf("HTTP/1.1 %s\r\n", status);
        writer.printf("Content-Type: %s\r\n", contentType);
        writer.printf("Content-Length: %d\r\n", content.length);
        writer.print("Connection: close\r\n\r\n");
        writer.flush();
        out.write(content);
        out.flush();
    }

    // Detect MIME type from file extension
    public static String getContentType(String fileName) {
        if (fileName.endsWith(".html")) return "text/html";
        if (fileName.endsWith(".css")) return "text/css";
        if (fileName.endsWith(".js")) return "application/javascript";
        if (fileName.endsWith(".png")) return "image/png";
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
        if (fileName.endsWith(".gif")) return "image/gif";
        return "application/octet-stream";
    }

    // Generate basic HTML listing of directory contents
    public static String generateDirectoryListing(File dir) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h2>Directory listing for ").append(dir.getName()).append("</h2><ul>");

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            html.append("<li><a href=\"")
                .append(file.getName())
                .append("\">")
                .append(file.getName())
                .append("</a></li>");
        }

        html.append("</ul></body></html>");
        return html.toString();
    }
}
