package network.tcp.v5;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV5 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Server started");
    ServerSocket serverSocket = new ServerSocket(PORT);
    log("Server socket listening on port: " + PORT);

    while (true) {
      Socket socket = serverSocket.accept(); // blocking
      log("Socket connected: " + socket);

      SessionV5 session = new SessionV5(socket);
      Thread thread = new Thread(session);
      thread.start();
    }
  }
}
