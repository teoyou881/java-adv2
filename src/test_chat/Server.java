package test_chat;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private static final int PORT = 12345;


  public static void main(String[] args) throws IOException {
    SessionManager sessionManager = new SessionManager();

    log("Server started");
    ServerSocket serverSocket = new ServerSocket(PORT);
    log("Server listening on port: " + PORT);

    try {
      while (true) {
        Socket socket = serverSocket.accept();
        Session session = new Session(socket, new DataInputStream(socket.getInputStream()),
            new DataOutputStream(socket.getOutputStream()), sessionManager);

        Thread thread = new Thread(session);
        thread.start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
