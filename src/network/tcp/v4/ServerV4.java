package network.tcp.v4;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV4 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Server started");
    ServerSocket serverSocket = new ServerSocket(PORT);
    log("Server socket created - Listening on port: " + PORT);

    while (true) {
      Socket socket = serverSocket.accept(); // 블로킹
      log("Socket connected: " + socket);

      SessionV4 session = new SessionV4(socket);
      Thread thread = new Thread(session);
      thread.start();
    }
  }
}