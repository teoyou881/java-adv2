package network.tcp.v4;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientV4 {

  public static final int PORT = 12345;

  public static void main(String[] args) {
    log("Client started");

    // Variables need to be accessible in the finally block, so they can't be declared inside try
    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream output = null;

    try {
      socket = new Socket("localhost", PORT);
      input = new DataInputStream(socket.getInputStream());
      output = new DataOutputStream(socket.getOutputStream());

      log("Socket connected: " + socket);
      Scanner scanner = new Scanner(System.in);

      while (true) {
        System.out.print("Enter message: ");
        String toSend = scanner.nextLine();

        // Send message to server
        output.writeUTF(toSend);
        log("client -> server: " + toSend);

        if (toSend.equals("exit")) {
          break;
        }

        // Receive message from server
        String received = input.readUTF();
        log("client <- server: " + received);
      }
    } catch (IOException e) {
      log(e);
    } finally {
      closeAll(socket, input, output);
      log("Connection closed: " + socket);
    }
  }
}
