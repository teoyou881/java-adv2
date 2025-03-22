package chat.server;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private final int port;
  private final CommandManager commandManager;
  private final chat.server.SessionManager sessionManager;
  private ServerSocket serverSocket;

  public Server(int port, CommandManager commandManager,
      chat.server.SessionManager sessionManager) {
    this.port = port;
    this.commandManager = commandManager;
    this.sessionManager = sessionManager;
  }

  public void start() throws IOException {
    log("Server started: " + commandManager.getClass());
    serverSocket = new ServerSocket(port);
    log("Server socket started - listening on port: " + port);
    addShutdownHook();
    running();
  }

  private void addShutdownHook() {
    ShutdownHook target = new ShutdownHook(serverSocket, sessionManager);
    Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown"));
  }

  private void running() {
    try {
      while (true) {
        Socket socket = serverSocket.accept(); // blocking
        log("Socket connected: " + socket);
        chat.server.Session session = new chat.server.Session(socket, commandManager,
            sessionManager);
        Thread thread = new Thread(session);
        thread.start();
      }
    } catch (IOException e) {
      log("Server socket closed: " + e);
    }
  }

  static class ShutdownHook implements Runnable {

    private final ServerSocket serverSocket;
    private final chat.server.SessionManager sessionManager;

    public ShutdownHook(ServerSocket serverSocket, chat.server.SessionManager sessionManager) {
      this.serverSocket = serverSocket;
      this.sessionManager = sessionManager;
    }

    @Override
    public void run() {
      log("ShutdownHook executed");
      try {
        sessionManager.closeAll();
        serverSocket.close();
        Thread.sleep(1000); // wait for resource cleanup
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("e = " + e);
      }
    }
  }
}
