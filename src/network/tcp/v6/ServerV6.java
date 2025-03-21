package network.tcp.v6;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerV6 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Server started");
    SessionManagerV6 sessionManager = new SessionManagerV6();
    ServerSocket serverSocket = new ServerSocket(PORT);
    log("Server socket listening on port: " + PORT);

    // register ShutdownHook
    ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
    Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown-hook"));

    // shutdownHook will be called by another thread
    // after calling it, socketException can occur
    // while serverSocket.accept() is waiting
    // this is because When the serverSocket is closed, accept() wakes up immediately and throws a SocketException.
    try {
      while (true) {
        Socket socket = serverSocket.accept(); // blocking
        log("Socket connected: " + socket);

        SessionV6 session = new SessionV6(socket, sessionManager);
        Thread thread = new Thread(session);
        thread.start();
      }
    } catch (IOException e) {
      log("Server socket closed: " + e);
    }

  }

  static class ShutdownHook implements Runnable {

    private final ServerSocket serverSocket;
    private final SessionManagerV6 sessionManager;

    public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
      this.serverSocket = serverSocket;
      this.sessionManager = sessionManager;
    }

    @Override
    public void run() {
      log("run shutdownHook");
      try {
        sessionManager.closeAll();
        serverSocket.close();

        // wait for cleaning up
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("e= " + e);
      }
    }
  }

}
