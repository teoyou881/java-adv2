package network.tcp.v5;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionV5 implements Runnable {

  private final Socket socket;

  public SessionV5(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try (socket; DataInputStream input = new DataInputStream(
        socket.getInputStream()); DataOutputStream output = new DataOutputStream(
        socket.getOutputStream())) {
      while (true) {
        // Receive message from client
        String received = input.readUTF();
        log("client -> server: " + received);

        if (received.equals("exit")) {
          break;
        }

        // Send message to client
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);
      }
    } catch (IOException e) {
      log(e);
    }

    log("Connection closed: " + socket + " isClosed: " + socket.isClosed());
  }
}
