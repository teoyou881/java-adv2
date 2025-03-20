package network.tcp.v3;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionV3 implements Runnable {

  private final Socket socket;

  public SessionV3(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      DataInputStream input = new DataInputStream(socket.getInputStream());
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());

      while (true) {
        // Receive message from client (blocking)
        String received = input.readUTF();
        log("Client -> Server: " + received);

        // If client sends "exit", terminate the session
        if (received.equals("exit")) {
          break;
        }

        // Send response to client
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("Client <- Server: " + toSend);
      }

      // Close resources
      log("Closing connection: " + socket);
      input.close();
      output.close();
      socket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
