package test_chat;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable {

  private final Socket socket;
  private final SessionManager sessionManager;
  private DataInputStream dis;
  private DataOutputStream dos;
  private boolean stoppped = false;

  public Session(Socket socket, DataInputStream dis, DataOutputStream dos,
      SessionManager sessionManager) throws IOException {
    this.socket = socket;
    this.dis = new DataInputStream(socket.getInputStream());
    this.dos = new DataOutputStream(socket.getOutputStream());
    this.sessionManager = sessionManager;
    sessionManager.add(this);
  }

  @Override
  public void run() {
    try {
      while (true) {
        // Receive message from client
        String received = dis.readUTF();
        log("client -> server: " + received);

        if (received.equals("exit")) {
          break;
        }

        sessionManager.print(received);

        // Send message to client
        String toSend = received + " World!";
        dos.writeUTF(toSend);
        log("client <- server: " + toSend);
      }
    } catch (IOException e) {
      log(e);
    } finally {
      sessionManager.remove(this);
      close();
    }
  }

  public synchronized void close() {
    if (stoppped) {
      return;
    }
    closeAll(socket, dis, dos);
    stoppped = true;
    log("Connection closed: " + socket);
  }

  public synchronized void print(String msg) throws IOException {
    dos.writeUTF(msg);
    System.out.println("public synchronized void print: " + msg);
  }
}
