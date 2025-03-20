package network.tcp.v3;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV3 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Server started");

    // Create a server socket and listen on the specified port
    ServerSocket serverSocket = new ServerSocket(PORT);
    log("Server socket created - Listening on port: " + PORT);

    while (true) {
      // Accept a client connection (blocking)
      Socket socket = serverSocket.accept();
      log("Socket connected: " + socket);

      // Create a new session for each client and run it in a separate thread
      SessionV3 session = new SessionV3(socket);
      Thread thread = new Thread(session);
      thread.start();
    }
  }
}
